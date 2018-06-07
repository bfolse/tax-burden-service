package com.ttb.service.taxburden.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttb.service.taxburden.domain.TaxBurdenReport;
import com.ttb.service.taxburden.domain.TaxPayerProfile;
import com.ttb.service.taxburden.entities.TaxBurdenReportEntity;
import com.ttb.service.taxburden.impl.TaxBurdenServiceImpl;

/**
 * 
 */
@Controller
@RequestMapping("/tax-burden")
public class TaxBurdenReportController {
    
	@Autowired
	TaxBurdenServiceImpl taxBurdenServiceImpl;
	
	// TODO Do this with a GUID or sequence
	// TEMP
	private final AtomicLong reportCounter = new AtomicLong();
	private final AtomicLong taxPayerCounter = new AtomicLong();
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody TaxBurdenReport getReport(@RequestParam(value="key", required=true) String key) {
    	TaxBurdenReport taxBurdenReport = taxBurdenServiceImpl.findTaxBurdenReportByKey(key);

    	return taxBurdenReport;
    }

    // Generate report from POST
    @RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
    public @ResponseBody TaxBurdenReport createReport(@RequestBody TaxPayerProfile taxPayerProfile) {
    	// TODO Validate taxPayerProfile
    	
    	TaxBurdenReport taxBurdenReport = taxBurdenServiceImpl.createReport(taxPayerProfile);
    	
    	return taxBurdenReport;
    }
   
}
