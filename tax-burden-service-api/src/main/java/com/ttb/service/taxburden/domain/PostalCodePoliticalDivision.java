package com.ttb.service.taxburden.domain;

public class PostalCodePoliticalDivision {
	private String postalCode;
	private String politicalDivisionKey;

	/**
	 * 
	 */
	public PostalCodePoliticalDivision() {
		super();
	}

	/**
	 * @param postalCode
	 * @param politicalDivisionKey
	 */
	public PostalCodePoliticalDivision(String postalCode, String politicalDivisionKey) {
		super();
		this.postalCode = postalCode;
		this.politicalDivisionKey = politicalDivisionKey;
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
	 * @return the politicalDivisionKey
	 */
	public String getPoliticalDivisionKey() {
		return politicalDivisionKey;
	}

	/**
	 * @param politicalDivisionKey the politicalDivisionKey to set
	 */
	public void setPoliticalDivisionKey(String politicalDivisionKey) {
		this.politicalDivisionKey = politicalDivisionKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((politicalDivisionKey == null) ? 0 : politicalDivisionKey.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
		PostalCodePoliticalDivision other = (PostalCodePoliticalDivision) obj;
		if (politicalDivisionKey == null) {
			if (other.politicalDivisionKey != null)
				return false;
		} else if (!politicalDivisionKey.equals(other.politicalDivisionKey))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PostalCodePoliticalDivision [postalCode=" + postalCode + ", politicalDivisionKey="
				+ politicalDivisionKey + "]";
	}

}
