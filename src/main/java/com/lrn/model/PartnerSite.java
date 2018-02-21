package com.lrn.model;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
  */ 
@Embeddable
@Table(name = "dim_partner_site")
public class PartnerSite implements Serializable{

private static final long serialVersionUID = 1439843694299436244L;

@Column(name="PARTNER_ID")
private Long partnerId;

@Column(name="SITE_ID")
private Long SITE_ID;

@Column(name="PARTNER_NAME")
private String partnerName;

@Column(name="SITE_NAME")
private String siteName;

@Column(name="P_REPORT_RECIPIENT_EMAIL")
private String p_report_recipient_email;

@Column(name="P_CUSTOMER_ID")
private String p_customer_id;

@Column(name="P_ACCOUNT_STATUS")
private String p_account_status;

@Column(name="P_COMMENTS")
private Clob  p_comments;

@Column(name="P_DEACTIVATED")
private Date p_deactivated;

@Column(name="P_CREATED")
private Date p_created;

@Column(name="P_UPDATED")
private Date p_updated;

@Column(name="S_PROFILE_QUIZ_ID")
private Long s_profile_quiz_id;

@Column(name="S_PROFILE_ACTIVE")
private String s_profile_active;

@Column(name="S_HOST")
private String s_host;

@Column(name="S_ACTIVE")
private Long s_active;

@Column(name="S_LAUNCH_DATE")
private Date s_launch_date;

@Column(name="S_PURPOSE")
private String s_purpose;

@Column(name="S_ACCOUNT_OWNER_EMAIL")
private String s_account_owner_email;

@Column(name="S_DEACTIVATED")
private Date s_deactivated;

@Column(name="S_UPDATED")
private Date s_updated;

@Column(name="LOAD_DATE")
private Date Load_date;

@Column(name="LOAD_BATCH_ID")
private Long load_batch_id;

@Column(name="ACTION")
private String action;

public Long getPartnerId() {
	return partnerId;
}

public void setPartnerId(Long partnerId) {
	this.partnerId = partnerId;
}

public Long getSITE_ID() {
	return SITE_ID;
}

public void setSITE_ID(Long sITE_ID) {
	SITE_ID = sITE_ID;
}

public String getPartnerName() {
	return partnerName;
}

public void setPartnerName(String partnerName) {
	this.partnerName = partnerName;
}

public String getSiteName() {
	return siteName;
}

public void setSiteName(String siteName) {
	this.siteName = siteName;
}

public String getP_report_recipient_email() {
	return p_report_recipient_email;
}

public void setP_report_recipient_email(String p_report_recipient_email) {
	this.p_report_recipient_email = p_report_recipient_email;
}

public String getP_customer_id() {
	return p_customer_id;
}

public void setP_customer_id(String p_customer_id) {
	this.p_customer_id = p_customer_id;
}

public String getP_account_status() {
	return p_account_status;
}

public void setP_account_status(String p_account_status) {
	this.p_account_status = p_account_status;
}

public Clob getP_comments() {
	return p_comments;
}

public void setP_comments(Clob p_comments) {
	this.p_comments = p_comments;
}

public Date getP_deactivated() {
	return p_deactivated;
}

public void setP_deactivated(Date p_deactivated) {
	this.p_deactivated = p_deactivated;
}

public Date getP_created() {
	return p_created;
}

public void setP_created(Date p_created) {
	this.p_created = p_created;
}

public Date getP_updated() {
	return p_updated;
}

public void setP_updated(Date p_updated) {
	this.p_updated = p_updated;
}

public Long getS_profile_quiz_id() {
	return s_profile_quiz_id;
}

public void setS_profile_quiz_id(Long s_profile_quiz_id) {
	this.s_profile_quiz_id = s_profile_quiz_id;
}

public String getS_profile_active() {
	return s_profile_active;
}

public void setS_profile_active(String s_profile_active) {
	this.s_profile_active = s_profile_active;
}

public String getS_host() {
	return s_host;
}

public void setS_host(String s_host) {
	this.s_host = s_host;
}

public Long getS_active() {
	return s_active;
}

public void setS_active(Long s_active) {
	this.s_active = s_active;
}

public Date getS_launch_date() {
	return s_launch_date;
}

public void setS_launch_date(Date s_launch_date) {
	this.s_launch_date = s_launch_date;
}

public String getS_purpose() {
	return s_purpose;
}

public void setS_purpose(String s_purpose) {
	this.s_purpose = s_purpose;
}

public String getS_account_owner_email() {
	return s_account_owner_email;
}

public void setS_account_owner_email(String s_account_owner_email) {
	this.s_account_owner_email = s_account_owner_email;
}

public Date getS_deactivated() {
	return s_deactivated;
}

public void setS_deactivated(Date s_deactivated) {
	this.s_deactivated = s_deactivated;
}

public Date getS_updated() {
	return s_updated;
}

public void setS_updated(Date s_updated) {
	this.s_updated = s_updated;
}

public Date getLoad_date() {
	return Load_date;
}

public void setLoad_date(Date load_date) {
	Load_date = load_date;
}

public Long getLoad_batch_id() {
	return load_batch_id;
}

public void setLoad_batch_id(Long load_batch_id) {
	this.load_batch_id = load_batch_id;
}

public String getAction() {
	return action;
}

public void setAction(String action) {
	this.action = action;
}


}
