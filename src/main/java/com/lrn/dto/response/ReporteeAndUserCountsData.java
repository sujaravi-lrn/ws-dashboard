package com.lrn.dto.response;

public class ReporteeAndUserCountsData {

	private String userId;
	private String firstName;
	private String lastName;
	private String loginName;
	private String groupByColumnValue;
	private Long totalReportees;
	private Long completeCount;
	private Long inProgressCount;
	private Long notStartedCount;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getGroupByColumnValue() {
		return groupByColumnValue;
	}
	public void setGroupByColumnValue(String groupByColumnValue) {
		this.groupByColumnValue = groupByColumnValue;
	}
	public Long getTotalReportees() {
		return totalReportees;
	}
	public void setTotalReportees(Long totalReportees) {
		this.totalReportees = totalReportees;
	}
	public Long getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(Long completeCount) {
		this.completeCount = completeCount;
	}
	public Long getInProgressCount() {
		return inProgressCount;
	}
	public void setInProgressCount(Long inProgressCount) {
		this.inProgressCount = inProgressCount;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
}
