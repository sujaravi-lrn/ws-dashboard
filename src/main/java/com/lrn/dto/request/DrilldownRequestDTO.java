package com.lrn.dto.request;

import java.io.Serializable;

public class DrilldownRequestDTO implements Serializable {

	private static final long serialVersionUID = -2030252380238972973L;
	
	private Long dashboardId;
	private Long siteId;
	private String userId;
	private String userName;
	private String groupByColumnName;
	private Long hasDashboardConfig;
	
	private String completionStatus; ////'complete', 'inProgress', 'notStarted', 'inComplete'
	private String filteredColumn;
	private String filteredColumnValue; //all columns and if it is a date - fromDateTOtoDate (ASSIGNMENT_DATE/DUE_DATE/COMPLETION_DATE)(08-25-2016TO09-25-2016)
	
	private String orderByColumnName;
	private String orderByDirection = "asc";
	 
	private Long rowsStart = 0L;
	private Long rowsSize = 100L;
	
	private Long isExport = 0L; //if isExport = 1, this will send the request to the dashboard Queue Processor instead
	private String loggedInUserId;
	private String loggedInUserName;
	private String reportType;
	
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
	public String getGroupByColumnName() {
		return groupByColumnName;
	}
	public void setGroupByColumnName(String groupByColumnName) {
		this.groupByColumnName = groupByColumnName;
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
	public String getFilteredColumn() {
		return filteredColumn;
	}
	public void setFilteredColumn(String filteredColumn) {
		this.filteredColumn = filteredColumn;
	}
	public String getFilteredColumnValue() {
		return filteredColumnValue;
	}
	public void setFilteredColumnValue(String filteredColumnValue) {
		this.filteredColumnValue = filteredColumnValue;
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
	public Long getRowsStart() {
		return rowsStart;
	}
	public void setRowsStart(Long rowsStart) {
		this.rowsStart = rowsStart;
	}
	public Long getRowsSize() {
		return rowsSize;
	}
	public void setRowsSize(Long rowsSize) {
		this.rowsSize = rowsSize;
	}
	public Long getIsExport() {
		return isExport;
	}
	public void setIsExport(Long isExport) {
		this.isExport = isExport;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoggedInUserName() {
		return loggedInUserName;
	}
	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}
	
}
