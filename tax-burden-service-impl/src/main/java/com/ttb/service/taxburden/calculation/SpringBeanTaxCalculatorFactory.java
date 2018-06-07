package com.ttb.service.taxburden.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanTaxCalculatorFactory  implements TaxCalculatorFactory, ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(SpringBeanTaxCalculatorFactory.class);

	private static ApplicationContext applicationContext;
	
	public TaxCalculator getTaxCalculator(String strategy) {
		TaxCalculator taxCalculator = null;
		try {
			taxCalculator = (TaxCalculator) applicationContext.getBean(strategy);
		} catch (Exception e) {
			logger.error("Could not get TaxCalculator for strategy: " + strategy, e);
		}
		return taxCalculator;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanTaxCalculatorFactory.applicationContext = applicationContext;
	}
}
