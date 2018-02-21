package com.lrn.dto.request;

import java.io.Serializable;

public class ShowExportReportDTO implements Serializable {

	private static final long serialVersionUID = 2083055485900530279L;
	
	private Long dashboardId;
	private Long siteId;
	private String userId;
	private Long showExport;
	
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
	public Long getShowExport() {
		return showExport;
	}
	public void setShowExport(Long showExport) {
		this.showExport = showExport;
	}
}
