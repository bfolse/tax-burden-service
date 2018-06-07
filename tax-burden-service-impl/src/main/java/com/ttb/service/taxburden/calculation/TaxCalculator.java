package com.ttb.service.taxburden.calculation;

import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;

public interface TaxCalculator {

	public MonetaryAmountEntity calculate(TaxPayerProfileEntity taxPayerProfile, PoliticalDivisionEntity politicalDivision, 
			TaxDefinitionEntity taxDefinition, TaxBurdenReportEntity taxBurdenReport);
}
