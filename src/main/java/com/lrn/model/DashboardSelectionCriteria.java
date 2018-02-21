package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lrn.dto.DashBoardSelectionCriteriaDTO;

@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "SEQ_DASH_SELECTION_CRITERIA_ID", allocationSize=1)
@Table(name = "DASHBOARD_SELECTION_CRITERIA")
public class DashboardSelectionCriteria implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1238440441424453771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@Column(name = "DASH_SELECTION_CRITERIA_ID")
	private Long dashSelectionCriteriaId;

	@Column(name = "DASHBOARD_ID")
	private Long dashboardId;

	@Column(name = "CONFIG_TYPE_ID")
	private Long configTypeId;

	@Column(name = "CLAUSE_NAME")
	private String clauseName;

	@Column(name = "SELECTION_SEQ")
	private String selectionSeq;

	@Column(name = "COLUMN_NAME")
	private String columnName;

	@Column(name = "FILTER_OPERATOR")
	private String filterOperator;

	@Column(name = "CONDITION_OPERATOR")
	private String conditionOperator;

	@Column(name = "SELECTION_VALUE")
	private String selectionValue;

	@Column(name = "TYPE_OF_VALUE")
	private String typeOfValue;

	@Column(name = "ACTIVE")
	private Long active;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	/**
	 * @return the dashSelectionCriteriaId
	 */
	public Long getDashSelectionCriteriaId() {
		return dashSelectionCriteriaId;
	}

	/**
	 * @param dashSelectionCriteriaId
	 *            the dashSelectionCriteriaId to set
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
	 * @param dashboardId
	 *            the dashboardId to set
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
	 * @param configTypeId
	 *            the configTypeId to set
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
	 * @param clauseName
	 *            the clauseName to set
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
	 * @param selectionSeq
	 *            the selectionSeq to set
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
	 * @param columnName
	 *            the columnName to set
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
	 * @param filterOperator
	 *            the filterOperator to set
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
	 * @param conditionOperator
	 *            the conditionOperator to set
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
	 * @param selectionValue
	 *            the selectionValue to set
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
	 * @param typeOfValue
	 *            the typeOfValue to set
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
	 * @param active
	 *            the active to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param createdOn
	 *            the createdOn to set
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
	 * @param updatedBy
	 *            the updatedBy to set
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
	 * @param updatedOn
	 *            the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public DashBoardSelectionCriteriaDTO convertModelToDTO() {
		DashBoardSelectionCriteriaDTO dto = new DashBoardSelectionCriteriaDTO();

		dto.setDashSelectionCriteriaId(dashSelectionCriteriaId);
		dto.setDashboardId(dashboardId);

		dto.setConfigTypeId(configTypeId);

		dto.setClauseName(clauseName);

		dto.setSelectionSeq(selectionSeq);
		dto.setColumnName(columnName);
		dto.setFilterOperator(filterOperator);
		dto.setConditionOperator(conditionOperator);
		dto.setSelectionValue(selectionValue);
		dto.setTypeOfValue(typeOfValue);
		dto.setActive(active);
		dto.setCreatedBy(createdBy);
		dto.setCreatedOn(createdOn);
		dto.setUpdatedBy(updatedBy);
		dto.setUpdatedOn(updatedOn);
		return dto;
	}
	

	@Override
	public boolean equals(Object object) {
		
		if(object == this) return true;
		
		if(object == null) return false;
		
		if(object instanceof DashboardSelectionCriteria) {
			if( ((DashboardSelectionCriteria) object).getColumnName() != null 
					&& this.getColumnName() != null
					&& ((DashboardSelectionCriteria) object).getColumnName().equals(this.getColumnName()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 112;
		hash = hash * 113 + this.getColumnName().hashCode();
		return hash;
	}
}
