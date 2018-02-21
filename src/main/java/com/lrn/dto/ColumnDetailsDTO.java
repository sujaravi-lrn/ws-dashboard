package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**  
* ColumnDetailsDTO - data transfer object class for Displaying columns that are selected and sequence number
* 
*/

public class ColumnDetailsDTO implements Serializable {

	private String columnDisplayName ;
	private String columnName ;
	
	private String isIncluded ;
	private Long sequenceNumber ;
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
	
}
