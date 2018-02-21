package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "REPORT_QUEUE_SEQ", allocationSize=1)
@Table(name = "REPORT_QUEUE")
public class ReportQueue extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = -5324590267061031882L;
	
	@Column(name="REPORT_NAME")
	private String reportName;
	
	@Column(name="DASHBOARD_ID")
	private Long dashboardId;
	
	@Column(name="SITE_ID")
	private Long siteId;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="HAS_DASHBOARD_CONFIG")
	private Long hasDashboardConfig;
	
	@Column(name="COMPLETION_STATUS")
	private String completionStatus;
	
	@Column(name="FILTERBY_COLUMNNAME")
	private String filterByColumnName;
	
	@Column(name="FILTERBY_COLUMNVALUE")
	private String filterByColumnValue;
	
	@Column(name="ORDERBY_COLUMN_NAME")
	private String orderByColumnName;
	
	@Column(name="ORDERBY_DIRECTION")
	private String orderByDirection;
	
	@Column(name="PROCESSED")
	private Long processed;
	
	@Column(name="FILE_LOCATION")
	private String fileLocation;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="REPORT_TYPE")
	private String reportType;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_BY_USER_NAME")
	private String createdByUserName;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Long getDashboardId() {
		return dashboardId;
	}

	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getHasDashboardConfig() {
		return hasDashboardConfig;
	}

	public void setHasDashboardConfig(Long hasDashboardConfig) {
		this.hasDashboardConfig = hasDashboardConfig;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public String getFilterByColumnName() {
		return filterByColumnName;
	}

	public void setFilterByColumnName(String filterByColumnName) {
		this.filterByColumnName = filterByColumnName;
	}

	public String getFilterByColumnValue() {
		return filterByColumnValue;
	}

	public void setFilterByColumnValue(String filterByColumnValue) {
		this.filterByColumnValue = filterByColumnValue;
	}

	public String getOrderByColumnName() {
		return orderByColumnName;
	}

	public void setOrderByColumnName(String orderByColumnName) {
		this.orderByColumnName = orderByColumnName;
	}

	public String getOrderByDirection() {
		return orderByDirection;
	}

	public void setOrderByDirection(String orderByDirection) {
		this.orderByDirection = orderByDirection;
	}

	public Long getProcessed() {
		return processed;
	}

	public void setProcessed(Long processed) {
		this.processed = processed;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	
}
