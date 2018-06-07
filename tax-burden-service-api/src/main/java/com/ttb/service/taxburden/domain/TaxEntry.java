package com.ttb.service.taxburden.domain;


public class TaxEntry {
	private TaxType taxType;
	private PoliticalDivision politicalDivision;
	private MonetaryAmount amount;

	/**
	 * 
	 */
	public TaxEntry() {
		super();
	}

	/**
	 * @param taxType
	 * @param politicalDivision
	 * @param amount
	 */
	public TaxEntry(TaxType taxType, PoliticalDivision politicalDivision, MonetaryAmount amount) {
		super();
		this.taxType = taxType;
		this.politicalDivision = politicalDivision;
		this.amount = amount;
	}

	/**
	 * @return the taxType
	 */
	public TaxType getTaxType() {
		return taxType;
	}

	/**
	 * @param taxType the taxType to set
	 */
	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	/**
	 * @return the politicalDivision
	 */
	public PoliticalDivision getPoliticalDivision() {
		return politicalDivision;
	}

	/**
	 * @param politicalDivision the politicalDivision to set
	 */
	public void setPoliticalDivision(PoliticalDivision politicalDivision) {
		this.politicalDivision = politicalDivision;
	}

	/**
	 * @return the amount
	 */
	public MonetaryAmount getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(MonetaryAmount amount) {
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((politicalDivision == null) ? 0 : politicalDivision.hashCode());
		result = prime * result + ((taxType == null) ? 0 : taxType.hashCode());
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
		TaxEntry other = (TaxEntry) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (politicalDivision == null) {
			if (other.politicalDivision != null)
				return false;
		} else if (!politicalDivision.equals(other.politicalDivision))
			return false;
		if (taxType != other.taxType)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxEntry [taxType=" + taxType + ", politicalDivision=" + politicalDivision + ", amount=" + amount + "]";
	}

}
