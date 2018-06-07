package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.TaxRateEntity;
import com.ttb.service.taxburden.repositories.TaxRateFlatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class TaxRateFlatRepositoryTest {
	@Autowired 
	private TaxRateFlatRepository taxRateFlatRepository;
	
	@Test
	public void saveTest() {
		TaxRateEntity taxRateFlatOne = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.05));
		TaxRateEntity savedTaxRateFlatOne = taxRateFlatRepository.save(taxRateFlatOne);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatOne);
		TaxRateEntity taxRateFlatTwo = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.07));
		TaxRateEntity savedTaxRateFlatTwo = taxRateFlatRepository.save(taxRateFlatTwo);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatTwo);
		
		taxRateFlatRepository.delete(taxRateFlatOne);
		taxRateFlatRepository.delete(taxRateFlatTwo);
	}

	@Test
	public void findAllTest() {
		TaxRateEntity taxRateFlatOne = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.05));
		TaxRateEntity savedTaxRateFlatOne = taxRateFlatRepository.save(taxRateFlatOne);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatOne);
		TaxRateEntity taxRateFlatTwo = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.07));
		TaxRateEntity savedTaxRateFlatTwo = taxRateFlatRepository.save(taxRateFlatTwo);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatTwo);
		
		List<TaxRateEntity> taxRateFlatList = new ArrayList<>();
		Iterable<TaxRateEntity> taxRateFlatIterable = taxRateFlatRepository.findAll();
		assertNotNull(taxRateFlatIterable);
		taxRateFlatIterable.forEach(k -> taxRateFlatList.add(k));
		assertEquals(2, taxRateFlatList.size());
		
		taxRateFlatRepository.delete(taxRateFlatOne);
		taxRateFlatRepository.delete(taxRateFlatTwo);
}
	
	@Test
	public void findByTaxDefinitionKeyTest() {
		TaxRateEntity taxRateFlatOne = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.05));
		TaxRateEntity savedTaxRateFlatOne = taxRateFlatRepository.save(taxRateFlatOne);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatOne);
		TaxRateEntity taxRateFlatTwo = new TaxRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.07));
		TaxRateEntity savedTaxRateFlatTwo = taxRateFlatRepository.save(taxRateFlatTwo);
		System.out.println("savedTaxRateFlat: " + savedTaxRateFlatTwo);
		
		TaxRateEntity taxRateFlat = taxRateFlatRepository.findByTaxDefinitionKey("TEST_TAX_DEFINITION_NAME_ONE");
		assertNotNull(taxRateFlat);
		assertEquals(BigDecimal.valueOf(0.05), taxRateFlat.getRate());
		
		taxRateFlatRepository.delete(taxRateFlatOne);
		taxRateFlatRepository.delete(taxRateFlatTwo);
	}
}
