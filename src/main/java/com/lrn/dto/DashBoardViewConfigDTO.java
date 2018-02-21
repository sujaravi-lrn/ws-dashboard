package com.lrn.dto;

import java.io.Serializable;

/**  
* DashBoardViewConfigDTO - data transfer object class for giving the configuration info which can then be used to 
* get the other values
* 
*/

public class DashBoardViewConfigDTO implements Serializable {
	
	private Long dashBoardId;
	private String showUserAssignmentStatusChart;
	private String showIncompletePastDue;
	private String showCourseCompletionStatusWithGroupByChart;
	private String showCourseCompletionStatusChart;
	private String showDrillDownReport;
	private String ShowDrillDownUserSummaryDetailReport;
	
	private String showStatusReportCompletionStatusReport;
	private String showStatusReportUserProgressReport;
	private String showStatusReportCompletionStatusDrilldownReport;
	private String showStatusReportUserProgressDrilldownReport;
	
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
	 * @return the showUserAssignmentStatusChart
	 */
	public String getShowUserAssignmentStatusChart() {
		return showUserAssignmentStatusChart;
	}
	/**
	 * @param showUserAssignmentStatusChart the showUserAssignmentStatusChart to set
	 */
	public void setShowUserAssignmentStatusChart(
			String showUserAssignmentStatusChart) {
		this.showUserAssignmentStatusChart = showUserAssignmentStatusChart;
	}
	/**
	 * @return the showIncompletePastDue
	 */
	public String getShowIncompletePastDue() {
		return showIncompletePastDue;
	}
	/**
	 * @param showIncompletePastDue the showIncompletePastDue to set
	 */
	public void setShowIncompletePastDue(String showIncompletePastDue) {
		this.showIncompletePastDue = showIncompletePastDue;
	}
	/**
	 * @return the showCourseCompletionStatusWithGroupByChart
	 */
	public String getShowCourseCompletionStatusWithGroupByChart() {
		return showCourseCompletionStatusWithGroupByChart;
	}
	/**
	 * @param showCourseCompletionStatusWithGroupByChart the showCourseCompletionStatusWithGroupByChart to set
	 */
	public void setShowCourseCompletionStatusWithGroupByChart(
			String showCourseCompletionStatusWithGroupByChart) {
		this.showCourseCompletionStatusWithGroupByChart = showCourseCompletionStatusWithGroupByChart;
	}
	/**
	 * @return the showCourseCompletionStatusChart
	 */
	public String getShowCourseCompletionStatusChart() {
		return showCourseCompletionStatusChart;
	}
	/**
	 * @param showCourseCompletionStatusChart the showCourseCompletionStatusChart to set
	 */
	public void setShowCourseCompletionStatusChart(
			String showCourseCompletionStatusChart) {
		this.showCourseCompletionStatusChart = showCourseCompletionStatusChart;
	}
	/**
	 * @return the showDrillDownReport
	 */
	public String getShowDrillDownReport() {
		return showDrillDownReport;
	}
	/**
	 * @param showDrillDownReport the showDrillDownReport to set
	 */
	public void setShowDrillDownReport(String showDrillDownReport) {
		this.showDrillDownReport = showDrillDownReport;
	}
	/**
	 * @return the showDrillDownUserSummaryDetailReport
	 */
	public String getShowDrillDownUserSummaryDetailReport() {
		return ShowDrillDownUserSummaryDetailReport;
	}
	/**
	 * @param showDrillDownUserSummaryDetailReport the showDrillDownUserSummaryDetailReport to set
	 */
	public void setShowDrillDownUserSummaryDetailReport(
			String showDrillDownUserSummaryDetailReport) {
		ShowDrillDownUserSummaryDetailReport = showDrillDownUserSummaryDetailReport;
	}
	public String getShowStatusReportCompletionStatusReport() {
		return showStatusReportCompletionStatusReport;
	}
	public void setShowStatusReportCompletionStatusReport(
			String showStatusReportCompletionStatusReport) {
		this.showStatusReportCompletionStatusReport = showStatusReportCompletionStatusReport;
	}
	public String getShowStatusReportUserProgressReport() {
		return showStatusReportUserProgressReport;
	}
	public void setShowStatusReportUserProgressReport(
			String showStatusReportUserProgressReport) {
		this.showStatusReportUserProgressReport = showStatusReportUserProgressReport;
	}
	public String getShowStatusReportCompletionStatusDrilldownReport() {
		return showStatusReportCompletionStatusDrilldownReport;
	}
	public void setShowStatusReportCompletionStatusDrilldownReport(
			String showStatusReportCompletionStatusDrilldownReport) {
		this.showStatusReportCompletionStatusDrilldownReport = showStatusReportCompletionStatusDrilldownReport;
	}
	public String getShowStatusReportUserProgressDrilldownReport() {
		return showStatusReportUserProgressDrilldownReport;
	}
	public void setShowStatusReportUserProgressDrilldownReport(
			String showStatusReportUserProgressDrilldownReport) {
		this.showStatusReportUserProgressDrilldownReport = showStatusReportUserProgressDrilldownReport;
	}	

}
