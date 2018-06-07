package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.TaxDefinitionExpenditureCategoryEntity;

public interface TaxDefinitionExpenditureCategoryRepository extends PagingAndSortingRepository<TaxDefinitionExpenditureCategoryEntity, Long> {

	TaxDefinitionExpenditureCategoryEntity findByTaxDefinitionKey(String taxDefinitionKey);
}