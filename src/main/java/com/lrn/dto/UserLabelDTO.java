package com.lrn.dto;

import java.io.Serializable;

/**  
* UserLabelDTO - data transfer object class for User Label. 
* It stores the data for the User Label. 
* 
*/

public class UserLabelDTO implements Serializable {

	private static final long serialVersionUID = -133825270512220806L;

	private String company;
	private String columnName;
	private String displayName;
	private String userValue;
	private Long reportPosition;
	private Long searchCriteria;
	private String sourceOfData;
	private Long editAllowed;
	private Long privilegeVisibilityLevel;
	private Long requiredField;
	private Long fieldType;
	private String instructionText;
	private Long protectedField;
	private String fieldFormat;

	/** Gets the company
	 * 
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/** Sets the company
	 * 
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/** Gets the columnName
	 * 
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/** Sets the columnName
	 * 
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/** Gets the displayName
	 * 
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/** Sets the displayName
	 * 
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/** Gets the userValue
	 * 
	 * @return the userValue
	 */
	public String getUserValue() {
		return userValue;
	}
	/** Sets the userValue
	 * 
	 * @param userValue the userValue to set
	 */
	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}
	/** Gets the reportPosition
	 * 
	 * @return the reportPosition
	 */
	public Long getReportPosition() {
		return reportPosition;
	}
	/** Sets the reportPosition
	 * 
	 * @param reportPosition the reportPosition to set
	 */
	public void setReportPosition(Long reportPosition) {
		this.reportPosition = reportPosition;
	}
	/** Gets the searchCriteria
	 * 
	 * @return the searchCriteria
	 */
	public Long getSearchCriteria() {
		return searchCriteria;
	}
	/** Sets the searchCriteria
	 * 
	 * @param searchCriteria the searchCriteria to set
	 */
	public void setSearchCriteria(Long searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/** Gets the sourceOfData
	 * 
	 * @return the sourceOfData
	 */
	public String getSourceOfData() {
		return sourceOfData;
	}
	/** Sets the sourceOfData
	 * 
	 * @param sourceOfData the sourceOfData to set
	 */
	public void setSourceOfData(String sourceOfData) {
		this.sourceOfData = sourceOfData;
	}
	/** Gets the editAllowed
	 * 
	 * @return the editAllowed
	 */
	public Long getEditAllowed() {
		return editAllowed;
	}
	/**  Sets the editAllowed
	 * 
	 * @param editAllowed the editAllowed to set
	 */
	public void setEditAllowed(Long editAllowed) {
		this.editAllowed = editAllowed;
	}
	/** Gets the privilegeVisibilityLevel
	 * 
	 * @return the privilegeVisibilityLevel
	 */
	public Long getPrivilegeVisibilityLevel() {
		return privilegeVisibilityLevel;
	}
	/** Sets the privilegeVisibilityLevel
	 * 
	 * @param privilegeVisibilityLevel the privilegeVisibilityLevel to set
	 */
	public void setPrivilegeVisibilityLevel(Long privilegeVisibilityLevel) {
		this.privilegeVisibilityLevel = privilegeVisibilityLevel;
	}
	/** Gets the requiredField
	 * 
	 * @return the requiredField
	 */
	public Long getRequiredField() {
		return requiredField;
	}
	/** Sets the requiredField
	 * 
	 * @param requiredField the requiredField to set
	 */
	public void setRequiredField(Long requiredField) {
		this.requiredField = requiredField;
	}
	/** Gets the fieldType
	 * 
	 * @return the fieldType
	 */
	public Long getFieldType() {
		return fieldType;
	}
	/** Sets the fieldType
	 * 
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(Long fieldType) {
		this.fieldType = fieldType;
	}
	/** Gets the instructionText
	 * 
	 * @return the instructionText
	 */
	public String getInstructionText() {
		return instructionText;
	}
	/** Sets the instructionText
	 * 
	 * @param instructionText the instructionText to set
	 */
	public void setInstructionText(String instructionText) {
		this.instructionText = instructionText;
	}
	/** Gets the protectedField
	 * 
	 * @return the protectedField
	 */
	public Long getProtectedField() {
		return protectedField;
	}
	/** Sets the protectedField
	 * 
	 * @param protectedField the protectedField to set
	 */
	public void setProtectedField(Long protectedField) {
		this.protectedField = protectedField;
	}
	/** Gets the fieldFormat
	 * 
	 * @return the fieldFormat
	 */
	public String getFieldFormat() {
		return fieldFormat;
	}
	/** Sets the fieldFormat
	 * 
	 * @param fieldFormat the fieldFormat to set
	 */
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}

	@Override
	public boolean equals(Object object) {
		
		if(object == this) return true;
		
		if(object == null) return false;
		
		if(object instanceof UserLabelDTO) {
			if(((UserLabelDTO) object).getColumnName().equals(this.getColumnName()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = hash * 11 + this.getColumnName().hashCode();
		return hash;
	}
}
