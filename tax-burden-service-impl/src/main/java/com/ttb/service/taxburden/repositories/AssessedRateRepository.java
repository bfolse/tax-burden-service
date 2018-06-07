package com.ttb.service.taxburden.repositories;

import com.ttb.service.taxburden.entities.AssessedRateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssessedRateRepository extends PagingAndSortingRepository<AssessedRateEntity, Long>{

	AssessedRateEntity findByTaxDefinitionKey(String taxDefinitionKey);
}
