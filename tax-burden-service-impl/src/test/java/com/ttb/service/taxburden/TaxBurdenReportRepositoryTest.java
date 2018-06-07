package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.entities.TaxEntryEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;
import com.ttb.service.taxburden.domain.TaxType;
import com.ttb.service.taxburden.repositories.PoliticalDivisionRepository;
import com.ttb.service.taxburden.repositories.TaxBurdenReportRepository;
import com.ttb.service.taxburden.repositories.TaxPayerProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class TaxBurdenReportRepositoryTest {

	@Autowired
	private TaxBurdenReportRepository repository;
	@Autowired
	private PoliticalDivisionRepository politicalDivisionRepository;
	@Autowired
	private TaxPayerProfileRepository taxPayerProfileRepository;
    @Autowired
    private TestEntityManager entityManager;

	private static TaxPayerProfileEntity taxPayerProfileOne;
	
	@Before
	public void setup() {
		entityManager.persist(new PoliticalDivisionEntity("13", "GA", "Georgia", "STATE"));
		entityManager.persist(new PoliticalDivisionEntity("089", "Dekalb", "Dekalb County", "COUNTY"));
		taxPayerProfileOne = entityManager.persist(new TaxPayerProfileEntity("30306", new MonetaryAmountEntity(new BigDecimal(100000.00)), new MonetaryAmountEntity(new BigDecimal(5000.00))));
	}
	
	@Test
	public void saveTest() {
		System.out.println("Begin saveTest()");
		//taxPayerProfileRepository.save(taxPayerProfileOne);
		PoliticalDivisionEntity politicalDivision13 = politicalDivisionRepository.findByFips("13");
		TaxEntryEntity taxEntryOne = new TaxEntryEntity(TaxType.INCOME_STATE, politicalDivision13, new MonetaryAmountEntity(new BigDecimal(1000.00)));
		TaxBurdenReportEntity taxBurdenReport = new TaxBurdenReportEntity(taxPayerProfileOne);
		taxBurdenReport.addTaxEntry(taxEntryOne);
		repository.save(taxBurdenReport);
		repository.delete(taxBurdenReport);
		System.out.println("End saveTest()");
	}

	@Test
	public void findByReportKeyTest() {
		System.out.println("Begin findByReportKeyTest()");
		//taxPayerProfileRepository.save(taxPayerProfileOne);
		PoliticalDivisionEntity politicalDivision13 = politicalDivisionRepository.findByFips("13");
		TaxEntryEntity taxEntryOne = new TaxEntryEntity(TaxType.INCOME_STATE, politicalDivision13, new MonetaryAmountEntity(new BigDecimal(1000.00)));
		TaxBurdenReportEntity taxBurdenReport = new TaxBurdenReportEntity(taxPayerProfileOne);
		taxBurdenReport.addTaxEntry(taxEntryOne);
		repository.save(taxBurdenReport);
		String reportKey = taxBurdenReport.getReportKey();
		TaxBurdenReportEntity foundTaxBurdenReport = repository.findByReportKey(reportKey);
		assertNotNull(foundTaxBurdenReport);
		assertEquals(reportKey, foundTaxBurdenReport.getReportKey());
		repository.delete(taxBurdenReport);
		System.out.println("End findByReportKeyTest()");
	}

	@Test
	public void getTaxEntryTest() {
		System.out.println("Begin getTaxEntryTest()");
		PoliticalDivisionEntity politicalDivision13 = politicalDivisionRepository.findByFips("13");
		TaxEntryEntity taxEntryOne = new TaxEntryEntity(TaxType.INCOME_STATE, politicalDivision13, new MonetaryAmountEntity(new BigDecimal(1000.00)));
		TaxEntryEntity taxEntryTwo = new TaxEntryEntity(TaxType.SALES_STATE, politicalDivision13, new MonetaryAmountEntity(new BigDecimal(100.00)));
		TaxBurdenReportEntity taxBurdenReport = new TaxBurdenReportEntity(taxPayerProfileOne);
		taxBurdenReport.addTaxEntry(taxEntryOne);
		taxBurdenReport.addTaxEntry(taxEntryTwo);
		assertEquals(taxEntryOne, taxBurdenReport.getTaxEntry(TaxType.INCOME_STATE, "13"));
		System.out.println("End getTaxEntryTest()");
	}

}
