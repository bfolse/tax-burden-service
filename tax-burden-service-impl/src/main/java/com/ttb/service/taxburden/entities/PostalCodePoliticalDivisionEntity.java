package com.ttb.service.taxburden.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.PostalCodePoliticalDivision;


@Entity
@Table(name="postal_code_political_division")
public class PostalCodePoliticalDivisionEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String postalCode;
	@Column(nullable=false)
	private String politicalDivisionKey;
	
	protected PostalCodePoliticalDivisionEntity() {}

	/**
	 * @param postalCode
	 * @param politicalDivisionKey
	 */
	public PostalCodePoliticalDivisionEntity(String postalCode, String politicalDivisionKey) {
		super();
		this.postalCode = postalCode;
		this.politicalDivisionKey = politicalDivisionKey;
	}

	public PostalCodePoliticalDivisionEntity(PostalCodePoliticalDivision postalCodePoliticalDivision) {
		this(postalCodePoliticalDivision.getPostalCode(), postalCodePoliticalDivision.getPoliticalDivisionKey());
	}
	
	public PostalCodePoliticalDivision toPostalCodePoliticalDivision() {
		return new PostalCodePoliticalDivision(this.getPostalCode(), this.getPoliticalDivisionKey());
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
		PostalCodePoliticalDivisionEntity other = (PostalCodePoliticalDivisionEntity) obj;
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
		return "PostalCodePoliticalDivisionEntity [id=" + id + ", postalCode=" + postalCode + ", politicalDivisionKey="
				+ politicalDivisionKey + "]";
	}

}
