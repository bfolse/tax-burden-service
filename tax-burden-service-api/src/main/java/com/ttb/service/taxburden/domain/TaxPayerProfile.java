package com.ttb.service.taxburden.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaxPayerProfile {

	public static final String BASIC_CONSUMER_EXPENDITURE_PROFILE_KEY = "BASIC";
	
	private String taxPayerProfileKey;
	private Date timestamp;
	private String postalCode;
	private List<String> politicalDivisionKeys;
	private MonetaryAmount annualIncome;
	private MonetaryAmount mortgageInterest;
	private MonetaryAmount realPropertyMarketValue;
	private String consumerExpenditureProfileKey;

	/**
	 * 
	 */
	public TaxPayerProfile() {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.consumerExpenditureProfileKey = BASIC_CONSUMER_EXPENDITURE_PROFILE_KEY;
	}

	/**
	 * @param taxPayerProfileKey
	 * @param timestamp
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 */
	public TaxPayerProfile(String postalCode, List<String> politicalDivisionKeys, MonetaryAmount annualIncome, MonetaryAmount mortgageInterest, MonetaryAmount realPropertyMarketValue) {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.postalCode = postalCode;
		this.politicalDivisionKeys = politicalDivisionKeys;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.realPropertyMarketValue = realPropertyMarketValue;
		this.consumerExpenditureProfileKey = BASIC_CONSUMER_EXPENDITURE_PROFILE_KEY;
	}

	/**
	 * @param taxPayerProfileKey
	 * @param timestamp
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 */
	public TaxPayerProfile(String postalCode, List<String> politicalDivisionKeys, MonetaryAmount annualIncome, MonetaryAmount mortgageInterest, MonetaryAmount realPropertyMarketValue, String consumerExpenditureProfileKey) {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.postalCode = postalCode;
		this.politicalDivisionKeys = politicalDivisionKeys;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.realPropertyMarketValue = realPropertyMarketValue;
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
	}

	/**
	 * @param taxPayerProfileKey
	 * @param timestamp
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 */
	public TaxPayerProfile(String taxPayerProfileKey, Date timestamp, String postalCode,
			List<String> politicalDivisionKeys, MonetaryAmount annualIncome, MonetaryAmount mortgageInterest, MonetaryAmount realPropertyMarketValue, String consumerExpenditureProfileKey) {
		super();
		if (taxPayerProfileKey == null) {
			this.taxPayerProfileKey = UUID.randomUUID().toString();
		} else {
			this.taxPayerProfileKey = taxPayerProfileKey;
		}
		if (timestamp == null) {
			this.timestamp = new Date();
		} else {
			this.timestamp = timestamp;
		}
		this.postalCode = postalCode;
		this.politicalDivisionKeys = politicalDivisionKeys;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.realPropertyMarketValue = realPropertyMarketValue;
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
	}

	/**
	 * @return the taxPayerProfileKey
	 */
	public String getTaxPayerProfileKey() {
		return taxPayerProfileKey;
	}

	/**
	 * @param taxPayerProfileKey the taxPayerProfileKey to set
	 */
	public void setTaxPayerProfileKey(String taxPayerProfileKey) {
		this.taxPayerProfileKey = taxPayerProfileKey;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the politicalDivisionKeys
	 */
	public List<String> getPoliticalDivisionKeys() {
		return politicalDivisionKeys;
	}

	/**
	 * @param politicalDivisionKeys the politicalDivisionKeys to set
	 */
	public void setPoliticalDivisionKeys(List<String> politicalDivisionKeys) {
		this.politicalDivisionKeys = politicalDivisionKeys;
	}

	/**
	 * @return the annualIncome
	 */
	public MonetaryAmount getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(MonetaryAmount annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the mortgageInterest
	 */
	public MonetaryAmount getMortgageInterest() {
		return mortgageInterest;
	}

	/**
	 * @param mortgageInterest the mortgageInterest to set
	 */
	public void setMortgageInterest(MonetaryAmount mortgageInterest) {
		this.mortgageInterest = mortgageInterest;
	}

	/**
	 * @return the realPropertyMarketValue
	 */
	public MonetaryAmount getRealPropertyMarketValue() {
		return realPropertyMarketValue;
	}

	/**
	 * @param realPropertyMarketValue the realPropertyMarketValue to set
	 */
	public void setRealPropertyMarketValue(MonetaryAmount realPropertyMarketValue) {
		this.realPropertyMarketValue = realPropertyMarketValue;
	}

	/**
	 * @return the consumerExpenditureProfileKey
	 */
	public String getConsumerExpenditureProfileKey() {
		return consumerExpenditureProfileKey;
	}

	/**
	 * @param consumerExpenditureProfileKey the consumerExpenditureProfileKey to set
	 */
	public void setConsumerExpenditureProfileKey(String consumerExpenditureProfileKey) {
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;

		TaxPayerProfile that = (TaxPayerProfile) object;

		if (taxPayerProfileKey != null ? !taxPayerProfileKey.equals(that.taxPayerProfileKey) : that.taxPayerProfileKey != null)
			return false;
		if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
		if (politicalDivisionKeys != null ? !politicalDivisionKeys.equals(that.politicalDivisionKeys) : that.politicalDivisionKeys != null)
			return false;
		if (annualIncome != null ? !annualIncome.equals(that.annualIncome) : that.annualIncome != null) return false;
		if (mortgageInterest != null ? !mortgageInterest.equals(that.mortgageInterest) : that.mortgageInterest != null)
			return false;
		if (realPropertyMarketValue != null ? !realPropertyMarketValue.equals(that.realPropertyMarketValue) : that.realPropertyMarketValue != null)
			return false;
		if (consumerExpenditureProfileKey != null ? !consumerExpenditureProfileKey.equals(that.consumerExpenditureProfileKey) : that.consumerExpenditureProfileKey != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (taxPayerProfileKey != null ? taxPayerProfileKey.hashCode() : 0);
		result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
		result = 31 * result + (politicalDivisionKeys != null ? politicalDivisionKeys.hashCode() : 0);
		result = 31 * result + (annualIncome != null ? annualIncome.hashCode() : 0);
		result = 31 * result + (mortgageInterest != null ? mortgageInterest.hashCode() : 0);
		result = 31 * result + (realPropertyMarketValue != null ? realPropertyMarketValue.hashCode() : 0);
		result = 31 * result + (consumerExpenditureProfileKey != null ? consumerExpenditureProfileKey.hashCode() : 0);
		return result;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "TaxPayerProfile{" +
				"taxPayerProfileKey='" + taxPayerProfileKey + '\'' +
				", timestamp=" + timestamp +
				", postalCode='" + postalCode + '\'' +
				", politicalDivisionKeys=" + politicalDivisionKeys +
				", annualIncome=" + annualIncome +
				", mortgageInterest=" + mortgageInterest +
				", realPropertyMarketValue=" + realPropertyMarketValue +
				", consumerExpenditureProfileKey='" + consumerExpenditureProfileKey + '\'' +
				'}';
	}
}
