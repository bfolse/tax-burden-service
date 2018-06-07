package com.ttb.service.taxburden.domain;

public class PoliticalDivision {
	private String fips;
	private String name;
	private String description;
	private String type;
	/**
	 * 
	 */
	public PoliticalDivision() {
		super();
	}
	/**
	 * @param fips
	 * @param name
	 * @param description
	 * @param type
	 */
	public PoliticalDivision(String fips, String name, String description, String type) {
		super();
		this.fips = fips;
		this.name = name;
		this.description = description;
		this.type = type;
	}
	/**
	 * @return the fips
	 */
	public String getFips() {
		return fips;
	}
	/**
	 * @param fips the fips to set
	 */
	public void setFips(String fips) {
		this.fips = fips;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fips == null) ? 0 : fips.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PoliticalDivision other = (PoliticalDivision) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fips == null) {
			if (other.fips != null)
				return false;
		} else if (!fips.equals(other.fips))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoliticalDivision [fips=" + fips + ", name=" + name + ", description=" + description + ", type=" + type
				+ "]";
	}

}
