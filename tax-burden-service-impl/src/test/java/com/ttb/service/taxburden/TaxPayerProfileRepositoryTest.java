package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;
import com.ttb.service.taxburden.repositories.TaxPayerProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class TaxPayerProfileRepositoryTest {
	@Autowired
	private TaxPayerProfileRepository taxPayerProfileRepository;

	private static TaxPayerProfileEntity taxPayerProfileOne = new TaxPayerProfileEntity("30306", new MonetaryAmountEntity(new BigDecimal(100000.00)), new MonetaryAmountEntity(new BigDecimal(5000.00)));

	@Test
	public void saveTest() {
		taxPayerProfileRepository.save(taxPayerProfileOne);
		taxPayerProfileRepository.delete(taxPayerProfileOne);
	}
	
	@Test
	public void findByKeyTest() {
		taxPayerProfileRepository.save(taxPayerProfileOne);
		String key = taxPayerProfileOne.getTaxPayerProfileKey();
		TaxPayerProfileEntity foundTaxPayerProfileEntity = taxPayerProfileRepository.findByTaxPayerProfileKey(key);
		assertNotNull(foundTaxPayerProfileEntity);
		assertEquals(key, foundTaxPayerProfileEntity.getTaxPayerProfileKey());
		taxPayerProfileRepository.delete(taxPayerProfileOne);
	}
	
	@Test
	public void politicalDivisionsTest() {
		taxPayerProfileOne.addPoliticalDivisionKey("13");
		taxPayerProfileRepository.save(taxPayerProfileOne);
		taxPayerProfileRepository.delete(taxPayerProfileOne);
	}
}
