package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**  
* ChartConfigurationDTO - data transfer object class for Displaying campaigns 
* as well as those campaigns which are selected and also whether the option is selected
* 
*/

public class ChartSelectionDTO implements Serializable {

	private List<ColumnDetailsDTO> columnDetailsDTOList;
	private String isAssignmentStatusSelected ;
	private String isIncompleteAndPastDueSelected ;
	private String isCourseCompletionStatusSelected ;
	private String isCourseCompletionStatusWithGroupBySelected ;
	private String isStatusReportCompletionStatusSelected;
	private String isStatusReportUserProgressSelected;
	
	/**
	 * @return the columnDetailsDTOList
	 */
	public List<ColumnDetailsDTO> getColumnDetailsDTOList() {
		return columnDetailsDTOList;
	}
	/**
	 * @param columnDetailsDTOList the columnDetailsDTOList to set
	 */
	public void setColumnDetailsDTOList(List<ColumnDetailsDTO> columnDetailsDTOList) {
		this.columnDetailsDTOList = columnDetailsDTOList;
	}
	/**
	 * @return the isAssignmentStatusSelected
	 */
	public String getIsAssignmentStatusSelected() {
		return isAssignmentStatusSelected;
	}
	/**
	 * @param isAssignmentStatusSelected the isAssignmentStatusSelected to set
	 */
	public void setIsAssignmentStatusSelected(String isAssignmentStatusSelected) {
		this.isAssignmentStatusSelected = isAssignmentStatusSelected;
	}
	/**
	 * @return the isIncompleteAndPastDueSelected
	 */
	public String getIsIncompleteAndPastDueSelected() {
		return isIncompleteAndPastDueSelected;
	}
	/**
	 * @param isIncompleteAndPastDueSelected the isIncompleteAndPastDueSelected to set
	 */
	public void setIsIncompleteAndPastDueSelected(
			String isIncompleteAndPastDueSelected) {
		this.isIncompleteAndPastDueSelected = isIncompleteAndPastDueSelected;
	}
	/**
	 * @return the isCourseCompletionStatusSelected
	 */
	public String getIsCourseCompletionStatusSelected() {
		return isCourseCompletionStatusSelected;
	}
	/**
	 * @param isCourseCompletionStatusSelected the isCourseCompletionStatusSelected to set
	 */
	public void setIsCourseCompletionStatusSelected(
			String isCourseCompletionStatusSelected) {
		this.isCourseCompletionStatusSelected = isCourseCompletionStatusSelected;
	}
	/**
	 * @return the isCourseCompletionStatusWithGroupBySelected
	 */
	public String getIsCourseCompletionStatusWithGroupBySelected() {
		return isCourseCompletionStatusWithGroupBySelected;
	}
	/**
	 * @param isCourseCompletionStatusWithGroupBySelected the isCourseCompletionStatusWithGroupBySelected to set
	 */
	public void setIsCourseCompletionStatusWithGroupBySelected(
			String isCourseCompletionStatusWithGroupBySelected) {
		this.isCourseCompletionStatusWithGroupBySelected = isCourseCompletionStatusWithGroupBySelected;
	}
	public String getIsStatusReportCompletionStatusSelected() {
		return isStatusReportCompletionStatusSelected;
	}
	public void setIsStatusReportCompletionStatusSelected(
			String isStatusReportCompletionStatusSelected) {
		this.isStatusReportCompletionStatusSelected = isStatusReportCompletionStatusSelected;
	}
	public String getIsStatusReportUserProgressSelected() {
		return isStatusReportUserProgressSelected;
	}
	public void setIsStatusReportUserProgressSelected(
			String isStatusReportUserProgressSelected) {
		this.isStatusReportUserProgressSelected = isStatusReportUserProgressSelected;
	}
	
	

}
