package com.lrn.dto;

import java.io.Serializable;

public class CompanyUserColumnDTO implements Serializable{
	
private String companyName;

private String columnName;

private String displayName;

private Long reportPosition;

private Long searchCriteria;

private String SourceOfData;

private Long editAllow;

private Long  privateVisibilityLevel;

private Long requiredFiled;

private Long filedType;

private String instructionText;

private Long protectedFiled;

private String filedformat;

private String isIncluded ;

private Long sequenceNumber ;


public String getCompanyName() {
	return companyName;
}

public void setCompanyName(String companyName) {
	this.companyName = companyName;
}

public String getColumnName() {
	return columnName;
}

public void setColumnName(String columnName) {
	this.columnName = columnName;
}

public String getDisplayName() {
	return displayName;
}

public void setDisplayName(String displayName) {
	this.displayName = displayName;
}

public Long getReportPosition() {
	return reportPosition;
}

public void setReportPosition(Long reportPosition) {
	this.reportPosition = reportPosition;
}

public Long getSearchCriteria() {
	return searchCriteria;
}

public void setSearchCriteria(Long searchCriteria) {
	this.searchCriteria = searchCriteria;
}

public String getSourceOfData() {
	return SourceOfData;
}

public void setSourceOfData(String sourceOfData) {
	SourceOfData = sourceOfData;
}

public Long getEditAllow() {
	return editAllow;
}

public void setEditAllow(Long editAllow) {
	this.editAllow = editAllow;
}

public Long getPrivateVisibilityLevel() {
	return privateVisibilityLevel;
}

public void setPrivateVisibilityLevel(Long privateVisibilityLevel) {
	this.privateVisibilityLevel = privateVisibilityLevel;
}

public Long getRequiredFiled() {
	return requiredFiled;
}

public void setRequiredFiled(Long requiredFiled) {
	this.requiredFiled = requiredFiled;
}

public Long getFiledType() {
	return filedType;
}

public void setFiledType(Long filedType) {
	this.filedType = filedType;
}

public String getInstructionText() {
	return instructionText;
}

public void setInstructionText(String instructionText) {
	this.instructionText = instructionText;
}

public Long getProtectedFiled() {
	return protectedFiled;
}

public void setProtectedFiled(Long protectedFiled) {
	this.protectedFiled = protectedFiled;
}

public String getFiledformat() {
	return filedformat;
}

public void setFiledformat(String filedformat) {
	this.filedformat = filedformat;
}

public String getIsIncluded() {
	return isIncluded;
}

public void setIsIncluded(String isIncluded) {
	this.isIncluded = isIncluded;
}

public Long getSequenceNumber() {
	return sequenceNumber;
}

public void setSequenceNumber(Long sequenceNumber) {
	this.sequenceNumber = sequenceNumber;
}

}
