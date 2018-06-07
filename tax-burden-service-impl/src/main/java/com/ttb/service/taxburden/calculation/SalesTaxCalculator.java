package com.ttb.service.taxburden.calculation;

import com.ttb.service.taxburden.domain.TaxType;
import com.ttb.service.taxburden.entities.*;
import com.ttb.service.taxburden.repositories.ConsumerExpenditureProfileRepository;
import com.ttb.service.taxburden.repositories.ExpenditureCategoryGroupRepository;
import com.ttb.service.taxburden.repositories.TaxDefinitionExpenditureCategoryRepository;
import com.ttb.service.taxburden.repositories.TaxRateFlatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SalesTaxCalculator implements TaxCalculator {
	private static final Logger logger = LoggerFactory.getLogger(SalesTaxCalculator.class);
	private final MathContext PRECISION_TWO = new MathContext(2);

	@Autowired
	TaxRateFlatRepository taxRateFlatRepository;

	@Autowired
	TaxDefinitionExpenditureCategoryRepository taxDefinitionExpenditureCategoryRepository;
	
	@Autowired
	ConsumerExpenditureProfileRepository consumerExpenditureProfileRepository;
	
	@Autowired
	ExpenditureCategoryGroupRepository expenditureCategoryGroupRepository;
	
	Map<String, BigDecimal> taxRates;
	
	Map<String, List<String>> taxDefinitionExpenditureCategories;
	
	Map<String, Map<String, BigDecimal>> consumerExpenditureProfiles;
	
	public SalesTaxCalculator() {
		super();
	}

	@PostConstruct
	public void init() {
		// Setup tax rates
		taxRates = new HashMap<String, BigDecimal>();
		Iterable<TaxRateEntity> taxRateFlatIterable = taxRateFlatRepository.findAll();
		for (TaxRateEntity taxRateFlat : taxRateFlatIterable) {
			if (taxRateFlat != null) {
				taxRates.put(taxRateFlat.getTaxDefinitionKey(), taxRateFlat.getRate());
			}
		}
		logger.debug("taxRates: " + taxRates);
		
		// Setup taxDefinition to expenditureCategories map
		taxDefinitionExpenditureCategories = new HashMap<String, List<String>>();
		taxDefinitionExpenditureCategoryRepository.findAll().forEach(k -> {
			String taxDefinitionKey = k.getTaxDefinitionKey();
			if (!taxDefinitionExpenditureCategories.containsKey(taxDefinitionKey)) {
				taxDefinitionExpenditureCategories.put(taxDefinitionKey, new ArrayList<String>());
			}
			ExpenditureCategoryGroupEntity expenditureCategoryGroup = expenditureCategoryGroupRepository.findByExpenditureCategoryGroupKey(k.getExpenditureCategoryGroupKey());
			if (expenditureCategoryGroup != null) {
				taxDefinitionExpenditureCategories.get(taxDefinitionKey).addAll(expenditureCategoryGroup.getExpenditureCategoryKeys());
			}
		});
		logger.debug("taxDefinitionExpenditureCategories: " + taxDefinitionExpenditureCategories);
		
		// Setup consumerExpenditureProfiles
		consumerExpenditureProfiles = new HashMap<String, Map<String, BigDecimal>>();
		consumerExpenditureProfileRepository.findAll().forEach(k -> {
			consumerExpenditureProfiles.put(k.getConsumerExpenditureProfileKey(), k.getConsumerExpenditureProfileEntries());
		});
		logger.debug("consumerExpenditureProfiles: " + consumerExpenditureProfiles);
	}
	
	@Override
	public MonetaryAmountEntity calculate(TaxPayerProfileEntity taxPayerProfile, PoliticalDivisionEntity politicalDivision, TaxDefinitionEntity taxDefinition, TaxBurdenReportEntity taxBurdenReport) {
		logger.debug("Begin tax calculation for politicalDivision: " + politicalDivision + " taxDefinition: " + taxDefinition);
		MonetaryAmountEntity calculatedSalesTax = null;
		
		// Get applicable rate
		BigDecimal rate = taxRates.get(taxDefinition.getTaxDefinitionKey());
		logger.debug("Rate: " + rate);
		if (rate == null) {
			logger.error("No rate defined for taxDefinitionKey: " + taxDefinition.getTaxDefinitionKey());
		} else {
			// Determine total annual expenditure
			// Subtract income taxes (federal and state)
			BigDecimal calculatedIncomeTaxes = new BigDecimal(0.0, PRECISION_TWO);
			List<TaxEntryEntity> federalIncomeTaxEntries = taxBurdenReport.getTaxEntries(TaxType.INCOME_FEDERAL);
			for (TaxEntryEntity entry : federalIncomeTaxEntries) {
				calculatedIncomeTaxes = calculatedIncomeTaxes.add(entry.getAmount().getAmount());
			}
			List<TaxEntryEntity> stateIncomeTaxEntries = taxBurdenReport.getTaxEntries(TaxType.INCOME_STATE);
			for (TaxEntryEntity entry : stateIncomeTaxEntries) {
				calculatedIncomeTaxes = calculatedIncomeTaxes.add(entry.getAmount().getAmount());
			}
			logger.debug("calculatedIncomeTaxes: " + calculatedIncomeTaxes);
			BigDecimal totalExpenditures = taxPayerProfile.getAnnualIncome().getAmount().subtract(calculatedIncomeTaxes);
			logger.debug("totalExpenditures: " + totalExpenditures);
			
			// Get applicable expenditure categories
			List<String> applicableExpenditureCategories = taxDefinitionExpenditureCategories.get(taxDefinition.getTaxDefinitionKey());
			logger.debug("applicableExpenditureCategories: " + applicableExpenditureCategories);
			BigDecimal taxableExpenditures = new BigDecimal(0.0, PRECISION_TWO);
			String consumerExpenditureProfileKey = taxPayerProfile.getConsumerExpenditureProfileKey();
			logger.debug("using consumerExpenditureProfile: " + consumerExpenditureProfileKey);
			Map<String, BigDecimal> consumerExpenditureProfileEntries = consumerExpenditureProfiles.get(consumerExpenditureProfileKey);
			if (consumerExpenditureProfileEntries == null) {
				logger.error("No consumerExpenditureProfile found for key: " + consumerExpenditureProfileKey);
			} else {
				for (String applicableExpenditureCategory : applicableExpenditureCategories) {
					BigDecimal expenditureShare = consumerExpenditureProfileEntries.get(applicableExpenditureCategory);
					if (expenditureShare == null) {
						logger.warn("Expected expenditureCategory: " + applicableExpenditureCategory + " in consumerExpenditureProfile, but was not found");
					} else {
						BigDecimal categoryExpenditure = totalExpenditures.multiply(expenditureShare);
						taxableExpenditures = taxableExpenditures.add(categoryExpenditure);
					}
				}
				logger.debug("taxableExpenditures: " + taxableExpenditures);
				
				calculatedSalesTax = new MonetaryAmountEntity(taxableExpenditures.multiply(rate).setScale(2, RoundingMode.UP));
				logger.debug("End tax calculation for politicalDivision: " + politicalDivision + " taxDefinition: " + taxDefinition + " calculatedTax: " + calculatedSalesTax);
			}
		}
		return calculatedSalesTax;
	}

	/**
	 * @param taxRateFlatRepository the taxRateFlatRepository to set
	 */
	public void setTaxRateFlatRepository(TaxRateFlatRepository taxRateFlatRepository) {
		this.taxRateFlatRepository = taxRateFlatRepository;
	}

	/**
	 * @param taxDefinitionExpenditureCategoryRepository the taxDefinitionExpenditureCategoryRepository to set
	 */
	public void setTaxDefinitionExpenditureCategoryRepository(
			TaxDefinitionExpenditureCategoryRepository taxDefinitionExpenditureCategoryRepository) {
		this.taxDefinitionExpenditureCategoryRepository = taxDefinitionExpenditureCategoryRepository;
	}

	/**
	 * @param consumerExpenditureProfileRepository the consumerExpenditureProfileRepository to set
	 */
	public void setConsumerExpenditureProfileRepository(
			ConsumerExpenditureProfileRepository consumerExpenditureProfileRepository) {
		this.consumerExpenditureProfileRepository = consumerExpenditureProfileRepository;
	}

	/**
	 * @return the expenditureCategoryGroupRepository
	 */
	public ExpenditureCategoryGroupRepository getExpenditureCategoryGroupRepository() {
		return expenditureCategoryGroupRepository;
	}

	/**
	 * @param expenditureCategoryGroupRepository the expenditureCategoryGroupRepository to set
	 */
	public void setExpenditureCategoryGroupRepository(
			ExpenditureCategoryGroupRepository expenditureCategoryGroupRepository) {
		this.expenditureCategoryGroupRepository = expenditureCategoryGroupRepository;
	}

}
