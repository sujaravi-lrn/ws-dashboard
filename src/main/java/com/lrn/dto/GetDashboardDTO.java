package com.lrn.dto;

public class GetDashboardDTO {
	
	private Long DashboardID;
	private String active = "-1";
	
	public Long getDashboardID() {
		return DashboardID;
	}
	
	public void setDashboardID(Long dashboardID) {
		DashboardID = dashboardID;
	}
	
	public String getActive() {
		return active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
}
