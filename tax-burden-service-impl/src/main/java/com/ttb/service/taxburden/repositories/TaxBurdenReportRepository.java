package com.ttb.service.taxburden.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;

public interface TaxBurdenReportRepository extends PagingAndSortingRepository<TaxBurdenReportEntity, Long> {

	TaxBurdenReportEntity findByReportKey(String reportKey);
}
