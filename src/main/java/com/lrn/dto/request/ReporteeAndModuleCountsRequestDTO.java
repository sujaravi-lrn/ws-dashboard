package com.lrn.dto.request;

import java.io.Serializable;

public class ReporteeAndModuleCountsRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 6952427383207657460L;
	
	private Long dashboardId;
	private Long siteId;
	private String userId;
	private String groupByColumnName;
	private Long hasDashboardConfig;
	private String completionStatus; //'complete', 'inProgress', 'notStarted', 'inComplete'

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
}
