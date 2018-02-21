package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "MODULE_SEQ")
@Table(name = "MODULE")
public class Module extends AbstractBaseEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1439843694299364244L;

	/** The base catalog id. */
	@Column(name="BASE_CATALOG_ID")
	private String baseCatalogId;
	
	/** The created. */
	@Column(name="CREATED")
	private Date created;

	/**
	 * Gets the base catalog id.
	 *
	 * @return the baseCatalogId
	 */
	public String getBaseCatalogId() {
		return baseCatalogId;
	}

	/**
	 * Sets the base catalog id.
	 *
	 * @param baseCatalogId the baseCatalogId to set
	 */
	public void setBaseCatalogId(String baseCatalogId) {
		this.baseCatalogId = baseCatalogId;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
}
