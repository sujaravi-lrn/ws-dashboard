/**
 * 
 */
package com.lrn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.lrn.model.AbstractBaseEntity;

/**
 * @author kishore.ohal
 * 
 */
@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "")
@Table(name = "FACT_ASSIGNMENT_STATUS")
public class FactAssignmentStatus extends AbstractBaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8710475436738343925L;

	@Column(name = "DASHBOARD_ID")
	private Long dashBoardId;

	@Column(name = "PARTNER_ID")
	private Long partnerId;
	
	@Column(name = "SITE_ID")
	private Long siteId;

	@Column(name = "MANAGER_ID")
	private String managerId;	

	@Column(name = "GROUP_BY_COLUMN")
	private String groupByColumn;

	@Column(name = "GROUP_BY_COL_VAL")
	private String groupByColVal;
	
	@Column(name = "COMPLETE_COUNT")
	private Long completeCount;

	@Column(name = "INCOMPLETE_COUNT")
	private Long incompleteCount;

	@Column(name = "NOT_STARTED_NOT_DUE_YET")
	private Long notStartedNotDueYet;
	
	@Column(name = "NOT_STARTED_PAST_DUE")
	private Long notStartedPastDue;
	

	@Column(name = "NOT_STARTED_NO_DUE_DATE")
	private Long notStartedNoDueDate;

	@Column(name = "IN_PROCESS_NOT_DUE_YET")
	private Long inProcessNotDueYet;	

	@Column(name = "IN_PROCESS_PAST_DUE")
	private Long inProcessPastDue;
       
	@Column(name = "IN_PROCESS_NO_DUE_DATE")
	private Long inProcessNoDueDate;

	@Column(name = "COMPLETE_ON_TIME")
	private Long completeOnTime;	

	@Column(name = "COMPLETE_PAST_DUE")
	private Long compeletePastDue;                                                                                                                                                                              

	@Column(name = "COMPLETE_NO_DUE_DATE")
	private Long completeNoDueDate;

	@Column(name = "COMPLETE_CREDITED_ON_TIME")
	private Long completeCreditedOnTime;

	@Column(name = "COMPLETE_CREDITED_PAST_DUE")
	private Long completeCreditedPastDue;
	
	@Column(name = "COMPLETE_CREDITED_NO_DUE_DATE")
	private Long completeCreditedNoDueDate;	
	
	/**
	 * @return the dashBoardId
	 */
	public Long getDashBoardId() {
		return dashBoardId;
	}

	/**
	 * @param dashBoardId the dashBoardId to set
	 */
	public void setDashBoardId(Long dashBoardId) {
		this.dashBoardId = dashBoardId;
	}

	/**
	 * @return the partnerId
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the siteId
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the managerId
	 */
	public String getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the groupByColumn
	 */
	public String getGroupByColumn() {
		return groupByColumn;
	}

	/**
	 * @param groupByColumn the groupByColumn to set
	 */
	public void setGroupByColumn(String groupByColumn) {
		this.groupByColumn = groupByColumn;
	}

	/**
	 * @return the groupByVal
	 */
	public String getGroupByColVal() {
		return groupByColVal;
	}

	/**
	 * @param groupByVal the groupByVal to set
	 */
	public void setGroupByColVal(String groupByColVal) {
		this.groupByColVal = groupByColVal;
	}

	/**
	 * @return the completCount
	 */
	public Long getCompleteCount() {
		return completeCount;
	}

	/**
	 * @param completCount the completCount to set
	 */
	public void setCompleteCount(Long completeCount) {
		this.completeCount = completeCount;
	}

	/**
	 * @return the incompleteCount
	 */
	public Long getIncompleteCount() {
		return incompleteCount;
	}

	/**
	 * @param incompleteCount the incompleteCount to set
	 */
	public void setIncompleteCount(Long incompleteCount) {
		this.incompleteCount = incompleteCount;
	}

	/**
	 * @return the notStartedNotDueYet
	 */
	public Long getNotStartedNotDueYet() {
		return notStartedNotDueYet;
	}

	/**
	 * @param notStartedNotDueYet the notStartedNotDueYet to set
	 */
	public void setNotStartedNotDueYet(Long notStartedNotDueYet) {
		this.notStartedNotDueYet = notStartedNotDueYet;
	}

	/**
	 * @return the notStartedPastDue
	 */
	public Long getNotStartedPastDue() {
		return notStartedPastDue;
	}

	/**
	 * @param notStartedPastDue the notStartedPastDue to set
	 */
	public void setNotStartedPastDue(Long notStartedPastDue) {
		this.notStartedPastDue = notStartedPastDue;
	}

	/**
	 * @return the notStartedNoDueDate
	 */
	public Long getNotStartedNoDueDate() {
		return notStartedNoDueDate;
	}

	/**
	 * @param notStartedNoDueDate the notStartedNoDueDate to set
	 */
	public void setNotStartedNoDueDate(Long notStartedNoDueDate) {
		this.notStartedNoDueDate = notStartedNoDueDate;
	}

	/**
	 * @return the inProcessNotDueYet
	 */
	public Long getInProcessNotDueYet() {
		return inProcessNotDueYet;
	}

	/**
	 * @param inProcessNotDueYet the inProcessNotDueYet to set
	 */
	public void setInProcessNotDueYet(Long inProcessNotDueYet) {
		this.inProcessNotDueYet = inProcessNotDueYet;
	}

	/**
	 * @return the inProcessPastDue
	 */
	public Long getInProcessPastDue() {
		return inProcessPastDue;
	}

	/**
	 * @param inProcessPastDue the inProcessPastDue to set
	 */
	public void setInProcessPastDue(Long inProcessPastDue) {
		this.inProcessPastDue = inProcessPastDue;
	}

	/**
	 * @return the inProcessNoDueDate
	 */
	public Long getInProcessNoDueDate() {
		return inProcessNoDueDate;
	}

	/**
	 * @param inProcessNoDueDate the inProcessNoDueDate to set
	 */
	public void setInProcessNoDueDate(Long inProcessNoDueDate) {
		this.inProcessNoDueDate = inProcessNoDueDate;
	}

	/**
	 * @return the completeOnTime
	 */
	public Long getCompleteOnTime() {
		return completeOnTime;
	}

	/**
	 * @param completeOnTime the completeOnTime to set
	 */
	public void setCompleteOnTime(Long completeOnTime) {
		this.completeOnTime = completeOnTime;
	}

	/**
	 * @return the compeletePastDue
	 */
	public Long getCompeletePastDue() {
		return compeletePastDue;
	}

	/**
	 * @param compeletePastDue the compeletePastDue to set
	 */
	public void setCompeletePastDue(Long compeletePastDue) {
		this.compeletePastDue = compeletePastDue;
	}

	/**
	 * @return the completeNoDueDate
	 */
	public Long getCompleteNoDueDate() {
		return completeNoDueDate;
	}

	/**
	 * @param completeNoDueDate the completeNoDueDate to set
	 */
	public void setCompleteNoDueDate(Long completeNoDueDate) {
		this.completeNoDueDate = completeNoDueDate;
	}

	/**
	 * @return the completeCreditedOnTime
	 */
	public Long getCompleteCreditedOnTime() {
		return completeCreditedOnTime;
	}

	/**
	 * @param completeCreditedOnTime the completeCreditedOnTime to set
	 */
	public void setCompleteCreditedOnTime(Long completeCreditedOnTime) {
		this.completeCreditedOnTime = completeCreditedOnTime;
	}

	/**
	 * @return the completeCreditedPastDue
	 */
	public Long getCompleteCreditedPastDue() {
		return completeCreditedPastDue;
	}

	/**
	 * @param completeCreditedPastDue the completeCreditedPastDue to set
	 */
	public void setCompleteCreditedPastDue(Long completeCreditedPastDue) {
		this.completeCreditedPastDue = completeCreditedPastDue;
	}

	/**
	 * @return the completeCreditedNoDueDate
	 */
	public Long getCompleteCreditedNoDueDate() {
		return completeCreditedNoDueDate;
	}

	/**
	 * @param completeCreditedNoDueDate the completeCreditedNoDueDate to set
	 */
	public void setCompleteCreditedNoDueDate(Long completeCreditedNoDueDate) {
		this.completeCreditedNoDueDate = completeCreditedNoDueDate;
	}

	
	
}
