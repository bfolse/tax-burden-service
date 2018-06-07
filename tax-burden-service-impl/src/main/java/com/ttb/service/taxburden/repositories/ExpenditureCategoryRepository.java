package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.ExpenditureCategoryEntity;

public interface ExpenditureCategoryRepository extends PagingAndSortingRepository<ExpenditureCategoryEntity, Long> {

	ExpenditureCategoryEntity findByExpenditureCategoryKey(String politicalDivisionKey);
}
