package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "fact_module_status")
public class FactModuleStatus implements Serializable{
	
private static final long serialVersionUID = 1459843694299436244L;

@Column(name="MODULE_STATUS_ID")
private Long moduleStatusId;

@Column(name="PARTNER_ID")
private Long partnerId;

@Column(name="SITE_ID")
private Long siteId;

@Column(name="CURRICULUM_ID")
private Long curriculumId;

@Column(name="BASE_CATALOG_ID ")
private String baseCatalogId;

@Column(name="USER_ID")
private String userId;

@Column(name="SYSTEM_ID")
private String systemId;

@Column(name="MODULE_ID")
private Long moduleId;

@Column(name="COMPLETION_COUNT")
private Long completionCount;

@Column(name="CERTIFICATE_ID")
private Long certificateId;

@Column(name="AICC_EVENTS_ID")
private Long aiccEventsId;

@Column(name="CATALOG_ID")
private String catalongId;

@Column(name="LANGUAGE")
private String language;

@Column(name="ASSIGNMENT_TYPE")
private String assignmentType;

@Column(name="ASSIGNMENT_DATE")
private Date assignmentDate;

@Column(name="MODULE_STATUS")
private String ModuleStatus;

@Column(name="MODULE_STATUS_DETAIL")
private String moduleStatusDetrail;

@Column(name="DUE_DATE")
private Date duteDate;

@Column(name="TIME_SPENT")
private Long timeSpent;

@Column(name="SCORE")
private Long score;

@Column(name="START_DATE")
private Date startDate;

@Column(name="COMPLETION_DATE")
private Date completionDate;

@Column(name="CREDIT_REASON_ID")
private String creatditReasonId;

public Long getModuleStatusId() {
	return moduleStatusId;
}

public void setModuleStatusId(Long moduleStatusId) {
	this.moduleStatusId = moduleStatusId;
}

public Long getPartnerId() {
	return partnerId;
}

public void setPartnerId(Long partnerId) {
	this.partnerId = partnerId;
}

public Long getSiteId() {
	return siteId;
}

public void setSiteId(Long siteId) {
	this.siteId = siteId;
}

public Long getCurriculumId() {
	return curriculumId;
}

public void setCurriculumId(Long curriculumId) {
	this.curriculumId = curriculumId;
}

public String getBaseCatalogId() {
	return baseCatalogId;
}

public void setBaseCatalogId(String baseCatalogId) {
	this.baseCatalogId = baseCatalogId;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getSystemId() {
	return systemId;
}

public void setSystemId(String systemId) {
	this.systemId = systemId;
}

public Long getModuleId() {
	return moduleId;
}

public void setModuleId(Long moduleId) {
	this.moduleId = moduleId;
}

public Long getCompletionCount() {
	return completionCount;
}

public void setCompletionCount(Long completionCount) {
	this.completionCount = completionCount;
}

public Long getCertificateId() {
	return certificateId;
}

public void setCertificateId(Long certificateId) {
	this.certificateId = certificateId;
}

public Long getAiccEventsId() {
	return aiccEventsId;
}

public void setAiccEventsId(Long aiccEventsId) {
	this.aiccEventsId = aiccEventsId;
}

public String getCatalongId() {
	return catalongId;
}

public void setCatalongId(String catalongId) {
	this.catalongId = catalongId;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public String getAssignmentType() {
	return assignmentType;
}

public void setAssignmentType(String assignmentType) {
	this.assignmentType = assignmentType;
}

public Date getAssignmentDate() {
	return assignmentDate;
}

public void setAssignmentDate(Date assignmentDate) {
	this.assignmentDate = assignmentDate;
}

public String getModuleStatus() {
	return ModuleStatus;
}

public void setModuleStatus(String moduleStatus) {
	ModuleStatus = moduleStatus;
}

public String getModuleStatusDetrail() {
	return moduleStatusDetrail;
}

public void setModuleStatusDetrail(String moduleStatusDetrail) {
	this.moduleStatusDetrail = moduleStatusDetrail;
}

public Date getDuteDate() {
	return duteDate;
}

public void setDuteDate(Date duteDate) {
	this.duteDate = duteDate;
}

public Long getTimeSpent() {
	return timeSpent;
}

public void setTimeSpent(Long timeSpent) {
	this.timeSpent = timeSpent;
}

public Long getScore() {
	return score;
}

public void setScore(Long score) {
	this.score = score;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getCompletionDate() {
	return completionDate;
}

public void setCompletionDate(Date completionDate) {
	this.completionDate = completionDate;
}

public String getCreatditReasonId() {
	return creatditReasonId;
}

public void setCreatditReasonId(String creatditReasonId) {
	this.creatditReasonId = creatditReasonId;
}





}
