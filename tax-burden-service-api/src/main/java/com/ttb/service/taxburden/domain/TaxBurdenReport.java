package com.ttb.service.taxburden.domain;

import java.util.Date;
import java.util.List;

public class TaxBurdenReport {
	private String reportKey;
    private Date timestamp;
    private TaxPayerProfile taxPayerProfile;
    private List<TaxEntry> taxEntries;

    /**
	 * 
	 */
	public TaxBurdenReport() {
		super();
	}

	/**
	 * @param reportKey
	 * @param timestamp
	 * @param taxPayerProfile
	 * @param taxEntries
	 */
	public TaxBurdenReport(String reportKey, Date timestamp, TaxPayerProfile taxPayerProfile,
			List<TaxEntry> taxEntries) {
		super();
		this.reportKey = reportKey;
		this.timestamp = timestamp;
		this.taxPayerProfile = taxPayerProfile;
		this.taxEntries = taxEntries;
	}

	/**
	 * @return the reportKey
	 */
	public String getReportKey() {
		return reportKey;
	}

	/**
	 * @param reportKey the reportKey to set
	 */
	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the taxPayerProfile
	 */
	public TaxPayerProfile getTaxPayerProfile() {
		return taxPayerProfile;
	}

	/**
	 * @param taxPayerProfile the taxPayerProfile to set
	 */
	public void setTaxPayerProfile(TaxPayerProfile taxPayerProfile) {
		this.taxPayerProfile = taxPayerProfile;
	}

	/**
	 * @return the taxEntries
	 */
	public List<TaxEntry> getTaxEntries() {
		return taxEntries;
	}

	/**
	 * @param taxEntries the taxEntries to set
	 */
	public void setTaxEntries(List<TaxEntry> taxEntries) {
		this.taxEntries = taxEntries;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reportKey == null) ? 0 : reportKey.hashCode());
		result = prime * result + ((taxEntries == null) ? 0 : taxEntries.hashCode());
		result = prime * result + ((taxPayerProfile == null) ? 0 : taxPayerProfile.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		TaxBurdenReport other = (TaxBurdenReport) obj;
		if (reportKey == null) {
			if (other.reportKey != null)
				return false;
		} else if (!reportKey.equals(other.reportKey))
			return false;
		if (taxEntries == null) {
			if (other.taxEntries != null)
				return false;
		} else if (!taxEntries.equals(other.taxEntries))
			return false;
		if (taxPayerProfile == null) {
			if (other.taxPayerProfile != null)
				return false;
		} else if (!taxPayerProfile.equals(other.taxPayerProfile))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaxBurdenReport [reportKey=" + reportKey + ", timestamp=" + timestamp + ", taxPayerProfile="
				+ taxPayerProfile + ", taxEntries=" + taxEntries + "]";
	}

}
