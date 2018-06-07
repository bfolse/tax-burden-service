package com.ttb.service.taxburden.calculation;

import com.ttb.service.taxburden.entities.*;
import com.ttb.service.taxburden.repositories.AssessedRateRepository;
import com.ttb.service.taxburden.repositories.TaxRateFlatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Component
public class PropertyTaxFlatRateCalculator implements TaxCalculator {
    private static final Logger logger = LoggerFactory.getLogger(PropertyTaxFlatRateCalculator.class);

    @Autowired
    TaxRateFlatRepository taxRateFlatRepository;

    @Autowired
    AssessedRateRepository assessedRateRepository;

    Map<String, BigDecimal> taxRates;
    Map<String, BigDecimal> assessedRates;
    private final MathContext PRECISION_TWO = new MathContext(2);

    public PropertyTaxFlatRateCalculator() {
        super();
    }

    @PostConstruct
    public void init() {
        // Load Tax Rates
        // TODO Load only relevant tax rates
        taxRates = new HashMap<String, BigDecimal>();
        Iterable<TaxRateEntity> taxRateFlatIterable = taxRateFlatRepository.findAll();
        for (TaxRateEntity taxRateFlat : taxRateFlatIterable) {
            if (taxRateFlat != null) {
                taxRates.put(taxRateFlat.getTaxDefinitionKey(), taxRateFlat.getRate());
            }
        }
        // Load Assessed Rates
        assessedRates = new HashMap<String, BigDecimal>();
        Iterable<AssessedRateEntity> assessedRateIterable = assessedRateRepository.findAll();
        for (AssessedRateEntity assessedRate : assessedRateIterable) {
            if (assessedRate != null) {
                assessedRates.put(assessedRate.getTaxDefinitionKey(), assessedRate.getRate());
            }
        }
    }

    @Override
    public MonetaryAmountEntity calculate(TaxPayerProfileEntity taxPayerProfile, PoliticalDivisionEntity politicalDivision, TaxDefinitionEntity taxDefinition, TaxBurdenReportEntity taxBurdenReport) {
        logger.debug("Begin tax calculation for politicalDivision: " + politicalDivision + " taxDefinition: " + taxDefinition);
        MonetaryAmountEntity calculatedTaxAmount = null;
        BigDecimal taxRate = taxRates.get(taxDefinition.getTaxDefinitionKey());
        if (taxRate == null) {
            logger.error("No taxRate defined for taxDefinition: " + taxDefinition);
        } else {
            BigDecimal assessedRate = assessedRates.get(taxDefinition.getTaxDefinitionKey());
            if (assessedRate == null) {
                logger.debug("No assessedRate for taxDefinition: " + taxDefinition + " using 100%");
                assessedRate = BigDecimal.valueOf(1.0);
            }
            MonetaryAmountEntity realPropertyMarketValue = taxPayerProfile.getRealPropertyMarketValue();
            if (realPropertyMarketValue == null) {
                realPropertyMarketValue = new MonetaryAmountEntity(0.0);
            }
            BigDecimal assessedValue = realPropertyMarketValue.getAmount().multiply(assessedRate).setScale(2, RoundingMode.UP);
            logger.debug("assessedRate: " + assessedRate + " taxRate: " + taxRate);
            logger.debug("realPropertyMarketValue: " + realPropertyMarketValue.getAmount() + " assessedValue: " + assessedValue);
            calculatedTaxAmount = new MonetaryAmountEntity(realPropertyMarketValue.getCurrency(), assessedValue.multiply(taxRate).setScale(2, RoundingMode.UP));
            logger.debug("End tax calculation for politicalDivision: " + politicalDivision + " taxDefinition: " + taxDefinition + " calculatedTax: " + calculatedTaxAmount);
        }
        return calculatedTaxAmount;
    }

    /**
     * @param taxRateFlatRepository the taxRateFlatRepository to set
     */
    public void setTaxRateFlatRepository(TaxRateFlatRepository taxRateFlatRepository) {
        this.taxRateFlatRepository = taxRateFlatRepository;
    }

}
