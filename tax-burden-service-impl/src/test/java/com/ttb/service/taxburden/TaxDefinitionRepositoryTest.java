package com.ttb.service.taxburden;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;
import com.ttb.service.taxburden.domain.TaxType;
import com.ttb.service.taxburden.repositories.TaxDefinitionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class TaxDefinitionRepositoryTest {
	@Autowired 
	private TaxDefinitionRepository taxDefinitionRepository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	@Before
	public void setup() {
		entityManager.persist(new PoliticalDivisionEntity("13", "GA", "Georgia", "STATE"));
		entityManager.persist(new PoliticalDivisionEntity("089", "Dekalb", "Dekalb County", "COUNTY"));
	}
	
	@Test
	public void saveTest() {
		TaxDefinitionEntity taxDefinitionGAIncome = new TaxDefinitionEntity(TaxType.INCOME_STATE, "13", "GA_INCOME", "STATE_GA_INCOME", "Georgia State Income Tax", 1);
		TaxDefinitionEntity savedTaxDefinition = taxDefinitionRepository.save(taxDefinitionGAIncome);
		System.out.println("savedTaxDefinition:" + savedTaxDefinition);
		taxDefinitionRepository.delete(taxDefinitionGAIncome);
	}

	@Test
	@Ignore
	public void findByPoliticalDivisionTest() {
		TaxDefinitionEntity taxDefinitionGAIncome = new TaxDefinitionEntity(TaxType.INCOME_STATE, "13", "GA_INCOME", "STATE_GA_INCOME", "Georgia State Income Tax", 1);
		taxDefinitionRepository.save(taxDefinitionGAIncome);
		TaxDefinitionEntity taxDefinitionGASales = new TaxDefinitionEntity(TaxType.SALES_STATE, "13", "GA_SALES", "STATE_GA_SALES", "Georgia State Sales Tax", 2);
		taxDefinitionRepository.save(taxDefinitionGASales);
		
		List<TaxDefinitionEntity> foundTaxDefinitions = taxDefinitionRepository.findAllByPoliticalDivisionKey("13");
		assertNotNull(foundTaxDefinitions);
		assertEquals(2, foundTaxDefinitions.size());
		for (TaxDefinitionEntity foundTaxDefinition : foundTaxDefinitions) {
			assertEquals("13", foundTaxDefinition.getPoliticalDivisionKey());
		}
		assertThat(foundTaxDefinitions).hasSize(2).extracting("taxType").containsOnly(TaxType.INCOME_STATE, TaxType.SALES_STATE);

		taxDefinitionRepository.delete(taxDefinitionGAIncome);
		taxDefinitionRepository.delete(taxDefinitionGASales);
	}
}
