package com.lrn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries( {
	@NamedQuery(name = "findCompanyUsersColumnsByCompany",
			query = "from CompanyUsersColumn uc where uc.companyUsersColumnPK.company = :company and uc.privilegeVisibilityLevel = 0 order by reportPosition")
})
@Entity
@Table(name = "COMPANY_USERS_COLUMN")
public class CompanyUsersColumn implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3157402909384340312L;

	/** The company_users_column primary key. */
	@Id
	private CompanyUsersColumnPK companyUsersColumnPK;

	/** The display name. */
	@Column(name="DISPLAY_NAME", nullable=false)
	private String displayName;
	
	/** The report position. */
	@Column(name="REPORT_POSITION", nullable=false)
	private Long reportPosition;
	
	/** The search criteria. */
	@Column(name="SEARCH_CRITERIA")
	private Long searchCriteria;
	
	/** The source of data. */
	@Column(name="SOURCE_OF_DATA")
	private String sourceOfData;
	
	/** The edit allowed. */
	@Column(name="EDIT_ALLOWED")
	private Long editAllowed;
	
	/** The privilege visibility level. */
	@Column(name="PRIVILEGE_VISIBILITY_LEVEL")
	private Long privilegeVisibilityLevel;
	
	/** The required field. */
	@Column(name="REQUIRED_FIELD")
	private Long requiredField;
	
	/** The field type. */
	@Column(name="FIELD_TYPE")
	private Long fieldType;
	
	/** The instruction text. */
	@Column(name="INSTRUCTION_TEXT")
	private String instructionText;
	
	/** The protected field. */
	@Column(name="PROTECTED_FIELD")
	private Long protectedField;
	
	/** The field format. */
	@Column(name="FIELD_FORMAT")
	private String fieldFormat;

	/**
	 * Gets the company users column pk.
	 *
	 * @return the companyUsersColumnPK
	 */
	public CompanyUsersColumnPK getCompanyUsersColumnPK() {
		return companyUsersColumnPK;
	}

	/**
	 * Sets the company users column pk.
	 *
	 * @param companyUsersColumnPK the companyUsersColumnPK to set
	 */
	public void setCompanyUsersColumnPK(CompanyUsersColumnPK companyUsersColumnPK) {
		this.companyUsersColumnPK = companyUsersColumnPK;
	}

	/**
	 * Gets the display name.
	 *
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the report position.
	 *
	 * @return the reportPosition
	 */
	public Long getReportPosition() {
		return reportPosition;
	}

	/**
	 * Sets the report position.
	 *
	 * @param reportPosition the reportPosition to set
	 */
	public void setReportPosition(Long reportPosition) {
		this.reportPosition = reportPosition;
	}

	/**
	 * Gets the search criteria.
	 *
	 * @return the searchCriteria
	 */
	public Long getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * Sets the search criteria.
	 *
	 * @param searchCriteria the searchCriteria to set
	 */
	public void setSearchCriteria(Long searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * Gets the source of data.
	 *
	 * @return the sourceOfData
	 */
	public String getSourceOfData() {
		return sourceOfData;
	}

	/**
	 * Sets the source of data.
	 *
	 * @param sourceOfData the sourceOfData to set
	 */
	public void setSourceOfData(String sourceOfData) {
		this.sourceOfData = sourceOfData;
	}

	/**
	 * Gets the edits the allowed.
	 *
	 * @return the editAllowed
	 */
	public Long getEditAllowed() {
		return editAllowed;
	}

	/**
	 * Sets the edits the allowed.
	 *
	 * @param editAllowed the editAllowed to set
	 */
	public void setEditAllowed(Long editAllowed) {
		this.editAllowed = editAllowed;
	}

	/**
	 * Gets the privilege visibility level.
	 *
	 * @return the privilegeVisibilityLevel
	 */
	public Long getPrivilegeVisibilityLevel() {
		return privilegeVisibilityLevel;
	}

	/**
	 * Sets the privilege visibility level.
	 *
	 * @param privilegeVisibilityLevel the privilegeVisibilityLevel to set
	 */
	public void setPrivilegeVisibilityLevel(Long privilegeVisibilityLevel) {
		this.privilegeVisibilityLevel = privilegeVisibilityLevel;
	}

	/**
	 * Gets the required field.
	 *
	 * @return the requiredField
	 */
	public Long getRequiredField() {
		return requiredField;
	}

	/**
	 * Sets the required field.
	 *
	 * @param requiredField the requiredField to set
	 */
	public void setRequiredField(Long requiredField) {
		this.requiredField = requiredField;
	}

	/**
	 * Gets the field type.
	 *
	 * @return the fieldType
	 */
	public Long getFieldType() {
		return fieldType;
	}

	/**
	 * Sets the field type.
	 *
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(Long fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * Gets the instruction text.
	 *
	 * @return the instructionText
	 */
	public String getInstructionText() {
		return instructionText;
	}

	/**
	 * Sets the instruction text.
	 *
	 * @param instructionText the instructionText to set
	 */
	public void setInstructionText(String instructionText) {
		this.instructionText = instructionText;
	}

	/**
	 * Gets the protected field.
	 *
	 * @return the protectedField
	 */
	public Long getProtectedField() {
		return protectedField;
	}

	/**
	 * Sets the protected field.
	 *
	 * @param protectedField the protectedField to set
	 */
	public void setProtectedField(Long protectedField) {
		this.protectedField = protectedField;
	}

	/**
	 * Gets the field format.
	 *
	 * @return the fieldFormat
	 */
	public String getFieldFormat() {
		return fieldFormat;
	}

	/**
	 * Sets the field format.
	 *
	 * @param fieldFormat the fieldFormat to set
	 */
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
	}
}
