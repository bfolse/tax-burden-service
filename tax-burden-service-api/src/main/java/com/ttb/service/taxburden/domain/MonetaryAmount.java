package com.ttb.service.taxburden.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class MonetaryAmount {
	private Currency currency;
	private BigDecimal amount;
	
	private final String DEFAULT_CURRENCY_CODE = "USD";
	
	/**
	 * 
	 */
	public MonetaryAmount() {
		super();
	}
	/**
	 * @param currency
	 * @param amount
	 */
	public MonetaryAmount(Currency currency, BigDecimal amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}
	/**
	 * @param amount
	 */
	public MonetaryAmount(BigDecimal amount) {
		super();
		this.currency = Currency.getInstance(DEFAULT_CURRENCY_CODE);
		this.amount = amount;
	}
	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
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
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
		MonetaryAmount other = (MonetaryAmount) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonetaryAmount [currency=" + currency + ", amount=" + amount + "]";
	}

}
