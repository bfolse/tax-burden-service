package com.ttb.service.taxburden.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.TaxBurdenReport;
import com.ttb.service.taxburden.domain.TaxEntry;
import com.ttb.service.taxburden.domain.TaxType;

/**
 * 
 */
@Entity
@Table(name="tax_burden_report")
public class TaxBurdenReportEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String reportKey;
    private Date timestamp;
    @OneToOne
    @JoinColumn(name="TAX_PAYER_PROFILE_ID")
    private TaxPayerProfileEntity taxPayerProfile;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable
    (
        name="tax_burden_report_tax_entries",
        joinColumns={ @JoinColumn(name="tax_burden_report_id", referencedColumnName="id") },
        inverseJoinColumns={ @JoinColumn(name="tax_entries_id", referencedColumnName="id", unique=true) }
    )
    private List<TaxEntryEntity> taxEntries;

	/**
	 * @param taxPayerProfile
	 * @param taxEntries
	 */
	public TaxBurdenReportEntity(TaxPayerProfileEntity taxPayerProfile, List<TaxEntryEntity> taxEntries) {
		super();
		this.reportKey = UUID.randomUUID().toString();
		this.timestamp = new Date();
		this.taxPayerProfile = taxPayerProfile;
		this.taxEntries = taxEntries;
	}

	/**
	 * @param taxPayerId
	 */
	public TaxBurdenReportEntity(TaxPayerProfileEntity taxPayerProfile) {
		super();
		this.reportKey = UUID.randomUUID().toString();
		this.timestamp = new Date();
		this.taxPayerProfile = taxPayerProfile;
		this.taxEntries = new ArrayList<TaxEntryEntity>();
	}

	/**
	 * 
	 */
	public TaxBurdenReportEntity() {
		super();
		this.reportKey = UUID.randomUUID().toString();
		this.timestamp = new Date();
	}

	public TaxBurdenReportEntity(TaxBurdenReport taxBurdenReport) {
		this(new TaxPayerProfileEntity(taxBurdenReport.getTaxPayerProfile()), TaxBurdenReportEntity.taxEntryListToTaxEntryEntityList(taxBurdenReport.getTaxEntries()));
	}
	
	public TaxBurdenReport toTaxBurdenReport() {
		return new TaxBurdenReport(this.getReportKey(), this.getTimestamp(), this.getTaxPayerProfile().toTaxPayerProfile(), TaxBurdenReportEntity.taxEntryEntityListToTaxEntryList(this.getTaxEntries()));
	}
	
	private static List<TaxEntryEntity> taxEntryListToTaxEntryEntityList(List<TaxEntry> taxEntries) {
		List<TaxEntryEntity> taxEntryEntities = new ArrayList<TaxEntryEntity>();
		for (TaxEntry taxEntry : taxEntries) {
			taxEntryEntities.add(new TaxEntryEntity(taxEntry));
		}
		return taxEntryEntities;
	}
	
	private static List<TaxEntry> taxEntryEntityListToTaxEntryList(List<TaxEntryEntity> taxEntryEntities) {
		List<TaxEntry> taxEntries = new ArrayList<TaxEntry>();
		for (TaxEntryEntity taxEntryEntity : taxEntryEntities) {
			taxEntries.add(taxEntryEntity.toTaxEntry());
		}
		return taxEntries;
		
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the reportKey
	 */
	public String getReportKey() {
		return reportKey;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the taxPayerProfile
	 */
	public TaxPayerProfileEntity getTaxPayerProfile() {
		return taxPayerProfile;
	}

	/**
	 * @param taxPayerProfile the taxPayerProfile to set
	 */
	public void setTaxPayerProfile(TaxPayerProfileEntity taxPayerProfile) {
		this.taxPayerProfile = taxPayerProfile;
	}

	/**
	 * @return the taxEntries
	 */
	public List<TaxEntryEntity> getTaxEntries() {
		return taxEntries;
	}

	/**
	 * @param taxType
	 * @param politicalDivisionKey
	 * @return
	 */
	public TaxEntryEntity getTaxEntry(TaxType taxType, String politicalDivisionKey) {
		TaxEntryEntity foundTaxEntry = null;
		if (taxType != null && politicalDivisionKey != null) {
			for (TaxEntryEntity taxEntry : this.taxEntries) {
				if (taxEntry != null) {
					if (taxType == taxEntry.getTaxType() && politicalDivisionKey.equals(taxEntry.getPoliticalDivision().getFips())) {
						foundTaxEntry = taxEntry;
						break;
					}
				}
			}
		}
		return foundTaxEntry;
	}
	
	/**
	 * @param taxType
	 * @return
	 */
	public List<TaxEntryEntity> getTaxEntries(TaxType taxType) {
		List<TaxEntryEntity> foundTaxEntries = new ArrayList<TaxEntryEntity>();
		if (taxType != null && this.taxEntries != null) {
			for (TaxEntryEntity taxEntry : this.taxEntries) {
				if (taxEntry != null) {
					if (taxType == taxEntry.getTaxType()) {
						foundTaxEntries.add(taxEntry);
					}
				}
			}
		}
		return foundTaxEntries;
	}
	
	/**
	 * @param taxEntries the taxEntries to set
	 */
	public void setTaxEntries(List<TaxEntryEntity> taxEntries) {
		this.taxEntries = taxEntries;
	}

	/**
	 * @param taxEntry the taxEntry to add
	 * 
	 */
	public void addTaxEntry(TaxEntryEntity taxEntry) {
		if (taxEntries != null) {
			this.taxEntries.add(taxEntry);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reportKey == null) ? 0 : reportKey.hashCode());
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
		TaxBurdenReportEntity other = (TaxBurdenReportEntity) obj;
		if (reportKey == null) {
			if (other.reportKey != null)
				return false;
		} else if (!reportKey.equals(other.reportKey))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxBurdenReportEntity [id=" + id + ", reportKey=" + reportKey + ", timestamp=" + timestamp
				+ ", taxPayerProfile=" + taxPayerProfile + ", taxEntries=" + taxEntries + "]";
	}

}
