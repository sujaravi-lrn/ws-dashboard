package com.lrn.dto.request;

import java.io.Serializable;

public class CompletionStatusReportRequestDTO implements Serializable {

	private static final long serialVersionUID = -8162884041610198224L;
	
	private Long dashboardId;
	private Long siteId;
	private String managerId;
	private String groupByColumnName;
	private Long hasDashboardConfig;
	
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
	
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
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
	
}
