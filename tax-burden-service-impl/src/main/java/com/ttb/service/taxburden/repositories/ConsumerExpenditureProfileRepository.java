package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.ConsumerExpenditureProfileEntity;

public interface ConsumerExpenditureProfileRepository extends PagingAndSortingRepository<ConsumerExpenditureProfileEntity, Long> {

	ConsumerExpenditureProfileEntity findByConsumerExpenditureProfileKey(String consumerExpenditureProfileKey);
}
