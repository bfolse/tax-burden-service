package com.ttb.service.taxburden.entities;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.MathContext;

import com.ttb.service.taxburden.domain.MonetaryAmount;

@Entity
@Table(name="monetary_amount")
public class MonetaryAmountEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Currency currency;
	private BigDecimal amount;
	
	@Transient
	private final String DEFAULT_CURRENCY_CODE = "USD";
	@Transient
	private final MathContext PRECISION_TWO = new MathContext(2);

	protected MonetaryAmountEntity() {}
	/**
	 * @param currency
	 * @param amount
	 */
	public MonetaryAmountEntity(Currency currency, BigDecimal amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}

	/**
	 * @param amount
	 */
	public MonetaryAmountEntity(BigDecimal amount) {
		super();
		this.currency = Currency.getInstance(DEFAULT_CURRENCY_CODE);
		this.amount = amount;
	}

	/**
	 * @param amount
	 */
	public MonetaryAmountEntity(double amount) {
		super();
		this.currency = Currency.getInstance(DEFAULT_CURRENCY_CODE);
		this.amount = new BigDecimal(amount, PRECISION_TWO);
	}

	public MonetaryAmountEntity(MonetaryAmount monetaryAmount) {
		this(monetaryAmount.getAmount());
	}
	
	public MonetaryAmount toMonetaryAmount() {
		return new MonetaryAmount(this.getCurrency(), this.amount);
	}
	
	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.getNumericCode());
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
		MonetaryAmountEntity other = (MonetaryAmountEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.getCurrencyCode().equals(other.currency.getCurrencyCode()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonetaryAmountEntity [currency=" + currency + ", amount=" + amount + "]";
	}
	
}
