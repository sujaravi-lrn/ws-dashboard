package com.lrn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyUsersColumnPK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5696809891342411393L;

	/** The company. */
	@Column(name="COMPANY", nullable=false)
	private String company;
	
	/** The column name. */
	@Column(name="COLUMN_NAME", nullable=false)
	private String columnName;

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
