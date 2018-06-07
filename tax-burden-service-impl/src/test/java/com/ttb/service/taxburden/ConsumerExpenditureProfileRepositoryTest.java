package com.ttb.service.taxburden;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttb.service.taxburden.entities.ConsumerExpenditureProfileEntity;
import com.ttb.service.taxburden.entities.MonetaryAmountEntity;
import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;
import com.ttb.service.taxburden.repositories.ConsumerExpenditureProfileRepository;
import com.ttb.service.taxburden.repositories.TaxPayerProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class ConsumerExpenditureProfileRepositoryTest {
	@Autowired
	private ConsumerExpenditureProfileRepository consumerExpenditureProfileRepository;

	private static Map<String, BigDecimal> expenditureProfileEntries() {
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("FOOD_AT_HOME", new BigDecimal(25.0)),
                new AbstractMap.SimpleEntry<>("FOOD_AWAY_FROM_HOME", new BigDecimal(25.0)),
                new AbstractMap.SimpleEntry<>("ALCOHOL", new BigDecimal(10.0)),
                new AbstractMap.SimpleEntry<>("HOUSING_SHELTER", new BigDecimal(40.0)))
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
    }
			
	private static ConsumerExpenditureProfileEntity consumerExpenditureProfileOne = new ConsumerExpenditureProfileEntity("ONE", "profile one",
			expenditureProfileEntries());

	@Test
	public void saveTest() {
		consumerExpenditureProfileRepository.save(consumerExpenditureProfileOne);
		consumerExpenditureProfileRepository.delete(consumerExpenditureProfileOne);
	}
	
	@Test
	public void findByKeyTest() {
		consumerExpenditureProfileRepository.save(consumerExpenditureProfileOne);
		String key = consumerExpenditureProfileOne.getConsumerExpenditureProfileKey();
		ConsumerExpenditureProfileEntity foundConsumerExpenditureProfileEntity = consumerExpenditureProfileRepository.findByConsumerExpenditureProfileKey(key);
		assertNotNull(foundConsumerExpenditureProfileEntity);
		assertEquals(key, foundConsumerExpenditureProfileEntity.getConsumerExpenditureProfileKey());
		assertEquals(4, foundConsumerExpenditureProfileEntity.getConsumerExpenditureProfileEntries().size());
		assertThat(foundConsumerExpenditureProfileEntity.getConsumerExpenditureProfileEntries().keySet()).containsOnly("FOOD_AT_HOME","FOOD_AWAY_FROM_HOME","ALCOHOL","HOUSING_SHELTER");

		consumerExpenditureProfileRepository.delete(consumerExpenditureProfileOne);
	}
}
