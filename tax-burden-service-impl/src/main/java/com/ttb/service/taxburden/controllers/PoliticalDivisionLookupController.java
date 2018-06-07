package com.ttb.service.taxburden.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttb.service.taxburden.impl.TaxBurdenServiceImpl;

@Controller
@RequestMapping("/political-division-lookup")
public class PoliticalDivisionLookupController {
	@Autowired
	TaxBurdenServiceImpl taxBurdenServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<String> politicalDivisionLookup(@RequestParam(value="postalCode") String postalCode) {
		return taxBurdenServiceImpl.findAllPoliticalDivisionsByPostalCode(postalCode);
	}
}
