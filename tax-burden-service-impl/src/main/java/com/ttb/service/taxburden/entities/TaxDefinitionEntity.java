package com.ttb.service.taxburden.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.TaxType;

@Entity
@Table(name="tax_definition")
public class TaxDefinitionEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private TaxType taxType;
	String politicalDivisionKey;
	private String taxCalcStrategy;
	@Column(unique=true, nullable=false)
	private String taxDefinitionKey;
	private String description;
	private int ordinal;
	
	protected TaxDefinitionEntity() {}
	
	/**
	 * @param taxType
	 * @param politicalDivision
	 * @param taxCalcStrategy
	 * @param taxDefinitionKey
	 * @param description
	 * @param ordinal
	 */
	public TaxDefinitionEntity(TaxType taxType, String politicalDivisionKey, String taxCalcStrategy, String name, String description, int ordinal) {
		super();
		this.taxType = taxType;
		this.politicalDivisionKey = politicalDivisionKey;
		this.taxCalcStrategy = taxCalcStrategy;
		this.taxDefinitionKey = name;
		this.description = description;
		this.ordinal = ordinal;
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
	 * @return the taxCalcStrategy
	 */
	public String getTaxCalcStrategy() {
		return taxCalcStrategy;
	}

	/**
	 * @param taxCalcStrategy the taxCalcStrategy to set
	 */
	public void setTaxCalcStrategy(String taxCalcStrategy) {
		this.taxCalcStrategy = taxCalcStrategy;
	}

	/**
	 * @return the taxDefinitionKey
	 */
	public String getTaxDefinitionKey() {
		return taxDefinitionKey;
	}

	/**
	 * @param taxDefinitionKey the taxDefinitionKey to set
	 */
	public void setTaxDefinitionKey(String taxDefinitionKey) {
		this.taxDefinitionKey = taxDefinitionKey;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the ordinal
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal the ordinal to set
	 */
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ordinal;
		result = prime * result + ((politicalDivisionKey == null) ? 0 : politicalDivisionKey.hashCode());
		result = prime * result + ((taxCalcStrategy == null) ? 0 : taxCalcStrategy.hashCode());
		result = prime * result + ((taxDefinitionKey == null) ? 0 : taxDefinitionKey.hashCode());
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
		TaxDefinitionEntity other = (TaxDefinitionEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ordinal != other.ordinal)
			return false;
		if (politicalDivisionKey == null) {
			if (other.politicalDivisionKey != null)
				return false;
		} else if (!politicalDivisionKey.equals(other.politicalDivisionKey))
			return false;
		if (taxCalcStrategy == null) {
			if (other.taxCalcStrategy != null)
				return false;
		} else if (!taxCalcStrategy.equals(other.taxCalcStrategy))
			return false;
		if (taxDefinitionKey == null) {
			if (other.taxDefinitionKey != null)
				return false;
		} else if (!taxDefinitionKey.equals(other.taxDefinitionKey))
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
		return "TaxDefinitionEntity [id=" + id + ", taxType=" + taxType + ", politicalDivisionKey="
				+ politicalDivisionKey + ", taxCalcStrategy=" + taxCalcStrategy + ", taxDefinitionKey="
				+ taxDefinitionKey + ", description=" + description + ", ordinal=" + ordinal + "]";
	}

}
