package com.ttb.service.taxburden.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ttb.service.taxburden.domain.PoliticalDivision;


@Entity
@Table(name="political_division")
public class PoliticalDivisionEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true, nullable=false)
	private String fips;
	private String name;
	private String description;
	private String type;
	
	protected PoliticalDivisionEntity() {}

	/**
	 * @param fips
	 * @param name
	 * @param description
	 * @param type
	 */
	public PoliticalDivisionEntity(String fips, String name, String description, String type) {
		super();
		this.fips = fips;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public PoliticalDivisionEntity(PoliticalDivision politicalDivision) {
		this(politicalDivision.getFips(), politicalDivision.getName(), politicalDivision.getDescription(), politicalDivision.getType());
	}
	
	public PoliticalDivision toPoliticalDivision() {
		return new PoliticalDivision(this.getFips(), this.getName(), this.getDescription(), this.getType());
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
		result = prime * result + ((fips == null) ? 0 : fips.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PoliticalDivisionEntity other = (PoliticalDivisionEntity) obj;
		if (fips == null) {
			if (other.fips != null)
				return false;
		} else if (!fips.equals(other.fips))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "PoliticalDivisionEntity [id=" + id + ", fips=" + fips + ", name=" + name + ", description=" + description
				+ ", type=" + type + "]";
	}
}
