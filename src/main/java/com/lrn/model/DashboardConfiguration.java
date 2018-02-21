package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "SEQ_DASHBOARD_CONFIGURATION_ID", allocationSize=1)
@Table(name = "DASHBOARD_CONFIGURATION")
public class DashboardConfiguration implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1238440441344453771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@Column(name = "DASHBOARD_CONFIGURATION_ID")
	private Long dashboardConfigurationId;
	
	@Column(name="CONFIG_TYPE_ID")
	private Long configTypeId;

	@Column(name="DASHBOARD_ID")
	private Long dashboardId;

	@Column(name="CONFIG_VALUE")
	private String configValue;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_ON")
	private Date updatedOn;

	
	/**
	 * @return the dashboardConfigurationId
	 */
	public Long getDashboardConfigurationId() {
		return dashboardConfigurationId;
	}
	
	/**
	 * @param dashboardConfigurationId the dashboardConfigurationId to set
	 */
	public void setDashboardConfigurationId(Long dashboardConfigurationId) {
		this.dashboardConfigurationId = dashboardConfigurationId;
	}
	
	/**
	 * @return the configTypeId
	 */
	public Long getConfigTypeId() {
		return configTypeId;
	}
	
	/**
	 * @param configTypeID the configTypeID to set
	 */
	public void setConfigTypeId(Long configTypeId) {
		this.configTypeId = configTypeId;
	}
	
	/**
	 * @return the dashboardId
	 */
	public Long getDashboardId() {
		return dashboardId;
	}
	
	/**
	 * @param dashboardId the dashboardId to set
	 */
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}
	
	/**
	 * @return the configValue
	 */
	public String getConfigValue() {
		return configValue;
	}
	
	/**
	 * @param configValue the configValue to set
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	

}
