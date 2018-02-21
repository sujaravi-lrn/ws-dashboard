package com.lrn.dto;

import java.io.Serializable;
import java.util.List;

/**  
* DrillDownDTO - data transfer object class for Displaying the Columns
* that are available and there sequence and relevant sections
* 
*/

public class DrillDownDTO implements Serializable {

	private static final long serialVersionUID = 1460126722496827367L;
	
	private List<ColumnDetailsForDrillDownDTO> ColumnDetailsForDrillDownDTOForDetailsList;
	private List<ColumnDetailsForDrillDownDTO> ColumnDetailsForDrillDownDTOForSummaryList;
	private List<ColumnDetailsForDrillDownDTO> ColumnDetailsForStatusReportCompletionStatusDrilldownList;
	private List<ColumnDetailsForDrillDownDTO> ColumnDetailsForStatusReportUserProgressDrilldownList;
	private String isDrillDownReportSelected ;
	private String isDrillDownUserSummaryDetailsSelected ;
	private String isStatusReportCompletionStatusDrilldownSelected;
	private String isStatusReportUserProgressDrilldownSelected;
	
	/**
	 * @return the columnDetailsForDrillDownDTOForDetailsList
	 */
	public List<ColumnDetailsForDrillDownDTO> getColumnDetailsForDrillDownDTOForDetailsList() {
		return ColumnDetailsForDrillDownDTOForDetailsList;
	}
	/**
	 * @param columnDetailsForDrillDownDTOForDetailsList the columnDetailsForDrillDownDTOForDetailsList to set
	 */
	public void setColumnDetailsForDrillDownDTOForDetailsList(
			List<ColumnDetailsForDrillDownDTO> columnDetailsForDrillDownDTOForDetailsList) {
		ColumnDetailsForDrillDownDTOForDetailsList = columnDetailsForDrillDownDTOForDetailsList;
	}
	/**
	 * @return the columnDetailsForDrillDownDTOForSummaryList
	 */
	public List<ColumnDetailsForDrillDownDTO> getColumnDetailsForDrillDownDTOForSummaryList() {
		return ColumnDetailsForDrillDownDTOForSummaryList;
	}
	/**
	 * @param columnDetailsForDrillDownDTOForSummaryList the columnDetailsForDrillDownDTOForSummaryList to set
	 */
	public void setColumnDetailsForDrillDownDTOForSummaryList(
			List<ColumnDetailsForDrillDownDTO> columnDetailsForDrillDownDTOForSummaryList) {
		ColumnDetailsForDrillDownDTOForSummaryList = columnDetailsForDrillDownDTOForSummaryList;
	}
	/**
	 * @return the isDrillDownReportSelected
	 */
	public String getIsDrillDownReportSelected() {
		return isDrillDownReportSelected;
	}
	/**
	 * @param isDrillDownReportSelected the isDrillDownReportSelected to set
	 */
	public void setIsDrillDownReportSelected(String isDrillDownReportSelected) {
		this.isDrillDownReportSelected = isDrillDownReportSelected;
	}
	/**
	 * @return the isDrillDownUserSummaryDetailsSelected
	 */
	public String getIsDrillDownUserSummaryDetailsSelected() {
		return isDrillDownUserSummaryDetailsSelected;
	}
	/**
	 * @param isDrillDownUserSummaryDetailsSelected the isDrillDownUserSummaryDetailsSelected to set
	 */
	public void setIsDrillDownUserSummaryDetailsSelected(
			String isDrillDownUserSummaryDetailsSelected) {
		this.isDrillDownUserSummaryDetailsSelected = isDrillDownUserSummaryDetailsSelected;
	}
	public List<ColumnDetailsForDrillDownDTO> getColumnDetailsForStatusReportCompletionStatusDrilldownList() {
		return ColumnDetailsForStatusReportCompletionStatusDrilldownList;
	}
	public void setColumnDetailsForStatusReportCompletionStatusDrilldownList(
			List<ColumnDetailsForDrillDownDTO> columnDetailsForStatusReportCompletionStatusDrilldownList) {
		ColumnDetailsForStatusReportCompletionStatusDrilldownList = columnDetailsForStatusReportCompletionStatusDrilldownList;
	}
	public List<ColumnDetailsForDrillDownDTO> getColumnDetailsForStatusReportUserProgressDrilldownList() {
		return ColumnDetailsForStatusReportUserProgressDrilldownList;
	}
	public void setColumnDetailsForStatusReportUserProgressDrilldownList(
			List<ColumnDetailsForDrillDownDTO> columnDetailsForStatusReportUserProgressDrilldownList) {
		ColumnDetailsForStatusReportUserProgressDrilldownList = columnDetailsForStatusReportUserProgressDrilldownList;
	}
	public String getIsStatusReportCompletionStatusDrilldownSelected() {
		return isStatusReportCompletionStatusDrilldownSelected;
	}
	public void setIsStatusReportCompletionStatusDrilldownSelected(
			String isStatusReportCompletionStatusDrilldownSelected) {
		this.isStatusReportCompletionStatusDrilldownSelected = isStatusReportCompletionStatusDrilldownSelected;
	}
	public String getIsStatusReportUserProgressDrilldownSelected() {
		return isStatusReportUserProgressDrilldownSelected;
	}
	public void setIsStatusReportUserProgressDrilldownSelected(
			String isStatusReportUserProgressDrilldownSelected) {
		this.isStatusReportUserProgressDrilldownSelected = isStatusReportUserProgressDrilldownSelected;
	}

}
