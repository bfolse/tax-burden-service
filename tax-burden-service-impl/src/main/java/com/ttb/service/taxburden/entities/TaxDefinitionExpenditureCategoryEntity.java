package com.ttb.service.taxburden.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tax_definition_expenditure_category")
public class TaxDefinitionExpenditureCategoryEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable=false)
	private String taxDefinitionKey;
	@Column(nullable=false)
	private String expenditureCategoryGroupKey;
	
	protected TaxDefinitionExpenditureCategoryEntity() {}

	/**
	 * @param taxDefinitionKey
	 * @param expenditureCategoryGroupKey
	 */
	public TaxDefinitionExpenditureCategoryEntity(String taxDefinitionKey, String expenditureCategoryGroupKey) {
		super();
		this.taxDefinitionKey = taxDefinitionKey;
		this.expenditureCategoryGroupKey = expenditureCategoryGroupKey;
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
	 * @return the expenditureCategoryGroupKey
	 */
	public String getExpenditureCategoryGroupKey() {
		return expenditureCategoryGroupKey;
	}

	/**
	 * @param expenditureCategoryGroupKey the expenditureCategoryGroupKey to set
	 */
	public void setExpenditureCategoryGroupKey(String expenditureCategoryGroupKey) {
		this.expenditureCategoryGroupKey = expenditureCategoryGroupKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expenditureCategoryGroupKey == null) ? 0 : expenditureCategoryGroupKey.hashCode());
		result = prime * result + ((taxDefinitionKey == null) ? 0 : taxDefinitionKey.hashCode());
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
		TaxDefinitionExpenditureCategoryEntity other = (TaxDefinitionExpenditureCategoryEntity) obj;
		if (expenditureCategoryGroupKey == null) {
			if (other.expenditureCategoryGroupKey != null)
				return false;
		} else if (!expenditureCategoryGroupKey.equals(other.expenditureCategoryGroupKey))
			return false;
		if (taxDefinitionKey == null) {
			if (other.taxDefinitionKey != null)
				return false;
		} else if (!taxDefinitionKey.equals(other.taxDefinitionKey))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxDefinitionExpenditureCategoryEntity [id=" + id + ", taxDefinitionKey=" + taxDefinitionKey
				+ ", expenditureCategoryGroupKey=" + expenditureCategoryGroupKey + "]";
	}

}
