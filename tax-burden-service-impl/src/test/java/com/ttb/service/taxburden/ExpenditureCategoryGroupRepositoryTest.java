package com.ttb.service.taxburden;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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

import com.ttb.service.taxburden.entities.ExpenditureCategoryEntity;
import com.ttb.service.taxburden.entities.ExpenditureCategoryGroupEntity;
import com.ttb.service.taxburden.repositories.ExpenditureCategoryGroupRepository;
import com.ttb.service.taxburden.repositories.ExpenditureCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class ExpenditureCategoryGroupRepositoryTest {

	@Autowired 
	private ExpenditureCategoryGroupRepository expenditureCategoryGroupRepository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	@Before
	public void setup() {
		List<String> expenditureCategoriesOne = new ArrayList<String>();
		expenditureCategoriesOne.add("CATEGORY_ONE");
		expenditureCategoriesOne.add("CATEGORY_TWO");
		expenditureCategoriesOne.add("CATEGORY_THREE");
		List<String> expenditureCategoriesTwo = new ArrayList<String>();
		expenditureCategoriesTwo.add("CATEGORY_ONE");
		entityManager.persist(new ExpenditureCategoryGroupEntity("GROUP_ONE", "group one", expenditureCategoriesOne));
		entityManager.persist(new ExpenditureCategoryGroupEntity("GROUP_TWO", "group two", expenditureCategoriesTwo));
	}
	
	@Test
	public void saveTest() {
		List<String> expenditureCategoriesThree = new ArrayList<String>();
		expenditureCategoriesThree.add("CATEGORY_ONE");
		expenditureCategoriesThree.add("CATEGORY_TWO");

		ExpenditureCategoryGroupEntity expenditureCategoryGroupThree = new ExpenditureCategoryGroupEntity("GROUP_THREE", "group three", expenditureCategoriesThree);
		ExpenditureCategoryGroupEntity savedExpenditureCategoryGroupThree = expenditureCategoryGroupRepository.save(expenditureCategoryGroupThree);
		System.out.println("savedExpenditureCategoryGroupThree" + savedExpenditureCategoryGroupThree);
		expenditureCategoryGroupRepository.delete(savedExpenditureCategoryGroupThree);
	}

	@Test
	public void findByExpenditureCategoryKeyTest() {
		// GROUP_ONE
		ExpenditureCategoryGroupEntity foundExpenditureCategoryGroup = expenditureCategoryGroupRepository.findByExpenditureCategoryGroupKey("GROUP_ONE");
		assertNotNull(foundExpenditureCategoryGroup);
		assertEquals("GROUP_ONE", foundExpenditureCategoryGroup.getExpenditureCategoryGroupKey());
		assertEquals(3, foundExpenditureCategoryGroup.getExpenditureCategoryKeys().size());
		assertThat(foundExpenditureCategoryGroup.getExpenditureCategoryKeys()).containsExactly("CATEGORY_ONE", "CATEGORY_TWO", "CATEGORY_THREE");

		// GROUP_TWO
		foundExpenditureCategoryGroup = expenditureCategoryGroupRepository.findByExpenditureCategoryGroupKey("GROUP_TWO");
		assertNotNull(foundExpenditureCategoryGroup);
		assertEquals("GROUP_TWO", foundExpenditureCategoryGroup.getExpenditureCategoryGroupKey());
		assertEquals(1, foundExpenditureCategoryGroup.getExpenditureCategoryKeys().size());
		assertThat(foundExpenditureCategoryGroup.getExpenditureCategoryKeys()).containsExactly("CATEGORY_ONE");
	}
}
