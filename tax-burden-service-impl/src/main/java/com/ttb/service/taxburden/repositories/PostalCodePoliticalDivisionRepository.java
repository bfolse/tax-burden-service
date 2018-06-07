package com.ttb.service.taxburden.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.PostalCodePoliticalDivisionEntity;

public interface PostalCodePoliticalDivisionRepository extends PagingAndSortingRepository<PostalCodePoliticalDivisionEntity, Long> {

	List<PostalCodePoliticalDivisionEntity> findAllByPostalCode(String postalCode);
}
