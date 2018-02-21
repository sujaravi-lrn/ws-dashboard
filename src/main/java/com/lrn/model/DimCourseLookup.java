package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
@Table(name = "dim_course_lookup")
public class DimCourseLookup implements Serializable{
	
	/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1238440441424453771L;

	/** The system id. */
	@Column(name="SYSTEM_ID")
	private String systemId;
	
	/** The node id. */
	@Column(name="NODE_ID")
	private Long nodeId;
	
	/** The course. */
	@Column(name="COURSE")
	private Long course;
	
	/** The catalog id. */
	@Column(name="CATALOG_ID")
	private String catalogId;
	
	/** The course short name. */
	@Column(name="COURSE_SHORT_NAME")
	private String courseShortName;
	
	/** The title. */
	@Column(name="TITLE")
	private String title;
	
	/** The description. */
	@Column(name="DESCRIPTION")
	private String description;
	
	/** The app type. */
	@Column(name="APP_TYPE")
	private String appType;
	
	/** The course path. */
	@Column(name="COURSE_PATH")
	private String coursePath;
	
	/** The language. */
	@Column(name="LANGUAGE")
	private String language;
	
	/** The version. */
	@Column(name="VERSION")
	private String version;
	
	/** The core vendor. */
	@Column(name="CORE_VENDOR")
	private String coreVendor;
	
	/** The media. */
	@Column(name="MEDIA")
	private String media;
	
	/** The module. *//*
	@ManyToOne(targetEntity = Module.class, fetch = FetchType.EAGER)
	@JoinColumn(name="MODULE_ID", nullable=false)
	private Module module;*/
	
	@Column(name="MODULE_ID")
	private Long module;
	
	/** The is library. */
	@Column(name="IS_LIBRARY")
	private Long isLibrary;
	
	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	private Date created;
	
	/** The templates. */
	@Column(name="TEMPLATES")
	private String templates;
	
	/** The documents. */
	@Column(name="DOCUMENTS")
	private String documents;
	
	/** The record quiz. */
	@Column(name="RECORD_QUIZ")
	private Long recordQuiz;
	
	/** The copyright year. */
	@Column(name="COPYRIGHT_YEAR")
	private Long copyrightYear;
	
	/** The vendor. */
	@Column(name="VENDOR")
	private String vendor;
	
	/** The edition id. */
	@Column(name="EDITION_ID")
	private Long editionId;
	
	/** The availability. */
	@Column(name="AVAILABILITY")
	private Long availability;
	
	/** The updated. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED")
	private Date updated;
	
	@Column(name="COURSE_CATEGORY_ID")
	private Long courseCategoryId;
	
	@Column(name="CONTENT_SIZE")
	private Long contentSize;

	/** The vendor. */
	@Column(name="COURSE_IMG_URL")
	private String courseImgUrl;
	
	/*** Course Category ***/
	@Column(name="Course_CATEGORY_ID")
	private Long CourseCategoryID;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getCourse() {
		return course;
	}

	public void setCourse(Long course) {
		this.course = course;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCourseShortName() {
		return courseShortName;
	}

	public void setCourseShortName(String courseShortName) {
		this.courseShortName = courseShortName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getCoursePath() {
		return coursePath;
	}

	public void setCoursePath(String coursePath) {
		this.coursePath = coursePath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCoreVendor() {
		return coreVendor;
	}

	public void setCoreVendor(String coreVendor) {
		this.coreVendor = coreVendor;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Long getModule() {
		return module;
	}

	public void setModule(Long module) {
		this.module = module;
	}

	public Long getIsLibrary() {
		return isLibrary;
	}

	public void setIsLibrary(Long isLibrary) {
		this.isLibrary = isLibrary;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getTemplates() {
		return templates;
	}

	public void setTemplates(String templates) {
		this.templates = templates;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	public Long getRecordQuiz() {
		return recordQuiz;
	}

	public void setRecordQuiz(Long recordQuiz) {
		this.recordQuiz = recordQuiz;
	}

	public Long getCopyrightYear() {
		return copyrightYear;
	}

	public void setCopyrightYear(Long copyrightYear) {
		this.copyrightYear = copyrightYear;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Long getEditionId() {
		return editionId;
	}

	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}

	public Long getAvailability() {
		return availability;
	}

	public void setAvailability(Long availability) {
		this.availability = availability;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(Long courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public Long getContentSize() {
		return contentSize;
	}

	public void setContentSize(Long contentSize) {
		this.contentSize = contentSize;
	}

	public String getCourseImgUrl() {
		return courseImgUrl;
	}

	public void setCourseImgUrl(String courseImgUrl) {
		this.courseImgUrl = courseImgUrl;
	}

	public Long getCourseCategoryID() {
		return CourseCategoryID;
	}

	public void setCourseCategoryID(Long courseCategoryID) {
		CourseCategoryID = courseCategoryID;
	}

}
 