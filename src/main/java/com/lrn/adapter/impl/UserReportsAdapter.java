package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.IUserReportsAdapter;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsDrilldownResponseDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserCountsData;
import com.lrn.dto.response.ReporteeAndUserCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserReportResponseDTO;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IReportQueueHistoryManager;
import com.lrn.manager.IReportQueueManager;
import com.lrn.manager.IUserManager;
import com.lrn.manager.IUserReportsManager;
import com.lrn.model.ReportQueue;
import com.lrn.util.StringUtil;

public class UserReportsAdapter implements IUserReportsAdapter {
	
	private IUserManager userManager;
	private IUserReportsManager userReportsManager;
	private IDashboardConfigurationManager dashboardConfigurationManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	private IReportQueueManager reportQueueManager;
	private IReportQueueHistoryManager reportQueueHistoryManager;
	
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public void setUserReportsManager(IUserReportsManager userReportsManager) {
		this.userReportsManager = userReportsManager;
	}

	public void setDashboardConfigurationManager(
			IDashboardConfigurationManager dashboardConfigurationManager) {
		this.dashboardConfigurationManager = dashboardConfigurationManager;
	}

	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}
	
	public void setReportQueueManager(IReportQueueManager reportQueueManager) {
		this.reportQueueManager = reportQueueManager;
	}

	public void setReportQueueHistoryManager(
			IReportQueueHistoryManager reportQueueHistoryManager) {
		this.reportQueueHistoryManager = reportQueueHistoryManager;
	}

	/**************************************************************************/
	
	@Override
	public Long getDirectReporteeCounts(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(requestDTO.getHasDashboardConfig() == 1) {
			return 0L;
		} else {
			return userReportsManager.getTotalDirectReportees(requestDTO, new HashMap<String, ArrayList<String>> ());
		}
	}
	
	@Override
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCounts(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		ReporteeAndModuleCountsResponseDTO responseDTO = new ReporteeAndModuleCountsResponseDTO();
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(requestDTO.getHasDashboardConfig() == 1) {
			
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());

			responseDTO = userReportsManager.getReporteeAndModuleCountsSuperUser(requestDTO, dataPrivacyColumnMap);	
			
//			responseDTO.setTotalDirectReportees(0L);

		} else {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
					
			responseDTO = userReportsManager.getReporteeAndModuleCountsRegularUser(requestDTO, sameDataHierCol, 
					supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
			
//			responseDTO.setTotalDirectReportees(userReportsManager.getTotalDirectReportees(requestDTO, new HashMap<String, ArrayList<String>> ()));

		}
		
		
		return responseDTO;
	}

	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldown(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(requestDTO.getHasDashboardConfig() == 1) {
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());

			return userReportsManager.getReporteeAndModuleCountsDrilldownSuperUser(requestDTO, dataPrivacyColumnMap);	
			
		} else {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
				
			return userReportsManager.getReporteeAndModuleCountsDrilldownRegularUser(requestDTO, sameDataHierCol, 
					supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
		}
		
	}
	
	/**************************************************************************/
	
	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getDirectReporteeAndModuleCountsDrilldown(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		return userReportsManager.getDirectReporteeAndModuleCountsDrilldown(requestDTO);
	}

	@Override
	public Long getDirectReporteeAndUserCountsTotalRecords(DrilldownRequestDTO requestDTO) {
	
		//direct reportees should always be empty for Adminstrator users
		if(requestDTO.getHasDashboardConfig() == 1L &&
				StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName())) {
			return 0L;
		}
		
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(requestDTO.getDashboardId(), 11l);
		
		//for Admin/SuperUsers (hasDashboardConfig = 1)
		if(requestDTO.getHasDashboardConfig() == 1L) {
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());
			
			return userReportsManager.getDirectReporteeAndUserReportTotalRecordsSuperUser(requestDTO, 
					columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
		} else {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
			
			String groupByColumn = requestDTO.getGroupByColumnName();
			requestDTO.setGroupByColumnName(groupByColumn);
			
			//TO DO - remove this method - getDirectReporteeAndUserCountsTotalRecords() - NOT needed. should reuse other method.
			//Long totalRecords = userReportsManager.getDirectReporteeAndUserCountsTotalRecords(requestDTO);
			return userReportsManager.getDirectReporteeAndUserReportTotalRecords(requestDTO, 
					columnNameANDColumnDisplayNameAndSequenceMap, new HashMap<String, ArrayList<String>> (),
					sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
		}
	}
	
	@Override
	public ReporteeAndUserCountsResponseDTO getDirectReporteeAndUserCounts(DrilldownRequestDTO requestDTO) {
		
		ReporteeAndUserCountsResponseDTO dto = new ReporteeAndUserCountsResponseDTO();
		List<ReporteeAndUserCountsData> data = new ArrayList<ReporteeAndUserCountsData> ();
//		Long totalRecords = 0L;
		
		//direct reportees should always be empty for Adminstrator users
		if(requestDTO.getHasDashboardConfig() == 1L &&
				StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName())) {
			dto.setData(data);
//			dto.setTotalRecords(totalRecords);
			return dto;
		}
		
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(requestDTO.getDashboardId(), 11l);
		
		//for Admin/SuperUsers (hasDashboardConfig = 1)
		if(requestDTO.getHasDashboardConfig() == 1L) {
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());
			
			data = userReportsManager.getDirectReporteeAndUserCountsSuperUser(requestDTO, dataPrivacyColumnMap);
			
//			totalRecords = userReportsManager.getDirectReporteeAndUserReportTotalRecordsSuperUser(requestDTO, 
//					columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
			
		} else {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
			
			String groupByColumn = requestDTO.getGroupByColumnName();
			data = userReportsManager.getDirectReporteeAndUserCountsRegularUser(requestDTO,
					sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
			
			requestDTO.setGroupByColumnName(groupByColumn);
			
			//TO DO - remove this method - getDirectReporteeAndUserCountsTotalRecords() - NOT needed. should reuse other method.
			//Long totalRecords = userReportsManager.getDirectReporteeAndUserCountsTotalRecords(requestDTO);
//			totalRecords = userReportsManager.getDirectReporteeAndUserReportTotalRecords(requestDTO, 
//					columnNameANDColumnDisplayNameAndSequenceMap, new HashMap<String, ArrayList<String>> (),
//					sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
		}
		
		dto.setData(data);
//		dto.setTotalRecords(totalRecords);

		return dto;
	}

	@Override
	public Long getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO) {

		//direct reportees should always be empty for Adminstrator users
		if(requestDTO.getHasDashboardConfig() == 1L &&
				StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName())) {
			return 0L;
		}
		
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(requestDTO.getDashboardId(), 11l);
		
		boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
		Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
		if(isDataPrivacySetupForSite && requestDTO.getHasDashboardConfig() == 1L) 
			dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());

		Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
		String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
		String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
		String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
		String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
		
		return userReportsManager.getDirectReporteeAndUserReportTotalRecords(requestDTO, 
				columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap,
				sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
		
	}
	
	@Override
	public ReporteeAndUserReportResponseDTO getDirectReporteeAndUserReport(DrilldownRequestDTO requestDTO) {

		ReporteeAndUserReportResponseDTO dto = new ReporteeAndUserReportResponseDTO();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>> ();
		Map<String, Map<String, Long>> columnNameColumnDisplayNameSequenceMap = new HashMap<String, Map<String, Long>> ();
//		Long totalRecords = 0L;
		
		//direct reportees should always be empty for Adminstrator users
		if(requestDTO.getHasDashboardConfig() == 1L &&
				StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName())) {
			dto.setData(data);
			dto.setColumnNameColumnDisplayNameSequenceMap(columnNameColumnDisplayNameSequenceMap);
//			dto.setTotalRecords(totalRecords);
			return dto;
		}
		
		Date startDate = new Date();
		ReportQueue reportQueue = new ReportQueue();
		//check to see if the isExport flag is 1, if so, then send the report generation request to the Queue
		if(requestDTO.getIsExport() == 1L) {
			reportQueue = reportQueueManager.addToQueue(requestDTO, "getDirectReporteeAndUserReport", "UserReport");
			requestDTO.setRowsSize(null); // this will return all the rows
		}
		
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(requestDTO.getDashboardId(), 11l);
		
		boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(requestDTO.getSiteId());
		Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
		if(isDataPrivacySetupForSite && requestDTO.getHasDashboardConfig() == 1L) 
			dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(requestDTO.getSiteId(), requestDTO.getUserId());

		Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(requestDTO.getDashboardId());
		String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
		String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
		String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
		String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(requestDTO.getUserId(), sameDataHierCol);
		
//		totalRecords = userReportsManager.getDirectReporteeAndUserReportTotalRecords(requestDTO, 
//				columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap,
//				sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
		
		dto = userReportsManager.getDirectReporteeAndUserReport(requestDTO, columnNameANDColumnDisplayNameAndSequenceMap,
				dataPrivacyColumnMap);
		
//		dto.setTotalRecords(totalRecords);
		dto.setColumnNameColumnDisplayNameSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
		
		//once report is generated, move the reportQueue to the reportQueueHistory and set processed = 1
		if(requestDTO.getIsExport() == 1L) {
			reportQueueHistoryManager.moveReportQueueRequestToHistory(reportQueue, startDate);
			reportQueueManager.remove(reportQueue.getId());
		}
		
		return dto;
	}
}
