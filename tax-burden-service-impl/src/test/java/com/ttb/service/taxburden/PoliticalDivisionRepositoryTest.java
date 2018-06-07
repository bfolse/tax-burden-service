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
import com.ttb.service.taxburden.repositories.PoliticalDivisionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })

public class PoliticalDivisionRepositoryTest {
	
	@Autowired
	private PoliticalDivisionRepository repository;
	
    @Autowired
    private TestEntityManager entityManager;
    
	private PoliticalDivisionEntity politicalDivisionStateAL = new PoliticalDivisionEntity("01", "AL", "Alabama", "STATE");
	private PoliticalDivisionEntity politicalDivisionCountyAutauga = new PoliticalDivisionEntity("001", "Autauga County", "Autauga County", "COUNTY");
	
	@Before
	public void setup() {
		entityManager.persist(new PoliticalDivisionEntity("13", "GA", "Georgia", "STATE"));
		entityManager.persist(new PoliticalDivisionEntity("089", "Dekalb", "Dekalb County", "COUNTY"));
	}
	
	@Test
	public void findAllTest() {
		assertThat(repository.findAll()).hasSize(2).extracting("fips").containsOnly("13","089");
	}

	@Test
	public void countTest() {
		assertEquals(2, repository.count());
	}
	
	@Test
	public void saveTest() {
		PoliticalDivisionEntity savedPoliticalDivisionStateAL = repository.save(politicalDivisionStateAL);
		PoliticalDivisionEntity savedPoliticalDivisionCountyAutauga = repository.save(politicalDivisionCountyAutauga);
		System.out.println("savedPoliticalDivisionStateAL: " + savedPoliticalDivisionStateAL);
		System.out.println("savedPoliticalDivisionCountyAutauga: " + savedPoliticalDivisionCountyAutauga);
	}
	
	@Test
	public void findByFipsTest() {
		assertThat(((PoliticalDivisionEntity)repository.findByFips("13")).getFips().equals("13"));
	}
}
