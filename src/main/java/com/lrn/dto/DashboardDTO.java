package com.lrn.dto;
import java.io.Serializable;
import java.util.Date;

public class DashboardDTO implements Serializable {

	private static final long serialVersionUID = 6480323228382511994L;
	
	private Long DashboardID;
	private String DashboardName;
	private String Description;
	private Long PartnerId;
	private Long siteId;
	private Long active;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	public Long getDashboardID() {
		return DashboardID;
	}
	
	public void setDashboardID(Long dashboardID) {
		DashboardID = dashboardID;
	}
	
	public String getDashboardName() {
		return DashboardName;
	}
	
	public void setDashboardName(String dashboardName) {
		DashboardName = dashboardName;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public Long getPartnerId() {
		return PartnerId;
	}
	
	public void setPartnerId(Long partnerId) {
		PartnerId = partnerId;
	}
	
	public Long getSiteId() {
		return siteId;
	}
	
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	
	public Long getActive() {
		return active;
	}
	
	public void setActive(Long active) {
		this.active = active;
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
