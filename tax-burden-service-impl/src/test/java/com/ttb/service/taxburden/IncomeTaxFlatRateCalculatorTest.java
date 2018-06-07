package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.calculation.IncomeTaxFlatRateCalculator;
import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;
import com.ttb.service.taxburden.entities.TaxEntryEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;
import com.ttb.service.taxburden.entities.TaxRateEntity;
import com.ttb.service.taxburden.domain.MonetaryAmount;
import com.ttb.service.taxburden.domain.TaxType;
import com.ttb.service.taxburden.repositories.TaxRateFlatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class IncomeTaxFlatRateCalculatorTest {

	private IncomeTaxFlatRateCalculator incomeTaxFlatRateCalculator;
	
	@MockBean
	private TaxRateFlatRepository mockTaxRateFlatRepository;
	
	@Before
	public void setup() {
		TaxRateEntity taxRateFlat = new TaxRateEntity("TEST", BigDecimal.valueOf(0.05));
		ArrayList<TaxRateEntity> taxRateFlatList = new ArrayList<TaxRateEntity>();
		taxRateFlatList.add(taxRateFlat);
        given(this.mockTaxRateFlatRepository.findAll())
        .willReturn(taxRateFlatList);
        
	}
	
	@Test
	public void calculateTest() {
		incomeTaxFlatRateCalculator = new IncomeTaxFlatRateCalculator();
		incomeTaxFlatRateCalculator.setTaxRateFlatRepository(mockTaxRateFlatRepository);
		incomeTaxFlatRateCalculator.init();
		TaxBurdenReportEntity taxBurdenReport = new TaxBurdenReportEntity();
		PoliticalDivisionEntity politicalDivision = new PoliticalDivisionEntity("0", "US", "United States Federal", "COUNTRY");
		TaxEntryEntity taxEntry = new TaxEntryEntity(TaxType.INCOME_FEDERAL, politicalDivision, new MonetaryAmountEntity(5000.00));
		taxBurdenReport.addTaxEntry(taxEntry);
		TaxPayerProfileEntity taxPayerProfile = new TaxPayerProfileEntity("30306", new MonetaryAmountEntity(BigDecimal.valueOf(10000)), new MonetaryAmountEntity(BigDecimal.valueOf(0)));
		TaxDefinitionEntity taxDefinition = new TaxDefinitionEntity(TaxType.INCOME_STATE, null, "incomeTaxFlatRateCalculator", "TEST", "test description", 1);
		MonetaryAmountEntity result = incomeTaxFlatRateCalculator.calculate(taxPayerProfile, null, taxDefinition, taxBurdenReport);
		assertNotNull(result);
		assertEquals(500, result.getAmount().longValue());
	}
	
}
