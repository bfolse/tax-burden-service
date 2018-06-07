package com.ttb.service.taxburden.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.PoliticalDivisionEntity;
import com.ttb.service.taxburden.entities.TaxDefinitionEntity;

public interface TaxDefinitionRepository extends PagingAndSortingRepository<TaxDefinitionEntity, Long> {

	List<TaxDefinitionEntity> findAllByPoliticalDivisionKey(String politicalDivisionKey);
}
