package com.ttb.service.taxburden;

import java.math.BigDecimal;

import com.ttb.service.taxburden.calculation.TaxCalculator;
import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;

public class MockTaxCalculator implements TaxCalculator {

	@Override
	public MonetaryAmountEntity calculate(TaxPayerProfileEntity taxPayerProfile, PoliticalDivisionEntity politicalDivision, 
			TaxDefinitionEntity taxDefinition, TaxBurdenReportEntity taxBurdenReport) {
		MonetaryAmountEntity monetaryAmount = null;
		if (taxPayerProfile != null) {
			BigDecimal annualIncome = taxPayerProfile.getAnnualIncome().getAmount();
			monetaryAmount = new MonetaryAmountEntity(annualIncome.multiply(BigDecimal.valueOf(0.05)));
		}
		return monetaryAmount;
	}

}
