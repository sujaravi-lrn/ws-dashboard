package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;

public class DashBoardSelectionCriteriaDTO implements Serializable{
	
		
	private Long dashSelectionCriteriaId;
	

	private Long dashboardId;
	

	private Long configTypeId;
	

	private String clauseName;
	

	private String selectionSeq;
	

	private String columnName;

	private String filterOperator;
	

	private String conditionOperator;
	
	

	private String selectionValue;
	
	

	private String typeOfValue;
	
	

	private Long active;
	

	private String createdBy;
	

	private Date createdOn;
	
	

	private String updatedBy;
	

	private Date updatedOn;

	/**
	 * @return the dashSelectionCriteriaId
	 */
	public Long getDashSelectionCriteriaId() {
		return dashSelectionCriteriaId;
	}

	/**
	 * @param dashSelectionCriteriaId the dashSelectionCriteriaId to set
	 */
	public void setDashSelectionCriteriaId(Long dashSelectionCriteriaId) {
		this.dashSelectionCriteriaId = dashSelectionCriteriaId;
	}

	/**
	 * @return the dashboardId
	 */
	public Long getDashboardId() {
		return dashboardId;
	}

	/**
	 * @param dashboardId the dashboardId to set
	 */
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}

	/**
	 * @return the configTypeId
	 */
	public Long getConfigTypeId() {
		return configTypeId;
	}

	/**
	 * @param configTypeId the configTypeId to set
	 */
	public void setConfigTypeId(Long configTypeId) {
		this.configTypeId = configTypeId;
	}

	/**
	 * @return the clauseName
	 */
	public String getClauseName() {
		return clauseName;
	}

	/**
	 * @param clauseName the clauseName to set
	 */
	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}

	/**
	 * @return the selectionSeq
	 */
	public String getSelectionSeq() {
		return selectionSeq;
	}

	/**
	 * @param selectionSeq the selectionSeq to set
	 */
	public void setSelectionSeq(String selectionSeq) {
		this.selectionSeq = selectionSeq;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the filterOperator
	 */
	public String getFilterOperator() {
		return filterOperator;
	}

	/**
	 * @param filterOperator the filterOperator to set
	 */
	public void setFilterOperator(String filterOperator) {
		this.filterOperator = filterOperator;
	}

	/**
	 * @return the conditionOperator
	 */
	public String getConditionOperator() {
		return conditionOperator;
	}

	/**
	 * @param conditionOperator the conditionOperator to set
	 */
	public void setConditionOperator(String conditionOperator) {
		this.conditionOperator = conditionOperator;
	}

	/**
	 * @return the selectionValue
	 */
	public String getSelectionValue() {
		return selectionValue;
	}

	/**
	 * @param selectionValue the selectionValue to set
	 */
	public void setSelectionValue(String selectionValue) {
		this.selectionValue = selectionValue;
	}

	/**
	 * @return the typeOfValue
	 */
	public String getTypeOfValue() {
		return typeOfValue;
	}

	/**
	 * @param typeOfValue the typeOfValue to set
	 */
	public void setTypeOfValue(String typeOfValue) {
		this.typeOfValue = typeOfValue;
	}

	/**
	 * @return the active
	 */
	public Long getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Long active) {
		this.active = active;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
 