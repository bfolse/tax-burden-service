package com.ttb.service.taxburden.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttb.service.taxburden.domain.TaxPayerProfile;
import com.ttb.service.taxburden.impl.TaxBurdenServiceImpl;

/**
 * 
 */
@Controller
@RequestMapping("/tax-payer-profile")
public class TaxPayerProfileController {
    
	@Autowired
	TaxBurdenServiceImpl taxBurdenServiceImpl;
	
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody TaxPayerProfile getTaxPayerProfile(@RequestParam(value="key", required=true) String key) {
    	TaxPayerProfile taxPayerProfile = taxBurdenServiceImpl.findTaxPayerProfileByKey(key);
        return taxPayerProfile;
    }
}
