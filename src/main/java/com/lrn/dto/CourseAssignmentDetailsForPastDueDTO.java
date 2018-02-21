package com.lrn.dto;

public class CourseAssignmentDetailsForPastDueDTO {
	
	private String name ;
	private Long incompletePastDue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIncompletePastDue() {
		return incompletePastDue;
	}
	public void setIncompletePastDue(Long incompletePastDue) {
		this.incompletePastDue = incompletePastDue;
	}

}
