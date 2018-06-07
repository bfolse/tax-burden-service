package com.ttb.service.taxburden;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.TaxDefinitionExpenditureCategoryEntity;
import com.ttb.service.taxburden.repositories.TaxDefinitionExpenditureCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })

public class TaxDefinitionExpenditureCategoryRepositoryTest {
	
	@Autowired
	private TaxDefinitionExpenditureCategoryRepository repository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	private TaxDefinitionExpenditureCategoryEntity taxDefinitionExpenditureCategoryOneOne = new TaxDefinitionExpenditureCategoryEntity("TAX_DEF_ONE", "GROUP_ONE");
	private TaxDefinitionExpenditureCategoryEntity taxDefinitionExpenditureCategoryTwoTwo = new TaxDefinitionExpenditureCategoryEntity("TAX_DEF_TWO", "GROUP_TWO");
	private TaxDefinitionExpenditureCategoryEntity taxDefinitionExpenditureCategoryTest = new TaxDefinitionExpenditureCategoryEntity("TAX_DEF_TEST", "GROUP_TEST");
	
	@Before
	public void setup() {
		entityManager.persist(taxDefinitionExpenditureCategoryOneOne);
		entityManager.persist(taxDefinitionExpenditureCategoryTwoTwo);
	}
	
	@Test
	public void findAllTest() {
		assertThat(repository.findAll()).hasSize(2).extracting("taxDefinitionKey").containsOnly("TAX_DEF_ONE","TAX_DEF_TWO");
		assertThat(repository.findAll()).extracting("expenditureCategoryGroupKey").containsOnly("GROUP_ONE","GROUP_TWO");
	}

	@Test
	public void countTest() {
		assertEquals(2, repository.count());
	}
	
	@Test
	public void saveTest() {
		TaxDefinitionExpenditureCategoryEntity savedTaxDefinitionExpenditureCategory = repository.save(taxDefinitionExpenditureCategoryTest);
		System.out.println("savedTaxDefinitionExpenditureCategory: " + savedTaxDefinitionExpenditureCategory);
		repository.delete(taxDefinitionExpenditureCategoryTest);
	}
	
	@Test
	public void findAllByTaxDefinitionKeyTest() {
		assertThat(repository.findByTaxDefinitionKey("TAX_DEF_ONE")).isEqualTo(taxDefinitionExpenditureCategoryOneOne);
		assertThat(repository.findByTaxDefinitionKey("TAX_DEF_TWO")).isEqualTo(taxDefinitionExpenditureCategoryTwoTwo);
	}
}
