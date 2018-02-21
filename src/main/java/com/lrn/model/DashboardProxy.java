package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class DashboardProxy implements Serializable {

	private static final long serialVersionUID = 929051681341177003L;

	@Id
	private DashboardProxyPK dashboardProxyPK;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="UPDATE_USER")
	private String updateUser;

	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="UPDATE_DATE")
	private Date updateDate;

	public DashboardProxyPK getDashboardProxyPK() {
		return dashboardProxyPK;
	}

	public void setDashboardProxyPK(DashboardProxyPK dashboardProxyPK) {
		this.dashboardProxyPK = dashboardProxyPK;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
