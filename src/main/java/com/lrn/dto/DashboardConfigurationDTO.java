package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;

public class DashboardConfigurationDTO implements Serializable{

	private Long DashboardConfigurationId;
	private Long ConfigTypeId;
	private Long DashboardId;
	private String configValue;
	private String createdBy;
	private Date createdOn;
	private String updateBy;
	private Date updatedOn;
	
	public Long getDashboardConfigurationId() {
		return DashboardConfigurationId;
	}
	
	public void setDashboardConfigurationId(Long dashboardConfigurationId) {
		DashboardConfigurationId = dashboardConfigurationId;
	}
	
	public Long getConfigTypeId() {
		return ConfigTypeId;
	}
	
	public void setConfigTypeId(Long configTypeId) {
		ConfigTypeId = configTypeId;
	}
	
	public Long getDashboardId() {
		return DashboardId;
	}
	
	public void setDashboardId(Long dashboardId) {
		DashboardId = dashboardId;
	}
	
	public String getConfigValue() {
		return configValue;
	}
	
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
