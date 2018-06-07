package com.ttb.service.taxburden.calculation;

public interface TaxCalculatorFactory {
	public TaxCalculator getTaxCalculator(String key);
}
