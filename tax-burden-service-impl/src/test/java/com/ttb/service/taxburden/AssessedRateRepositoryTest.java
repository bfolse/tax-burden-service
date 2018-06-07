package com.ttb.service.taxburden;

import com.ttb.service.taxburden.entities.AssessedRateEntity;
import com.ttb.service.taxburden.repositories.AssessedRateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class AssessedRateRepositoryTest {
	@Autowired 
	private AssessedRateRepository assessedRateRepository;
	
	@Test
	public void saveTest() {
		AssessedRateEntity assessedRateOne = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.05));
		AssessedRateEntity savedAssessedRateOne = assessedRateRepository.save(assessedRateOne);
		System.out.println("savedAssessedRate: " + savedAssessedRateOne);
		AssessedRateEntity assessedRateTwo = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.07));
		AssessedRateEntity savedAssessedRateTwo = assessedRateRepository.save(assessedRateTwo);
		System.out.println("savedAssessedRate: " + savedAssessedRateTwo);
		
		assessedRateRepository.delete(assessedRateOne);
		assessedRateRepository.delete(assessedRateTwo);
	}

	@Test
	public void findAllTest() {
		AssessedRateEntity assessedRateOne = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.40));
		AssessedRateEntity savedAssessedRateOne = assessedRateRepository.save(assessedRateOne);
		System.out.println("savedAssessedRate: " + savedAssessedRateOne);
		AssessedRateEntity assessedRateTwo = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.80));
		AssessedRateEntity savedAssessedRateTwo = assessedRateRepository.save(assessedRateTwo);
		System.out.println("savedAssessedRate: " + savedAssessedRateTwo);
		
		List<AssessedRateEntity> assessedRateList = new ArrayList<>();
		Iterable<AssessedRateEntity> assessedRateIterable = assessedRateRepository.findAll();
		assertNotNull(assessedRateIterable);
		assessedRateIterable.forEach(k -> assessedRateList.add(k));
		assertEquals(2, assessedRateList.size());
		
		assessedRateRepository.delete(assessedRateOne);
		assessedRateRepository.delete(assessedRateTwo);
}
	
	@Test
	public void findByTaxDefinitionKeyTest() {
		AssessedRateEntity assessedRateOne = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_ONE", BigDecimal.valueOf(0.40));
		AssessedRateEntity savedAssessedRateOne = assessedRateRepository.save(assessedRateOne);
		System.out.println("savedAssessedRate: " + savedAssessedRateOne);
		AssessedRateEntity assessedRateTwo = new AssessedRateEntity("TEST_TAX_DEFINITION_NAME_TWO", BigDecimal.valueOf(0.80));
		AssessedRateEntity savedAssessedRateTwo = assessedRateRepository.save(assessedRateTwo);
		System.out.println("savedAssessedRate: " + savedAssessedRateTwo);
		
		AssessedRateEntity assessedRate = assessedRateRepository.findByTaxDefinitionKey("TEST_TAX_DEFINITION_NAME_ONE");
		assertNotNull(assessedRate);
		assertEquals(BigDecimal.valueOf(0.40), assessedRate.getRate());
		
		assessedRateRepository.delete(assessedRateOne);
		assessedRateRepository.delete(assessedRateTwo);
	}
}
