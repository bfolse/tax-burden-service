package com.ttb.service.taxburden;

import com.ttb.service.taxburden.calculation.TaxCalculatorFactory;
import com.ttb.service.taxburden.domain.MonetaryAmount;
import com.ttb.service.taxburden.domain.TaxBurdenReport;
import com.ttb.service.taxburden.domain.TaxPayerProfile;
import com.ttb.service.taxburden.impl.TaxBurdenServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class TaxBurdenServiceImplTest {
	@Autowired
	private TaxBurdenServiceImpl taxBurdenServiceImpl;

	@MockBean 
	private TaxCalculatorFactory mockTaxCalculatorFactory;
	
	@Test
	public void createReportTest() {
        given(this.mockTaxCalculatorFactory.getTaxCalculator(""))
        .willReturn(new MockTaxCalculator());
		taxBurdenServiceImpl.setTaxCalculatorFactory(mockTaxCalculatorFactory);

        ArrayList<String> politicalDivisionKeys = new ArrayList<String>();
		politicalDivisionKeys.add("13");
		TaxPayerProfile taxPayerProfile = taxBurdenServiceImpl.createTaxPayerProfile("30306", politicalDivisionKeys, new MonetaryAmount(new BigDecimal(100000.00)), new MonetaryAmount(new BigDecimal(5000.00)), new MonetaryAmount(new BigDecimal(100000.00)));
		TaxBurdenReport taxBurdenReport = taxBurdenServiceImpl.createReport(taxPayerProfile);
		assertNotNull(taxBurdenReport);
		System.out.println(taxBurdenReport);
	}
}
