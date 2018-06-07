package com.ttb.service.taxburden.entities;

import com.ttb.service.taxburden.domain.TaxPayerProfile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 */
@Entity
@Table(name="tax_payer_profile")
public class TaxPayerProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String taxPayerProfileKey;
	private Date timestamp;
	private String postalCode;
	@ElementCollection
	@CollectionTable(name="tax_payer_profile_political_division_keys",
	        joinColumns=@JoinColumn(name="tax_payer_profile_id"))
	private List<String> politicalDivisionKeys;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MonetaryAmountEntity annualIncome;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MonetaryAmountEntity mortgageInterest;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MonetaryAmountEntity realPropertyMarketValue;
	private String consumerExpenditureProfileKey;

	/**
	 * 
	 */
	public TaxPayerProfileEntity() {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.politicalDivisionKeys = new ArrayList<String>();
		this.consumerExpenditureProfileKey = "BASIC";
	}

	/**
	 * @param postalCode
	 * @param annualIncome
	 * @param mortgageInterest
	 */
	public TaxPayerProfileEntity(String postalCode, MonetaryAmountEntity annualIncome, MonetaryAmountEntity mortgageInterest) {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.politicalDivisionKeys = new ArrayList<String>();
		this.postalCode = postalCode;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.consumerExpenditureProfileKey = "BASIC";
	}

	/**
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 */
	public TaxPayerProfileEntity(String postalCode, List<String> politicalDivisionKeys, MonetaryAmountEntity annualIncome, MonetaryAmountEntity mortgageInterest) {
		super();
		this.timestamp = new Date();
		this.taxPayerProfileKey = UUID.randomUUID().toString();
		this.postalCode = postalCode;
		this.politicalDivisionKeys = politicalDivisionKeys;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.consumerExpenditureProfileKey = "BASIC";
	}

	/**
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 * @param consumerExpenditureProfileKey
	 */
	public TaxPayerProfileEntity(String postalCode, List<String> politicalDivisionKeys,	MonetaryAmountEntity annualIncome, 
			MonetaryAmountEntity mortgageInterest, String consumerExpenditureProfileKey) {
		super();
		this.postalCode = postalCode;
		this.politicalDivisionKeys = politicalDivisionKeys;
		this.annualIncome = annualIncome;
		this.mortgageInterest = mortgageInterest;
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
	}

	/**
	 * @param postalCode
	 * @param politicalDivisionKeys
	 * @param annualIncome
	 * @param mortgageInterest
	 * @param realPropertyMarketValue
	 * @param consumerExpenditureProfileKey
	 */
	public TaxPayerProfileEntity(String postalCode, List<String> politicalDivisionKeys,	MonetaryAmountEntity annualIncome,
								 MonetaryAmountEntity mortgageInterest, MonetaryAmountEntity realPropertyMarketValue, String consumerExpenditureProfileKey) {
		super();
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
	public TaxPayerProfileEntity(String taxPayerProfileKey, Date timestamp, String postalCode,
			List<String> politicalDivisionKeys, MonetaryAmountEntity annualIncome,
			MonetaryAmountEntity mortgageInterest, String consumerExpenditureProfileKey) {
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
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
	}

	public TaxPayerProfileEntity(String taxPayerProfileKey, Date timestamp, String postalCode, List<String> politicalDivisionKeys, MonetaryAmountEntity annualIncome, MonetaryAmountEntity mortgageInterest, MonetaryAmountEntity realPropertyMarketValue, String consumerExpenditureProfileKey) {
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

	public TaxPayerProfileEntity(TaxPayerProfile taxPayerProfile) {
		this(taxPayerProfile.getPostalCode(), taxPayerProfile.getPoliticalDivisionKeys(), new MonetaryAmountEntity(taxPayerProfile.getAnnualIncome()), new MonetaryAmountEntity(taxPayerProfile.getMortgageInterest()), new MonetaryAmountEntity(taxPayerProfile.getRealPropertyMarketValue()), taxPayerProfile.getConsumerExpenditureProfileKey());
	}
	
	public TaxPayerProfile toTaxPayerProfile() {
		return new TaxPayerProfile(this.getTaxPayerProfileKey(), this.getTimestamp(), this.getPostalCode(), this.getPoliticalDivisionKeys(), this.getAnnualIncome().toMonetaryAmount(), this.getMortgageInterest().toMonetaryAmount(), this.getRealPropertyMarketValue().toMonetaryAmount(), this.getConsumerExpenditureProfileKey());
	}
	
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the politicalDivisionKeys
	 */
	public List<String> getPoliticalDivisionKeys() {
		return politicalDivisionKeys;
	}

	/**
	 * @return the annualIncome
	 */
	public MonetaryAmountEntity getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @return the mortgageInterest
	 */
	public MonetaryAmountEntity getMortgageInterest() {
		return mortgageInterest;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param politicalDivisionKeys the politicalDivisionKeys to set
	 */
	public void setPoliticalDivisionKeys(List<String> politicalDivisionKeys) {
		this.politicalDivisionKeys = politicalDivisionKeys;
	}

	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(MonetaryAmountEntity annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @param mortgageInterest the mortgageInterest to set
	 */
	public void setMortgageInterest(MonetaryAmountEntity mortgageInterest) {
		this.mortgageInterest = mortgageInterest;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the taxPayerProfileKey
	 */
	public String getTaxPayerProfileKey() {
		return taxPayerProfileKey;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return true if collection was changed
	 */
	public boolean addPoliticalDivisionKey(String politicalDivisionKey) {
		return this.politicalDivisionKeys.add(politicalDivisionKey);
	}
	
	/**
	 * @return true if collection was changed
	 * 
	 */
	public boolean addPoliticalDivisionKeys(List<String> politicalDivisionKeys) {
		return this.politicalDivisionKeys.addAll(politicalDivisionKeys);
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

	public MonetaryAmountEntity getRealPropertyMarketValue() {
		return realPropertyMarketValue;
	}

	public void setRealPropertyMarketValue(MonetaryAmountEntity realPropertyMarketValue) {
		this.realPropertyMarketValue = realPropertyMarketValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annualIncome == null) ? 0 : annualIncome.hashCode());
		result = prime * result
				+ ((consumerExpenditureProfileKey == null) ? 0 : consumerExpenditureProfileKey.hashCode());
		result = prime * result + ((mortgageInterest == null) ? 0 : mortgageInterest.hashCode());
		result = prime * result + ((politicalDivisionKeys == null) ? 0 : politicalDivisionKeys.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((taxPayerProfileKey == null) ? 0 : taxPayerProfileKey.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxPayerProfileEntity other = (TaxPayerProfileEntity) obj;
		if (annualIncome == null) {
			if (other.annualIncome != null)
				return false;
		} else if (!annualIncome.equals(other.annualIncome))
			return false;
		if (consumerExpenditureProfileKey == null) {
			if (other.consumerExpenditureProfileKey != null)
				return false;
		} else if (!consumerExpenditureProfileKey.equals(other.consumerExpenditureProfileKey))
			return false;
		if (mortgageInterest == null) {
			if (other.mortgageInterest != null)
				return false;
		} else if (!mortgageInterest.equals(other.mortgageInterest))
			return false;
		if (politicalDivisionKeys == null) {
			if (other.politicalDivisionKeys != null)
				return false;
		} else if (!politicalDivisionKeys.equals(other.politicalDivisionKeys))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (taxPayerProfileKey == null) {
			if (other.taxPayerProfileKey != null)
				return false;
		} else if (!taxPayerProfileKey.equals(other.taxPayerProfileKey))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxPayerProfileEntity [id=" + id + ", taxPayerProfileKey=" + taxPayerProfileKey + ", timestamp="
				+ timestamp + ", postalCode=" + postalCode + ", politicalDivisionKeys=" + politicalDivisionKeys
				+ ", annualIncome=" + annualIncome + ", mortgageInterest=" + mortgageInterest
				+ ", consumerExpenditureProfileKey=" + consumerExpenditureProfileKey + "]";
	}

}
