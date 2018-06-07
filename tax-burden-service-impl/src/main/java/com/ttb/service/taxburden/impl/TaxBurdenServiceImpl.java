package com.ttb.service.taxburden.impl;

import com.ttb.service.taxburden.TaxBurdenService;
import com.ttb.service.taxburden.calculation.TaxCalculator;
import com.ttb.service.taxburden.calculation.TaxCalculatorFactory;
import com.ttb.service.taxburden.domain.MonetaryAmount;
import com.ttb.service.taxburden.domain.TaxBurdenReport;
import com.ttb.service.taxburden.domain.TaxPayerProfile;
import com.ttb.service.taxburden.entities.*;
import com.ttb.service.taxburden.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@RefreshScope
@Service
public class TaxBurdenServiceImpl implements TaxBurdenService {
	private static final Logger logger = LoggerFactory.getLogger(TaxBurdenServiceImpl.class);

	private static final int MAX_TAX_DEFINITION_ORDINAL = 10;
	
	@Autowired
	PoliticalDivisionRepository politicalDivisionRepository;
	@Autowired
	PostalCodePoliticalDivisionRepository postalCodePoliticalDivisionRepository;
	@Autowired
	TaxBurdenReportRepository taxBurdenReportRepository;
	@Autowired
	TaxPayerProfileRepository taxPayerProfileRepository;
	@Autowired
	TaxDefinitionRepository taxDefinitionRepository;
	@Autowired
	TaxCalculatorFactory taxCalculatorFactory;

	public List<String> findAllPoliticalDivisionsByPostalCode(String postalCode) {
		logger.info("Begin findAllPoliticalDivisionsByPostalCode");
		logger.debug("postalCode: " + postalCode);
		List<String> foundPoliticalDivisionKeys = new ArrayList<String>();
		if (postalCode != null && !"".equals(postalCode)) {
			List<PostalCodePoliticalDivisionEntity>	postalCodePoliticalDivisionEntities = new ArrayList<PostalCodePoliticalDivisionEntity>();
			postalCodePoliticalDivisionEntities = postalCodePoliticalDivisionRepository.findAllByPostalCode(postalCode);
			postalCodePoliticalDivisionEntities.forEach(p -> foundPoliticalDivisionKeys.add(p.getPoliticalDivisionKey()));
		}
		logger.info("End findAllPoliticalDivisionsByPostalCode");
		return foundPoliticalDivisionKeys;
	}
	
	public TaxBurdenReport createReport(TaxPayerProfile taxPayerProfile) {
		TaxBurdenReport taxBurdenReport = null;
		if (taxPayerProfile != null) {
			TaxPayerProfileEntity taxPayerProfileEntity = new TaxPayerProfileEntity(taxPayerProfile);
			taxPayerProfileRepository.save(taxPayerProfileEntity);
			TaxBurdenReportEntity taxBurdenReportEntity = new TaxBurdenReportEntity(taxPayerProfileEntity);
			calculateTaxes(taxPayerProfileEntity, taxBurdenReportEntity);
			taxBurdenReportRepository.save(taxBurdenReportEntity);
			taxBurdenReport = taxBurdenReportEntity.toTaxBurdenReport();
		}
		return taxBurdenReport;
	}

	public TaxBurdenReport findTaxBurdenReportByKey(String reportKey) {
		TaxBurdenReport taxBurdenReport = null;
		if (reportKey != null && !"".equals(reportKey)) {
			TaxBurdenReportEntity taxBurdenReportEntity = taxBurdenReportRepository.findByReportKey(reportKey);
			if (taxBurdenReportEntity != null) {
				taxBurdenReport = taxBurdenReportEntity.toTaxBurdenReport();
			}
		}
		return taxBurdenReport;
	}

	public TaxPayerProfile createTaxPayerProfile(String postalCode, List<String> politicalDivisionKeys, MonetaryAmount annualIncome, MonetaryAmount mortgageInterest, MonetaryAmount realPropertyMarketValue) {
		TaxPayerProfile taxPayerProfile = new TaxPayerProfile(postalCode, politicalDivisionKeys, annualIncome, mortgageInterest, realPropertyMarketValue);
		addTaxPayerProfile(new TaxPayerProfileEntity(taxPayerProfile));
		return taxPayerProfile;
	}
	
	public TaxPayerProfile findTaxPayerProfileByKey(String taxPayerProfileKey) {
		logger.info("Begin findTaxPayerProfileByKey");
		logger.debug("taxPayerProfileKey: " + taxPayerProfileKey);
		TaxPayerProfile foundTaxPayerProfile = null;
		if (taxPayerProfileKey != null && !"".equals(taxPayerProfileKey)) {
			TaxPayerProfileEntity taxPayerProfileEntity = taxPayerProfileRepository.findByTaxPayerProfileKey(taxPayerProfileKey);
			if (taxPayerProfileEntity != null) {
				foundTaxPayerProfile = taxPayerProfileEntity.toTaxPayerProfile();
			}
		}
		logger.debug("foundTaxPayerProfile: " + foundTaxPayerProfile);
		logger.info("End findTaxPayerProfileByKey");
		return foundTaxPayerProfile;
	}

	private void addTaxPayerProfile(TaxPayerProfileEntity taxPayerProfile) {
		taxPayerProfileRepository.save(taxPayerProfile);
	}

	private void calculateTaxes(TaxPayerProfileEntity taxPayerProfile, TaxBurdenReportEntity taxBurdenReport) {
		logger.info("Begin calculateTaxes");
		logger.debug("taxpayerProfile: " + taxPayerProfile);
		// Find all applicable taxDefinitions based on taxPayerProfile
		List<TaxDefinitionEntity> taxDefinitions = new ArrayList<TaxDefinitionEntity>();
		if (taxPayerProfile.getPoliticalDivisionKeys() != null) {
			for (String politicalDivisionKey : taxPayerProfile.getPoliticalDivisionKeys()) {
				logger.debug("politicalDivisionKey: " + politicalDivisionKey);
				if (politicalDivisionKey != null) {
					taxDefinitions.addAll(taxDefinitionRepository.findAllByPoliticalDivisionKey(politicalDivisionKey));
				}
			}
		}
		logger.debug("taxDefinitions: " + taxDefinitions);
		
		// Calculate and create a taxEntry for each taxDefinition, work from 0 to max ordinal
		for (int i=0; i<=MAX_TAX_DEFINITION_ORDINAL; i++) {
			logger.debug("Calculating taxDefinitions with ordinal: " + i);
			for (TaxDefinitionEntity taxDefinition : taxDefinitions) {
				if (i == taxDefinition.getOrdinal()) {
					logger.debug("taxDefinition: " + taxDefinition);
					PoliticalDivisionEntity politicalDivision = politicalDivisionRepository.findByFips(taxDefinition.getPoliticalDivisionKey());
					logger.debug("politicalDivision: " + politicalDivision);
					TaxEntryEntity taxEntry = createTaxEntry(taxPayerProfile, politicalDivision, taxDefinition, taxBurdenReport);
					taxBurdenReport.addTaxEntry(taxEntry);
				}
			}
		}
		logger.info("End calculateTaxes");
	}

	private TaxCalculator findTaxCalculator(String taxCalcStrategy) {
		return taxCalculatorFactory.getTaxCalculator(taxCalcStrategy);
	}

	private TaxEntryEntity createTaxEntry(TaxPayerProfileEntity taxPayerProfile, PoliticalDivisionEntity politicalDivision, TaxDefinitionEntity taxDefinition, TaxBurdenReportEntity taxBurdenReport) {
		TaxEntryEntity taxEntry = null;
		TaxCalculator taxCalculator = findTaxCalculator(taxDefinition.getTaxCalcStrategy());
		if (taxCalculator == null) {
			logger.warn("TaxCalculator not found taxCalcStrategy: " + taxDefinition.getTaxCalcStrategy());
		} else {
			MonetaryAmountEntity calculatedTax = taxCalculator.calculate(taxPayerProfile, politicalDivision, taxDefinition, taxBurdenReport);
			taxEntry = new TaxEntryEntity(taxDefinition.getTaxType(), politicalDivision, calculatedTax);
		}
		
		return taxEntry;
	}
	
	/**
	 * @return the taxCalculatorFactory
	 */
	public TaxCalculatorFactory getTaxCalculatorFactory() {
		return taxCalculatorFactory;
	}

	/**
	 * @param taxCalculatorFactory the taxCalculatorFactory to set
	 */
	public void setTaxCalculatorFactory(TaxCalculatorFactory taxCalculatorFactory) {
		this.taxCalculatorFactory = taxCalculatorFactory;
	}
}
