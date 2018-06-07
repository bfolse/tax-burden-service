package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.ExpenditureCategoryGroupEntity;

public interface ExpenditureCategoryGroupRepository extends PagingAndSortingRepository<ExpenditureCategoryGroupEntity, Long> {

	ExpenditureCategoryGroupEntity findByExpenditureCategoryGroupKey(String expenditureCategoryGroupKey);
}
