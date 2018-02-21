package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "FIRST_TIME_ETL_LOAD")
public class ETL implements Serializable {
	
private static final long serialVersionUID = 1434343694299436244L;

@Column(name="PARTNER_ID")
private Long partnerId;

@Column(name="SITE_ID")
private Long siteId;

@Column(name="BATCH_ID")
private Long batchId;

@Column(name="BATCH_START_TIME")
private Date batchStartDate;

@Column(name="BATCH_END_TIME")
private Date batchEndDate;

@Column(name="BATCH_STATUS")
private String batchStatus;

public Long getPartnerId() {
	return partnerId;
}

public void setPartnerId(Long partnerId) {
	this.partnerId = partnerId;
}

public Long getSiteId() {
	return siteId;
}

public void setSiteId(Long siteId) {
	this.siteId = siteId;
}

public Long getBatchId() {
	return batchId;
}

public void setBatchId(Long batchId) {
	this.batchId = batchId;
}

public Date getBatchStartDate() {
	return batchStartDate;
}

public void setBatchStartDate(Date batchStartDate) {
	this.batchStartDate = batchStartDate;
}

public Date getBatchEndDate() {
	return batchEndDate;
}

public void setBatchEndDate(Date batchEndDate) {
	this.batchEndDate = batchEndDate;
}

public String getBatchStatus() {
	return batchStatus;
}

public void setBatchStatus(String batchStatus) {
	this.batchStatus = batchStatus;
}




}
