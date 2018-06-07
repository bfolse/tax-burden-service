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

import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.PostalCodePoliticalDivisionEntity;
import com.ttb.service.taxburden.repositories.PoliticalDivisionRepository;
import com.ttb.service.taxburden.repositories.PostalCodePoliticalDivisionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })

public class PostalCodePoliticalDivisionRepositoryTest {
	
	@Autowired
	private PostalCodePoliticalDivisionRepository repository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	private PostalCodePoliticalDivisionEntity politicalDivision30306StateGA = new PostalCodePoliticalDivisionEntity("30306", "13");
	private PostalCodePoliticalDivisionEntity politicalDivision30306CountyDekalb = new PostalCodePoliticalDivisionEntity("30306", "13089");
	private PostalCodePoliticalDivisionEntity politicalDivision30306CountyFulton = new PostalCodePoliticalDivisionEntity("30306", "13121");
	private PostalCodePoliticalDivisionEntity PostalCodePoliticalDivision31515CountyAppling = new PostalCodePoliticalDivisionEntity("31515", "13001");
	private PostalCodePoliticalDivisionEntity PostalCodePoliticalDivision31513CountyAppling = new PostalCodePoliticalDivisionEntity("31513", "13001");
	
	@Before
	public void setup() {
		entityManager.persist(politicalDivision30306StateGA);
		entityManager.persist(politicalDivision30306CountyDekalb);
		entityManager.persist(politicalDivision30306CountyFulton);
		entityManager.persist(PostalCodePoliticalDivision31515CountyAppling);
	}
	
	@Test
	public void findAllTest() {
		assertThat(repository.findAll()).hasSize(4).extracting("politicalDivisionKey").containsOnly("13","13089","13121","13001");
	}

	@Test
	public void countTest() {
		assertEquals(4, repository.count());
	}
	
	@Test
	public void saveTest() {
		PostalCodePoliticalDivisionEntity savedPostalCodePoliticalDivision31513CountyAppling = repository.save(PostalCodePoliticalDivision31513CountyAppling);
		System.out.println("savedPostalCodePoliticalDivision31513CountyAppling: " + savedPostalCodePoliticalDivision31513CountyAppling);
	}
	
	@Test
	public void findAllByPostalCodeTest() {
		assertThat(repository.findAllByPostalCode("30306")).hasSize(3).extracting("politicalDivisionKey").containsOnly("13","13089","13121");
	}
}
