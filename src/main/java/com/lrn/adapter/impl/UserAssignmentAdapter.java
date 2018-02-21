package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.IUserAssignmentAdapter;
import com.lrn.dto.AssignmentStatusForManagerDTO;
import com.lrn.dto.GetUserAssignmentDetailsForManagerDTO;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IUserAssignmentManager;
import com.lrn.manager.IUserManager;
import com.lrn.util.StringUtil;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class UserAssignmentAdapter implements IUserAssignmentAdapter {

	private IUserManager userManager;
	private IUserAssignmentManager userAssignmentManager;
	private IDashboardConfigurationManager dashboardConfigurationManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;

	public void setUserAssignmentManager(
			IUserAssignmentManager userAssignmentManager) {
		this.userAssignmentManager = userAssignmentManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}


	public void setDashboardConfigurationManager(
			IDashboardConfigurationManager dashboardConfigurationManager) {
		this.dashboardConfigurationManager = dashboardConfigurationManager;
	}
	
	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}

	
	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatus(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(siteId);
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(siteId, userId);
			
			return userAssignmentManager.get3rdChartUserCompletionStatusSuperUser(dashboardId, siteId, userId, 
					groupByColumnName, hasDashboardConfig, dataPrivacyColumnMap);
		
		} else if(hasDashboardConfig == 0) { //for regular users (hasDashboardConfig == 0)
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(userId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))   
				return userAssignmentManager
					.get3rdChartUserCompletionStatusRegularUserSameHierarchy(dashboardId, siteId, userId,
							groupByColumnName, hasDashboardConfig, sameDataHierCol, userColValueForSameDataHierCol);
			else if(supervisorHierCol != null || additionalHierCol != null)
				return userAssignmentManager
					.get3rdChartUserCompletionStatusRegularUser(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig);
			
		}
		return null;
	}

	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldown(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport) {

		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		groupByColumnValue = StringUtil.decodeRequestParameter(groupByColumnValue);
		filteredColumn = StringUtil.decodeRequestParameter(filteredColumn);
		filteredColumnValue = StringUtil.decodeRequestParameter(filteredColumnValue);
		
		GetUserAssignmentDetailsForManagerDTO dto = new GetUserAssignmentDetailsForManagerDTO();
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(dashboardId, 11l);
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			
			dto = userAssignmentManager.get3rdChartUserCompletionStatusDrilldownSuperUser(dashboardId, managerId, 
					groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, siteId,
					filteredColumn,filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
			
		//for regular users (hasDashboardConfig = 0)
		} else if(hasDashboardConfig == 0) {
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))  
				dto = userAssignmentManager
					.get3rdChartUserCompletionStatusDrilldownRegularUserSameHierarchy(dashboardId, managerId, groupByColumnName, groupByColumnValue,
							completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport,
							sameDataHierCol, userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);
			
			else if(supervisorHierCol != null || additionalHierCol != null)
				dto = userAssignmentManager
					.get3rdChartUserCompletionStatusDrilldownRegularUser(dashboardId, managerId, groupByColumnName, groupByColumnValue,
							completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport,
							columnNameANDColumnDisplayNameAndSequenceMap);
			}
		
	    dto.setColumnNameANDColumnDisplayNameAndSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
		return dto;
	}

}
