package com.lrn.dto.response;

import java.io.Serializable;

public class ReporteeAndModuleCountsResponseDTO implements Serializable {

	private static final long serialVersionUID = 4411137981425821007L;
	
	private Long totalReportees;
//	private Long totalDirectReportees;
	private Long totalMandatoryModuleCounts;
	
	public Long getTotalReportees() {
		return totalReportees;
	}
	public void setTotalReportees(Long totalReportees) {
		this.totalReportees = totalReportees;
	}
	public Long getTotalMandatoryModuleCounts() {
		return totalMandatoryModuleCounts;
	}
	public void setTotalMandatoryModuleCounts(Long totalMandatoryModuleCounts) {
		this.totalMandatoryModuleCounts = totalMandatoryModuleCounts;
	}
	
}
