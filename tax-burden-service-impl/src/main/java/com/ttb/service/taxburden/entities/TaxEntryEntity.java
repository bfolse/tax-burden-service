package com.ttb.service.taxburden.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.TaxEntry;
import com.ttb.service.taxburden.domain.TaxType;

@Entity
@Table(name="tax_entry")
public class TaxEntryEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private TaxType taxType;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="POLITICAL_DIVISION_ID")
	private PoliticalDivisionEntity politicalDivision;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MonetaryAmountEntity amount;
	
	protected TaxEntryEntity() {}
	
	/**
	 * @param taxType
	 * @param politicalDivisionId
	 * @param politicalDivisionName
	 * @param amount
	 */
	public TaxEntryEntity(TaxType taxType, PoliticalDivisionEntity politicalDivision, MonetaryAmountEntity amount) {
		super();
		this.taxType = taxType;
		this.politicalDivision = politicalDivision;
		this.amount = amount;
	}
	
	public TaxEntryEntity(TaxEntry taxEntry) {
		this(taxEntry.getTaxType(), new PoliticalDivisionEntity(taxEntry.getPoliticalDivision()), new MonetaryAmountEntity(taxEntry.getAmount()));
	}
	
	public TaxEntry toTaxEntry() {
		return new TaxEntry(this.getTaxType(), this.getPoliticalDivision().toPoliticalDivision(), this.getAmount().toMonetaryAmount());
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the taxType
	 */
	public TaxType getTaxType() {
		return taxType;
	}
	
	/**
	 * @return the amount
	 */
	public MonetaryAmountEntity getAmount() {
		return amount;
	}

	/**
	 * @return the politicalDivision
	 */
	public PoliticalDivisionEntity getPoliticalDivision() {
		return politicalDivision;
	}

	/**
	 * @param politicalDivision the politicalDivision to set
	 */
	public void setPoliticalDivision(PoliticalDivisionEntity politicalDivision) {
		this.politicalDivision = politicalDivision;
	}

	/**
	 * @param taxType the taxType to set
	 */
	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(MonetaryAmountEntity amount) {
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
		result = prime * result + ((politicalDivision == null) ? 0 : politicalDivision.hashCode());
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
		TaxEntryEntity other = (TaxEntryEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (politicalDivision == null) {
			if (other.politicalDivision != null)
				return false;
		} else if (!politicalDivision.equals(other.politicalDivision))
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
		return "TaxEntryEntity [id=" + id + ", taxType=" + taxType + ", politicalDivision=" + politicalDivision + ", amount="
				+ amount + "]";
	}

}
