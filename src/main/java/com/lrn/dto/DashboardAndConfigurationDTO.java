package com.lrn.dto;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public class DashboardAndConfigurationDTO extends DashboardDTO {
	
	private static final long serialVersionUID = 8972783198080430084L;
	
	private Long dashboardConfigurationId;
	private Long configTypeId;
	private String configValue;
	
	public Long getDashboardConfigurationId() {
		return dashboardConfigurationId;
	}
	public void setDashboardConfigurationId(Long dashboardConfigurationId) {
		this.dashboardConfigurationId = dashboardConfigurationId;
	}
	public Long getConfigTypeId() {
		return configTypeId;
	}
	public void setConfigTypeId(Long configTypeId) {
		this.configTypeId = configTypeId;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
