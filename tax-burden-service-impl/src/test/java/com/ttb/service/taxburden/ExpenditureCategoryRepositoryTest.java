package com.ttb.service.taxburden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.ttb.service.taxburden.entities.ExpenditureCategoryEntity;
import com.ttb.service.taxburden.repositories.ExpenditureCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class ExpenditureCategoryRepositoryTest {

	@Autowired 
	private ExpenditureCategoryRepository expenditureCategoryRepository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	@Before
	public void setup() {
		entityManager.persist(new ExpenditureCategoryEntity("TEST_KEY_ONE", "test description one"));
		entityManager.persist(new ExpenditureCategoryEntity("TEST_KEY_TWO", "test description two"));
	}
	
	@Test
	public void saveTest() {
		ExpenditureCategoryEntity expenditureCategoryHousing = new ExpenditureCategoryEntity("HOUSING", "housing");
		ExpenditureCategoryEntity savedExpenditureCategoryHousing = expenditureCategoryRepository.save(expenditureCategoryHousing);
		System.out.println("savedExpenditureCategoryHousing" + savedExpenditureCategoryHousing);
		expenditureCategoryRepository.delete(expenditureCategoryHousing);
	}

	@Test
	@Ignore
	public void findByExpenditureCategoryKeyTest() {
		ExpenditureCategoryEntity foundExpenditureCategory = expenditureCategoryRepository.findByExpenditureCategoryKey("TEST_KEY_ONE");
		
		assertNotNull(foundExpenditureCategory);
		assertEquals("TEST_KEY_ONE", foundExpenditureCategory.getExpenditureCategoryKey());
		assertEquals("test description one", foundExpenditureCategory.getDescription());
	}
}
