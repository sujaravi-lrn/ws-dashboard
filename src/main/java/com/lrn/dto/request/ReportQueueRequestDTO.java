package com.lrn.dto.request;

import java.io.Serializable;

public class ReportQueueRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 4932571100681035508L;
	
	private Long siteId;
	private Long dashboardId;
	private String userId;
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Long getDashboardId() {
		return dashboardId;
	}
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
