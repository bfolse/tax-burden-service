package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;

public interface PoliticalDivisionRepository extends PagingAndSortingRepository<PoliticalDivisionEntity, Long> {

	PoliticalDivisionEntity findByFips(String fips);
}
