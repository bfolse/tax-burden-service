package com.ttb.service.taxburden.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A tax rate for either a flat tax assigned to a specific tax definition, or an entry in a TaxRateSetEntity with a low and high range.
 * Low range is exclusive (greater than) and high range is inclusive (less than or equal to).  For an unbounded range, set either
 * high or low to null.
 */
@Entity
@Table(name="tax_rate")
public class TaxRateEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String taxDefinitionKey;
	private BigDecimal rate;
	private Integer rangeLow;
	private Integer rangeHigh;

	public TaxRateEntity() {
		super();
	}

	/**
	 * @param id
	 * @param taxDefinitionKey
	 * @param rate
	 */
	public TaxRateEntity(String taxDefinitionKey, BigDecimal rate) {
		super();
		this.taxDefinitionKey = taxDefinitionKey;
		this.rate = rate;
		this.rangeLow = null;
		this.rangeHigh = null;
	}

	/**
	 * @param id
	 * @param taxDefinitionKey
	 * @param rate
	 * @param rangeLow
	 * @param rangeHigh
	 */
	public TaxRateEntity(String taxDefinitionKey, BigDecimal rate, Integer rangeLow, Integer rangeHigh) {
		super();
		this.taxDefinitionKey = taxDefinitionKey;
		this.rate = rate;
		this.rangeLow = rangeLow;
		this.rangeHigh = rangeHigh;
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

	/**
	 * @return the rangeLow
	 */
	public Integer getRangeLow() {
		return rangeLow;
	}

	/**
	 * @param rangeLow the rangeLow to set
	 */
	public void setRangeLow(Integer rangeLow) {
		this.rangeLow = rangeLow;
	}

	/**
	 * @return the rangeHigh
	 */
	public Integer getRangeHigh() {
		return rangeHigh;
	}

	/**
	 * @param rangeHigh the rangeHigh to set
	 */
	public void setRangeHigh(Integer rangeHigh) {
		this.rangeHigh = rangeHigh;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rangeHigh == null) ? 0 : rangeHigh.hashCode());
		result = prime * result + ((rangeLow == null) ? 0 : rangeLow.hashCode());
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
		TaxRateEntity other = (TaxRateEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rangeHigh == null) {
			if (other.rangeHigh != null)
				return false;
		} else if (!rangeHigh.equals(other.rangeHigh))
			return false;
		if (rangeLow == null) {
			if (other.rangeLow != null)
				return false;
		} else if (!rangeLow.equals(other.rangeLow))
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
		return "TaxRateEntity [id=" + id + ", taxDefinitionKey=" + taxDefinitionKey + ", rate=" + rate + ", rangeLow="
				+ rangeLow + ", rangeHigh=" + rangeHigh + "]";
	}


}
