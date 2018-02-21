package com.lrn.dto;

public class GetCourseAssignmentStatusGroupByForManagerDTO {
	
	private String division;
	private Long complete_ON_TIME;
	private Long complete_PAST_DUE;
	private Long incomplete_PAST_DUE;
	private Long incomplete_NOT_DUE_YET;
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public Long getComplete_ON_TIME() {
		return complete_ON_TIME;
	}
	public void setComplete_ON_TIME(Long complete_ON_TIME) {
		this.complete_ON_TIME = complete_ON_TIME;
	}
	public Long getComplete_PAST_DUE() {
		return complete_PAST_DUE;
	}
	public void setComplete_PAST_DUE(Long complete_PAST_DUE) {
		this.complete_PAST_DUE = complete_PAST_DUE;
	}
	public Long getIncomplete_PAST_DUE() {
		return incomplete_PAST_DUE;
	}
	public void setIncomplete_PAST_DUE(Long incomplete_PAST_DUE) {
		this.incomplete_PAST_DUE = incomplete_PAST_DUE;
	}
	public Long getIncomplete_NOT_DUE_YET() {
		return incomplete_NOT_DUE_YET;
	}
	public void setIncomplete_NOT_DUE_YET(Long incomplete_NOT_DUE_YET) {
		this.incomplete_NOT_DUE_YET = incomplete_NOT_DUE_YET;
	}
}
