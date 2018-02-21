package com.lrn.dto;



public class FirstTimeETLDTO {
	
	private String batchStatus;
	
    private String dashBoardConfigured;
	
	private String campaignConfigured;
	
	private String groupByConfigured;
	

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getDashBoardConfigured() {
		return dashBoardConfigured;
	}

	public void setDashBoardConfigured(String dashBoardConfigured) {
		this.dashBoardConfigured = dashBoardConfigured;
	}

	public String getCampaignConfigured() {
		return campaignConfigured;
	}

	public void setCampaignConfigured(String campaignConfigured) {
		this.campaignConfigured = campaignConfigured;
	}

	public String getGroupByConfigured() {
		return groupByConfigured;
	}

	public void setGroupByConfigured(String groupByConfigured) {
		this.groupByConfigured = groupByConfigured;
	}

}
