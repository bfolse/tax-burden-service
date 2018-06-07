package com.ttb.service.taxburden.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.TaxPayerProfile;

/**
 * 
 */
@Entity
@Table(name="consumer_expenditure_profile")
public class ConsumerExpenditureProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String consumerExpenditureProfileKey;
	private String description;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="consumer_expenditure_profile_entry",
	        joinColumns=@JoinColumn(name="consumer_expenditure_profile_id"))
	@MapKeyColumn(name="expenditure_category_key")
	@Column(name = "percentage")
	private Map<String, BigDecimal> consumerExpenditureProfileEntries;

	public ConsumerExpenditureProfileEntity() {
		super();
	}

	/**
	 * @param consumerExpenditureProfileKey
	 * @param description
	 * @param consumerExpenditureProfileEntries
	 */
	public ConsumerExpenditureProfileEntity(String consumerExpenditureProfileKey, String description,
			Map<String, BigDecimal> consumerExpenditureProfileEntries) {
		super();
		this.consumerExpenditureProfileKey = consumerExpenditureProfileKey;
		this.description = description;
		this.consumerExpenditureProfileEntries = consumerExpenditureProfileEntries;
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
	 * @return the consumerExpenditureProfileEntries
	 */
	public Map<String, BigDecimal> getConsumerExpenditureProfileEntries() {
		return consumerExpenditureProfileEntries;
	}

	/**
	 * @param consumerExpenditureProfileEntries the consumerExpenditureProfileEntries to set
	 */
	public void setConsumerExpenditureProfileEntries(Map<String, BigDecimal> consumerExpenditureProfileEntries) {
		this.consumerExpenditureProfileEntries = consumerExpenditureProfileEntries;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumerExpenditureProfileEntries == null) ? 0 : consumerExpenditureProfileEntries.hashCode());
		result = prime * result
				+ ((consumerExpenditureProfileKey == null) ? 0 : consumerExpenditureProfileKey.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		ConsumerExpenditureProfileEntity other = (ConsumerExpenditureProfileEntity) obj;
		if (consumerExpenditureProfileEntries == null) {
			if (other.consumerExpenditureProfileEntries != null)
				return false;
		} else if (!consumerExpenditureProfileEntries.equals(other.consumerExpenditureProfileEntries))
			return false;
		if (consumerExpenditureProfileKey == null) {
			if (other.consumerExpenditureProfileKey != null)
				return false;
		} else if (!consumerExpenditureProfileKey.equals(other.consumerExpenditureProfileKey))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsumerExpenditureProfileEntity [id=" + id + ", consumerExpenditureProfileKey="
				+ consumerExpenditureProfileKey + ", description=" + description
				+ ", consumerExpenditureProfileEntries=" + consumerExpenditureProfileEntries + "]";
	}

}
