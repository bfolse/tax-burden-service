package com.ttb.service.taxburden.entities;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="expenditure_category_group")
public class ExpenditureCategoryGroupEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable=false)
	private String expenditureCategoryGroupKey;
	private String description;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="expenditure_category_group_expenditure_category_key",
	        joinColumns=@JoinColumn(name="expenditure_category_group_id"))
	private List<String> expenditureCategoryKeys;
	
	protected ExpenditureCategoryGroupEntity() {}

	/**
	 * @param expenditureCategoryGroupKey
	 * @param expenditureCategoryKeys
	 */
	public ExpenditureCategoryGroupEntity(String expenditureCategoryGroupKey, String description, List<String> expenditureCategoryKeys) {
		super();
		this.expenditureCategoryGroupKey = expenditureCategoryGroupKey;
		this.expenditureCategoryKeys = expenditureCategoryKeys;
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

	/**
	 * @return the expenditureCategoryKeys
	 */
	public List<String> getExpenditureCategoryKeys() {
		return expenditureCategoryKeys;
	}

	/**
	 * @param expenditureCategoryKeys the expenditureCategoryKeys to set
	 */
	public void setExpenditureCategoryKeys(List<String> expenditureCategoryKeys) {
		this.expenditureCategoryKeys = expenditureCategoryKeys;
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
		result = prime * result + ((expenditureCategoryGroupKey == null) ? 0 : expenditureCategoryGroupKey.hashCode());
		result = prime * result + ((expenditureCategoryKeys == null) ? 0 : expenditureCategoryKeys.hashCode());
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
		ExpenditureCategoryGroupEntity other = (ExpenditureCategoryGroupEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expenditureCategoryGroupKey == null) {
			if (other.expenditureCategoryGroupKey != null)
				return false;
		} else if (!expenditureCategoryGroupKey.equals(other.expenditureCategoryGroupKey))
			return false;
		if (expenditureCategoryKeys == null) {
			if (other.expenditureCategoryKeys != null)
				return false;
		} else if (!expenditureCategoryKeys.equals(other.expenditureCategoryKeys))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpenditureCategoryGroupEntity [id=" + id + ", expenditureCategoryGroupKey="
				+ expenditureCategoryGroupKey + ", description=" + description + ", expenditureCategoryKeys="
				+ expenditureCategoryKeys + "]";
	}

}
