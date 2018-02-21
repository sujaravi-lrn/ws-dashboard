package com.lrn.dto;

import java.io.Serializable;

/**  
* AssignmentStatusForManagerDTO - data transfer object class for Displaying
* userAssignment and course assignment status	
* 
*/

public class AssignmentStatusForManagerDTO implements Serializable {

	private String name ;
	private Long completed ;
	private Long  incomplete;
/*	private Long  notStarted;*/
	private Long pastDue;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the completed
	 */
	public Long getCompleted() {
		return completed;
	}
	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	/**
	 * @return the incomplete
	 */
	public Long getIncomplete() {
		return incomplete;
	}
	/**
	 * @param incomplete the incomplete to set
	 */
	public void setIncomplete(Long incomplete) {
		this.incomplete = incomplete;
	}
	/**
	 * @return the notStarted
	 */
	/*public Long getNotStarted() {
		return notStarted;
	}
	*//**
	 * @param notStarted the notStarted to set
	 *//*
	public void setNotStarted(Long notStarted) {
		this.notStarted = notStarted;
	}*/
	/**
	 * @return the pastDue
	 */
	public Long getPastDue() {
		return pastDue;
	}
	/**
	 * @param pastDue the pastDue to set
	 */
	public void setPastDue(Long pastDue) {
		this.pastDue = pastDue;
	}
	

}
