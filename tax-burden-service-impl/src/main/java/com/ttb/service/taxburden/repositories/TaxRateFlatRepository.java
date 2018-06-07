package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.TaxRateEntity;

public interface TaxRateFlatRepository  extends PagingAndSortingRepository<TaxRateEntity, Long>{

	TaxRateEntity findByTaxDefinitionKey(String taxDefinitionKey);
}
