package com.ttb.service.taxburden.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The percentage of market value used for the calculation of property taxes
 */
@Entity
@Table(name="assessed_rate")
public class AssessedRateEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String taxDefinitionKey;
	private BigDecimal rate;

	public AssessedRateEntity() {
		super();
	}

	/**
	 * @param id
	 * @param taxDefinitionKey
	 * @param rate
	 */
	public AssessedRateEntity(String taxDefinitionKey, BigDecimal rate) {
		super();
		this.taxDefinitionKey = taxDefinitionKey;
		this.rate = rate;
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
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		AssessedRateEntity other = (AssessedRateEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
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
		return "TaxRateEntity [id=" + id + ", taxDefinitionKey=" + taxDefinitionKey + ", rate=" + rate + "]";
	}


}
