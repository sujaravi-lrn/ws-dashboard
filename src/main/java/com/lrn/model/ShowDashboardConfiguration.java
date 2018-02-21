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
@SequenceGenerator(
		name 			= "GEN_SEQ",
		sequenceName 	= "SEQ_DASHBOARD_CONFIGURATION_ID",
		allocationSize=1
)
@Table(name = "dashboard_configuration")
public class ShowDashboardConfiguration implements Serializable {

	private static final long serialVersionUID = -3038145092585424806L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_SEQ")
	@Column(name="DASHBOARD_CONFIGURATION_ID")
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
	private Date cretaedOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
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
	
	public Long getDashboardId() {
		return dashboardId;
	}
	
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
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
	
	public Date getCretaedOn() {
		return cretaedOn;
	}
	
	public void setCretaedOn(Date cretaedOn) {
		this.cretaedOn = cretaedOn;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
