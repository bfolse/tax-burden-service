package com.ttb.service.taxburden.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expenditure_category")
public class ExpenditureCategoryEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true, nullable=false)
	private String expenditureCategoryKey;
	private String description;
	
	protected ExpenditureCategoryEntity() {}
	
	/**
	 * @param expenditureCategoryKey
	 * @param description
	 */
	public ExpenditureCategoryEntity(String expenditureCategoryKey, String description) {
		super();
		this.expenditureCategoryKey = expenditureCategoryKey;
		this.description = description;
	}

	/**
	 * @return the expenditureCategoryKey
	 */
	public String getExpenditureCategoryKey() {
		return expenditureCategoryKey;
	}

	/**
	 * @param expenditureCategoryKey the expenditureCategoryKey to set
	 */
	public void setExpenditureCategoryKey(String expenditureCategoryKey) {
		this.expenditureCategoryKey = expenditureCategoryKey;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expenditureCategoryKey == null) ? 0 : expenditureCategoryKey.hashCode());
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
		ExpenditureCategoryEntity other = (ExpenditureCategoryEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expenditureCategoryKey == null) {
			if (other.expenditureCategoryKey != null)
				return false;
		} else if (!expenditureCategoryKey.equals(other.expenditureCategoryKey))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpenditureCategoryEntity [id=" + id + ", expenditureCategoryKey=" + expenditureCategoryKey + ", description="
				+ description + "]";
	}

}
