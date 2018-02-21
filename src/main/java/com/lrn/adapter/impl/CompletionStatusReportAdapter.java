package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.ICompletionStatusReportAdapter;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.response.CompletionStatusReportDrilldownResponseDTO;
import com.lrn.manager.ICompletionStatusReportManager;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IReportQueueHistoryManager;
import com.lrn.manager.IReportQueueManager;
import com.lrn.manager.IUserManager;
import com.lrn.model.ReportQueue;
import com.lrn.util.DBUtils;

public class CompletionStatusReportAdapter implements ICompletionStatusReportAdapter {

	private ICompletionStatusReportManager completionStatusReportManager;
	private IDashboardConfigurationManager dashboardConfigurationManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	private IUserManager userManager;
	private IReportQueueManager reportQueueManager;
	private IReportQueueHistoryManager reportQueueHistoryManager;
	
	public void setCompletionStatusReportManager(
			ICompletionStatusReportManager completionStatusReportManager) {
		this.completionStatusReportManager = completionStatusReportManager;
	}

	public void setDashboardConfigurationManager(
			IDashboardConfigurationManager dashboardConfigurationManager) {
		this.dashboardConfigurationManager = dashboardConfigurationManager;
	}

	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public void setReportQueueManager(IReportQueueManager reportQueueManager) {
		this.reportQueueManager = reportQueueManager;
	}

	public void setReportQueueHistoryManager(
			IReportQueueHistoryManager reportQueueHistoryManager) {
		this.reportQueueHistoryManager = reportQueueHistoryManager;
	}

	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldown(DrilldownRequestDTO requestDTO) {
		
		CompletionStatusReportDrilldownResponseDTO dto = new CompletionStatusReportDrilldownResponseDTO();
		
		Date startDate = new Date();
		ReportQueue reportQueue = new ReportQueue();
		//check to see if the isExport flag is 1, if so, then send the report generation request to the Queue
		if(requestDTO.getIsExport() == 1L) {
			reportQueue = reportQueueManager.addToQueue(requestDTO, "getCompletionStatusReportDrilldown", "CompletionReport");
			requestDTO.setRowsSize(null); // this will return all the rows
		}
		
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(requestDTO.getDashboardId(), 10l);
		
		Long totalRecords = 0L;
		if(requestDTO.getHasDashboardConfig() == 1) {
			
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());

			totalRecords = completionStatusReportManager
					.getCompletionStatusReportDrilldownSuperUserTotalRecords(requestDTO, dataPrivacyColumnMap);
					
			dto = completionStatusReportManager
					.getCompletionStatusReportDrilldownSuperUser(requestDTO, 
							columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
			
		} else if(requestDTO.getHasDashboardConfig() == 0) {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
			
			totalRecords = completionStatusReportManager.getCompletionStatusReportDrilldownRegularUserTotalRecords(requestDTO,
					sameDataHierCol, supervisorHierCol, additionalHierCol,
					userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);
			
			dto = completionStatusReportManager
					.getCompletionStatusReportDrilldownRegularUser(requestDTO, sameDataHierCol, supervisorHierCol, 
							additionalHierCol, userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);
			
		}
		
		//once report is generated, move the reportQueue to the reportQueueHistory and set processed = 1
		if(requestDTO.getIsExport() == 1L) {
			reportQueueHistoryManager.moveReportQueueRequestToHistory(reportQueue, startDate);
			reportQueueManager.remove(reportQueue.getId());
		}

		dto.setTotalRecords(totalRecords);
		dto.setColumnNameColumnDisplayNameSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
		
		return dto;
	}

}
