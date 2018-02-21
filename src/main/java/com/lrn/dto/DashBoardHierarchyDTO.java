package com.lrn.dto;

import java.io.Serializable;
import java.util.List;

/**  
* DashBoardHierarchyDTO - data transfer object class for Displaying selected Hierarchy and available columns Display Name
* 
*/

public class DashBoardHierarchyDTO implements Serializable {

	private List<String> columnDisplayNameList;
	private String hierarchy1Value;
	private String hierarchy2Value;
	private String hierarchy3Value;
	/**
	 * @return the columnsDisplayNameList
	 */
	public List<String> getColumnDisplayNameList() {
		return columnDisplayNameList;
	}
	/**
	 * @param columnsDisplayNameList the columnsDisplayNameList to set
	 */
	public void setColumnsDisplayNameList(List<String> columnDisplayNameList) {
		this.columnDisplayNameList = columnDisplayNameList;
	}
	/**
	 * @return the hierarchy1Value
	 */
	public String getHierarchy1Value() {
		return hierarchy1Value;
	}
	/**
	 * @param hierarchy1Value the hierarchy1Value to set
	 */
	public void setHierarchy1Value(String hierarchy1Value) {
		this.hierarchy1Value = hierarchy1Value;
	}
	/**
	 * @return the hierarchy2Value
	 */
	public String getHierarchy2Value() {
		return hierarchy2Value;
	}
	/**
	 * @param hierarchy2Value the hierarchy2Value to set
	 */
	public void setHierarchy2Value(String hierarchy2Value) {
		this.hierarchy2Value = hierarchy2Value;
	}
	/**
	 * @return the hierarchy3Value
	 */
	public String getHierarchy3Value() {
		return hierarchy3Value;
	}
	/**
	 * @param hierarchy3Value the hierarchy3Value to set
	 */
	public void setHierarchy3Value(String hierarchy3Value) {
		this.hierarchy3Value = hierarchy3Value;
	}
	
	
}
