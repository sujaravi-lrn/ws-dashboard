package com.lrn.model;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class DashboardProxyPK implements Serializable {

	private static final long serialVersionUID = -3581769101073539995L;

	@Column(name = "PROXY_USER_ID")
	private String proxyUserId;
	
	@Column(name="USER_ID")
	private String userId;

	public String getProxyUserId() {
		return proxyUserId;
	}

	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
