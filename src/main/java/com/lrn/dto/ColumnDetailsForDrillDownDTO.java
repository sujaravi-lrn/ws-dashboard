package com.lrn.dto;

import java.io.Serializable;

/**  
* ColumnDetailsForDrillDownDTO - data transfer object class for Displaying columns that are selected and sequence number
* 
*/

public class ColumnDetailsForDrillDownDTO implements Serializable {

	private String columnName ;
	private String columnDisplayName;
	private String isIncluded ;
	private Long sequenceNumber ;
	private String section ;
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnDisplayName
	 */
	public String getColumnDisplayName() {
		return columnDisplayName;
	}
	/**
	 * @param columnDisplayName the columnDisplayName to set
	 */
	public void setColumnDisplayName(String columnDisplayName) {
		this.columnDisplayName = columnDisplayName;
	}
	/**
	 * @return the isIncluded
	 */
	public String getIsIncluded() {
		return isIncluded;
	}
	/**
	 * @param isIncluded the isIncluded to set
	 */
	public void setIsIncluded(String isIncluded) {
		this.isIncluded = isIncluded;
	}
	/**
	 * @return the sequenceNumber
	 */
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	
}
