package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.TaxPayerProfileEntity;

public interface TaxPayerProfileRepository extends PagingAndSortingRepository<TaxPayerProfileEntity, Long> {

	TaxPayerProfileEntity findByTaxPayerProfileKey(String taxPayerProfileKey);
}
