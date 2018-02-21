package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class AssignedProxyUsersDTO implements Serializable {

	private static final long serialVersionUID = 6273825321090545706L;

	private String userDisplayName ;
	private String userId;
	private String userLoginName;
	
	private String proxyDisplayName;
	private String proxyUserId;
	private String proxyLoginName;	
	
	private Long sequenceNumber;
	
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;

	
	/**
	 * @return the userLoginName
	 */
	public String getUserLoginName() {
		return userLoginName;
	}

	/**
	 * @param userLoginName the userLoginName to set
	 */
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	/**
	 * @return the proxyId
	 */
	public String getProxyUserId() {
		return proxyUserId;
	}

	/**
	 * @param proxyId the proxyId to set
	 */
	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}

	/**
	 * @return the proxyLoginName
	 */
	public String getProxyLoginName() {
		return proxyLoginName;
	}

	/**
	 * @param proxyLoginName the proxyLoginName to set
	 */
	public void setProxyLoginName(String proxyLoginName) {
		this.proxyLoginName = proxyLoginName;
	}
	
	
	/**
	 * @return the proxyDisplayName
	 */
	public String getProxyDisplayName() {
		return proxyDisplayName;
	}

	/**
	 * @param proxyDisplayName the proxyDisplayName to set
	 */
	public void setProxyDisplayName(String proxyDisplayName) {
		this.proxyDisplayName = proxyDisplayName;
	}

		
	/**
	 * @return the sequenceNumber
	 */
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	/**
	 * @return the userDisplayName
	 */
	public String getUserDisplayName() {
		return userDisplayName;
	}
	
	/**
	 * @param userDisplayName the userDisplayName to set
	 */
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
