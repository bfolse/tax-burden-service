package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.calculation.SalesTaxCalculator;
import com.ttb.service.taxburden.domain.TaxType;
import com.ttb.service.taxburden.entities.ConsumerExpenditureProfileEntity;
import com.ttb.service.taxburden.entities.ExpenditureCategoryGroupEntity;
import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionExpenditureCategoryEntity;
import com.ttb.service.taxburden.entities.TaxEntryEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;
import com.ttb.service.taxburden.entities.TaxRateEntity;
import com.ttb.service.taxburden.repositories.ConsumerExpenditureProfileRepository;
import com.ttb.service.taxburden.repositories.ExpenditureCategoryGroupRepository;
import com.ttb.service.taxburden.repositories.TaxDefinitionExpenditureCategoryRepository;
import com.ttb.service.taxburden.repositories.TaxRateFlatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class SalesTaxCalculatorTest {

	private SalesTaxCalculator salesTaxCalculator;
	
	@MockBean
	private TaxRateFlatRepository mockTaxRateFlatRepository;

	@MockBean
	private TaxDefinitionExpenditureCategoryRepository mockTaxDefinitionExpenditureCategoryRepository;
	
	@MockBean
	private ConsumerExpenditureProfileRepository mockConsumerExpenditureProfileRepository;
	
	@MockBean
	private ExpenditureCategoryGroupRepository mockExpenditureCategoryGroupRepository;
	
	@Before
	public void setup() {
		ArrayList<TaxRateEntity> taxRateFlatList = new ArrayList<TaxRateEntity>();
		taxRateFlatList.add(new TaxRateEntity("TD_ONE", BigDecimal.valueOf(0.05)));
		taxRateFlatList.add(new TaxRateEntity("TD_TWO", BigDecimal.valueOf(0.01)));
        given(this.mockTaxRateFlatRepository.findAll())
        .willReturn(taxRateFlatList);
        
        String taxDefinitionKeyOne = "TD_ONE";
        String taxDefinitionKeyTwo = "TD_TWO";
        String expenditureCategoryKeyOne = "EC_ONE";
        String expenditureCategoryKeyTwo = "EC_TWO";
        String expenditureCategoryGroupKeyOne = "GROUP_ONE";
        String expenditureCategoryGroupKeyTwo = "GROUP_TWO";
        List<String> groupOneCategoryKeys = new ArrayList<String>();
        groupOneCategoryKeys.add(expenditureCategoryKeyOne);
        groupOneCategoryKeys.add(expenditureCategoryKeyTwo);
        List<String> groupTwoCategoryKeys = new ArrayList<String>();
        groupTwoCategoryKeys.add(expenditureCategoryKeyOne);
        ExpenditureCategoryGroupEntity egceOne = new ExpenditureCategoryGroupEntity(expenditureCategoryGroupKeyOne, "desc one", groupOneCategoryKeys);
        ExpenditureCategoryGroupEntity egceTwo = new ExpenditureCategoryGroupEntity(expenditureCategoryGroupKeyTwo, "desc two", groupTwoCategoryKeys);
        given(this.mockExpenditureCategoryGroupRepository.findByExpenditureCategoryGroupKey(expenditureCategoryGroupKeyOne)).willReturn(egceOne);
        given(this.mockExpenditureCategoryGroupRepository.findByExpenditureCategoryGroupKey(expenditureCategoryGroupKeyTwo)).willReturn(egceTwo);
        
        List<TaxDefinitionExpenditureCategoryEntity> tdeceList= new ArrayList<TaxDefinitionExpenditureCategoryEntity>();
        tdeceList.add(new TaxDefinitionExpenditureCategoryEntity(taxDefinitionKeyOne, expenditureCategoryGroupKeyOne));
        tdeceList.add(new TaxDefinitionExpenditureCategoryEntity(taxDefinitionKeyTwo, expenditureCategoryGroupKeyTwo));
        given(this.mockTaxDefinitionExpenditureCategoryRepository.findAll()).willReturn(tdeceList);
        
        Map<String, BigDecimal> profileEntryListBasic = new HashMap<String, BigDecimal>();
        profileEntryListBasic.put(expenditureCategoryKeyOne, BigDecimal.valueOf(0.01));
        profileEntryListBasic.put(expenditureCategoryKeyTwo, BigDecimal.valueOf(0.05));
        ConsumerExpenditureProfileEntity cepeBasic = new ConsumerExpenditureProfileEntity("BASIC", "basic description", profileEntryListBasic);
        Map<String, BigDecimal> profileEntryListSpender = new HashMap<String, BigDecimal>();
        profileEntryListSpender.put(expenditureCategoryKeyOne, BigDecimal.valueOf(0.05));
        profileEntryListSpender.put(expenditureCategoryKeyTwo, BigDecimal.valueOf(0.05));
        ConsumerExpenditureProfileEntity cepeSpender = new ConsumerExpenditureProfileEntity("SPENDER", "spender description", profileEntryListSpender);
        List<ConsumerExpenditureProfileEntity> cepeList = new ArrayList<ConsumerExpenditureProfileEntity>();
        cepeList.add(cepeBasic);
        cepeList.add(cepeSpender);
        given(this.mockConsumerExpenditureProfileRepository.findAll()).willReturn(cepeList);
	}
	
	@Test
	public void calculateTest() {
		salesTaxCalculator = new SalesTaxCalculator();
		salesTaxCalculator.setTaxRateFlatRepository(mockTaxRateFlatRepository);
		salesTaxCalculator.setConsumerExpenditureProfileRepository(mockConsumerExpenditureProfileRepository);
		salesTaxCalculator.setTaxDefinitionExpenditureCategoryRepository(mockTaxDefinitionExpenditureCategoryRepository);
		salesTaxCalculator.setExpenditureCategoryGroupRepository(mockExpenditureCategoryGroupRepository);
		salesTaxCalculator.init();
		
		TaxBurdenReportEntity taxBurdenReport = new TaxBurdenReportEntity();
		PoliticalDivisionEntity politicalDivision = new PoliticalDivisionEntity("0", "US", "United States Federal", "COUNTRY");
		TaxEntryEntity taxEntry = new TaxEntryEntity(TaxType.INCOME_FEDERAL, politicalDivision, new MonetaryAmountEntity(5000.00));
		taxBurdenReport.addTaxEntry(taxEntry);

		TaxPayerProfileEntity taxPayerProfileBasic = new TaxPayerProfileEntity("30306", null, new MonetaryAmountEntity(BigDecimal.valueOf(10000)), 
				new MonetaryAmountEntity(BigDecimal.valueOf(0)), "BASIC");
		TaxDefinitionEntity taxDefinitionOne = new TaxDefinitionEntity(TaxType.SALES_STATE, null, "salesTaxCalculator", "TD_ONE", "test description one", 1);
		MonetaryAmountEntity resultOne = salesTaxCalculator.calculate(taxPayerProfileBasic, null, taxDefinitionOne, taxBurdenReport);
		assertNotNull(resultOne);
		assertEquals(30, resultOne.getAmount().longValue());

		TaxPayerProfileEntity taxPayerProfileSpender = new TaxPayerProfileEntity("30306", null, new MonetaryAmountEntity(BigDecimal.valueOf(10000)), 
				new MonetaryAmountEntity(BigDecimal.valueOf(0)), "SPENDER");
		TaxDefinitionEntity taxDefinitionTwo = new TaxDefinitionEntity(TaxType.SALES_STATE, null, "salesTaxCalculator", "TD_TWO", "test description two", 1);
		MonetaryAmountEntity resultTwo = salesTaxCalculator.calculate(taxPayerProfileSpender, null, taxDefinitionTwo, taxBurdenReport);
		assertNotNull(resultTwo);
		assertEquals(5, resultTwo.getAmount().longValue());
}
	
}
