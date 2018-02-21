package com.lrn.dto.response;

import java.io.Serializable;

public class DashboardUpdateDateDTO implements Serializable {

	private String statusETLUpdateDate;
	private String dailyETLUpdateDate;
	private String initialETLDate;
	private String userHierarchyUpdateDate;
	
	public String getStatusETLUpdateDate() {
		return statusETLUpdateDate;
	}
	public void setStatusETLUpdateDate(String statusETLUpdateDate) {
		this.statusETLUpdateDate = statusETLUpdateDate;
	}
	public String getDailyETLUpdateDate() {
		return dailyETLUpdateDate;
	}
	public void setDailyETLUpdateDate(String dailyETLUpdateDate) {
		this.dailyETLUpdateDate = dailyETLUpdateDate;
	}
	public String getInitialETLDate() {
		return initialETLDate;
	}
	public void setInitialETLDate(String initialETLDate) {
		this.initialETLDate = initialETLDate;
	}
	public String getUserHierarchyUpdateDate() {
		return userHierarchyUpdateDate;
	}
	public void setUserHierarchyUpdateDate(String userHierarchyUpdateDate) {
		this.userHierarchyUpdateDate = userHierarchyUpdateDate;
	}

}
