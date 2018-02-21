package com.lrn.dto.response;

import java.io.Serializable;

public class ReporteeAndModuleCountsDrilldownResponseDTO implements Serializable {

	private static final long serialVersionUID = 5058375806246015772L;

	private String baseCatalogId;
	private String moduleTitle;
	private Long totalReportees;
	private Long completeCount;
	private Long incompleteCount;
	private Long incompletePasDueCount;
	
	public String getBaseCatalogId() {
		return baseCatalogId;
	}
	public void setBaseCatalogId(String baseCatalogId) {
		this.baseCatalogId = baseCatalogId;
	}
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
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
	public Long getIncompleteCount() {
		return incompleteCount;
	}
	public void setIncompleteCount(Long incompleteCount) {
		this.incompleteCount = incompleteCount;
	}
	public Long getIncompletePasDueCount() {
		return incompletePasDueCount;
	}
	public void setIncompletePasDueCount(Long incompletePasDueCount) {
		this.incompletePasDueCount = incompletePasDueCount;
	}
	
}
