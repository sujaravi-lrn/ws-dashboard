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
		name 			= "GEN_SEQ1",
		sequenceName 	= "SEQ_DASHBOARD_ID",
		allocationSize=1
)
@Table(name = "DASHBOARD")
public class Dashboard implements Serializable{

	private static final long serialVersionUID = -3388145092585424806L;
	
	/** The dashboard id. */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_SEQ1")
	@Column(name="DASHBOARD_ID ")
	private Long dashboardId;
	
	/** The DASHBOARD_NAME. */
	@Column(name="DASHBOARD_NAME")
	private String dashboardName;
	
	/** The DESCRIPTION. */
	@Column(name="DESCRIPTION")
	private String description;
	
	/** The PARTNER_ID. */
	@Column(name="PARTNER_ID")
	private Long partnerID;
	
	/** The DESCRIPTION. */
	@Column(name="SITE_ID")
	private Long siteId;
	
	/** The DESCRIPTION. */
	@Column(name="ACTIVE")
	private Long active;
	
	/** The CREATED_BY. */
	@Column(name="CREATED_BY")
	private String createdBy;
	
	/** The CREATED_ON. */
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	/** The UPDATED_BY. */
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	/** The UPDATED_ON. */
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	public String getDashboardName() {
		return dashboardName;
	}
	
	public void setDashboardName(String dashboardName) {
		this.dashboardName = dashboardName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public Long getPartnerID() {
		return partnerID;
	}
	
	public void setPartnerID(Long partnerID) {
		this.partnerID = partnerID;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getDashboardId() {
		return dashboardId;
	}
	
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}
}
