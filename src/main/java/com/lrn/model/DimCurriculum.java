/**
 * 
 */
package com.lrn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lrn.dto.DimCurriculumDTO;
import com.lrn.model.AbstractBaseEntity;

/**
 * @author kishore.ohal
 * 
 */
@NamedQueries( {
	@NamedQuery(name = "getCurriculumsBySiteId",
			query = "from DimCurriculum c where c.siteId = :siteId and c.driveState = 2  ")
})

@Table(name = "DIM_CURRICULUM")
public class DimCurriculum extends AbstractBaseEntity {

	@Column(name = "NAME")
	private String name;

	@Column(name = "SITE_ID")
	private Long siteId;

	@Column(name = "IS_DEFAULT")
	private String isDefault;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPEN_DATE")
	private Date openDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENROLL_CLOSE_DATE")
	private Date enrollCloseDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CLOSE_DATE")
	private Date closeDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DUE_DATE")
	private Date dueDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXEMPTION_DATE")
	private Date exemptionDate;

	@Column(name = "DRIVE_STATE")
	private Long driveState;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ADMINISTRATOR_EMAILS")
	private String administratorEmails;

	@Column(name = "IS_RULE_BASED_SCHEDULED")
	private Long isRuleBasedScheduled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED")
	private Date modified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REVIEWER_DUE_DATE")
	private Date reviewerDueDate;

	@Column(name = "NUM_DAYS_BEFORE_DUE")
	private Long numDaysBeforeDue;

	@Column(name = "NUM_DAYS_BEFORE_REVIEW_DUE")
	private Long numDaysBeforeReviewDue;

	/** The Site. */
/*	@ManyToOne(targetEntity = User.class, fetch = javax.persistence.FetchType.EAGER)
	@JoinTable(name = "curriculum_user", joinColumns = @JoinColumn(name = "curriculum_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
*/
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the siteId
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the isDefault
	 */
	public String getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the openDate
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate
	 *            the openDate to set
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return the enrolCloseDate
	 */
	public Date getEnrollCloseDate() {
		return enrollCloseDate;
	}

	/**
	 * @param enrolCloseDate
	 *            the enrolCloseDate to set
	 */
	public void setEnrollCloseDate(Date enrollCloseDate) {
		this.enrollCloseDate = enrollCloseDate;
	}

	/**
	 * @return the closeDate
	 */
	public Date getCloseDate() {
		return closeDate;
	}

	/**
	 * @param closeDate
	 *            the closeDate to set
	 */
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the exemptionDate
	 */
	public Date getExemptionDate() {
		return exemptionDate;
	}

	/**
	 * @param exemptionDate
	 *            the exemptionDate to set
	 */
	public void setExemptionDate(Date exemptionDate) {
		this.exemptionDate = exemptionDate;
	}

	/**
	 * @return the driveState
	 */
	public Long getDriveState() {
		return driveState;
	}

	/**
	 * @param driveState
	 *            the driveState to set
	 */
	public void setDriveState(Long driveState) {
		this.driveState = driveState;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the administratorEmails
	 */
	public String getAdministratorEmails() {
		return administratorEmails;
	}

	/**
	 * @param administratorEmails
	 *            the administratorEmails to set
	 */
	public void setAdministratorEmails(String administratorEmails) {
		this.administratorEmails = administratorEmails;
	}

	/**
	 * @return the isRuleBasedScheduled
	 */
	public Long getIsRuleBasedScheduled() {
		return isRuleBasedScheduled;
	}

	/**
	 * @param isRuleBasedScheduled
	 *            the isRuleBasedScheduled to set
	 */
	public void setIsRuleBasedScheduled(Long isRuleBasedScheduled) {
		this.isRuleBasedScheduled = isRuleBasedScheduled;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the reviewerDueDate
	 */
	public Date getReviewerDueDate() {
		return reviewerDueDate;
	}

	/**
	 * @param reviewerDueDate
	 *            the reviewerDueDate to set
	 */
	public void setReviewerDueDate(Date reviewerDueDate) {
		this.reviewerDueDate = reviewerDueDate;
	}

	/**
	 * @return the numDaysBeforeDue
	 */
	public Long getNumDaysBeforeDue() {
		return numDaysBeforeDue;
	}

	/**
	 * @param numDaysBeforeDue
	 *            the numDaysBeforeDue to set
	 */
	public void setNumDaysBeforeDue(Long numDaysBeforeDue) {
		this.numDaysBeforeDue = numDaysBeforeDue;
	}

	/**
	 * @return the numDaysBeforeReviewDue
	 */
	public Long getNumDaysBeforeReviewDue() {
		return numDaysBeforeReviewDue;
	}

	/**
	 * @param numDaysBeforeReviewDue
	 *            the numDaysBeforeReviewDue to set
	 */
	public void setNumDaysBeforeReviewDue(Long numDaysBeforeReviewDue) {
		this.numDaysBeforeReviewDue = numDaysBeforeReviewDue;
	}

	/**
	 * @return the user
	 */
/*	public User getUser() {
		return user;
	}
*/
	/**
	 * @param user
	 *            the user to set
	 */
/*	public void setUser(User user) {
		this.user = user;
	}
*/
	
	public DimCurriculumDTO convertModelToDTO() {
		DimCurriculumDTO dto = new DimCurriculumDTO();
		dto.setName(name);
		dto.setSiteId(siteId);
		dto.setIsDefault(isDefault);
		dto.setCreated(created);
		dto.setOpenDate(openDate);
		dto.setEnrolCloseDate(enrollCloseDate);
		dto.setCloseDate(closeDate);
		dto.setDueDate(dueDate);
		dto.setExemptionDate(exemptionDate);
		dto.setDriveState(driveState);
		dto.setDescription(description);
		dto.setAdministratorEmails(administratorEmails);
		dto.setIsRuleBasedScheduled(isRuleBasedScheduled);
		dto.setModified(modified);
		dto.setReviewerDueDate(reviewerDueDate);
		dto.setNumDaysBeforeDue(numDaysBeforeDue);
		dto.setNumDaysBeforeReviewDue(numDaysBeforeReviewDue);
				
		return dto;
	}
	
}
