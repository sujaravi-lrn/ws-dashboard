package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.ICourseAdapter;
import com.lrn.dto.CourseAssignmentDetailsForPastDueDTO;
import com.lrn.dto.GetCourseAssignmentDetailsForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusGroupByForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;
import com.lrn.manager.ICourseManager;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IUserManager;
import com.lrn.util.StringUtil;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class CourseAdapter implements ICourseAdapter {

	private IUserManager userManager;
	private ICourseManager courseManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	private IDashboardConfigurationManager dashboardConfigurationManager;
	
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public void setCourseManager(
			ICourseManager courseManager) {
		this.courseManager = courseManager;
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
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatus(
			Long dashboardId, String managerId, String groupByColumnName,
			Long hasDashboardConfig, Long siteId) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(siteId);
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(siteId, managerId);
			
			return courseManager
					.get1stChartCourseCompletionStatusSuperUser(dashboardId, managerId, groupByColumnName, hasDashboardConfig, siteId, dataPrivacyColumnMap);
		
		//for regular users (hasDashboardConfig = 0)
		} else if(hasDashboardConfig == 0) {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))  
				return courseManager
						.get1stChartCourseCompletionStatusRegularUserSameHierarchy(dashboardId, managerId, groupByColumnName, 
								hasDashboardConfig, siteId, sameDataHierCol, userColValueForSameDataHierCol);
			else if(supervisorHierCol != null || additionalHierCol != null) 
				return courseManager
						.get1stChartCourseCompletionStatusRegularUser(dashboardId, managerId, groupByColumnName, hasDashboardConfig, siteId);
			
		}
			
		return null;
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldown(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		groupByColumnValue = StringUtil.decodeRequestParameter(groupByColumnValue);
		filteredColumn = StringUtil.decodeRequestParameter(filteredColumn);
		filteredColumnValue = StringUtil.decodeRequestParameter(filteredColumnValue);
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(dashboardId, 10l);
		
		if(hasDashboardConfig == 1) {
			
			//List<String> dataPrivacyColumnList = userManager.getDataPrivacyUserColumns(siteId, managerId, groupByColumnName);
			dto = courseManager
					.get1stChartCourseCompletionStatusDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue, 
							completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, 
							columnNameANDColumnDisplayNameAndSequenceMap);
			
		} else if(hasDashboardConfig == 0) {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))  
				dto = courseManager
					.get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue, 
						completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, 
						isExport, sameDataHierCol, userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);
			else if(supervisorHierCol != null || additionalHierCol != null) 
				dto = courseManager
					.get1stChartCourseCompletionStatusDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue, 
						completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
			
		}

		dto.setColumnNameANDColumnDisplayNameAndSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
		return dto;
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldown(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		groupByColumnValue = StringUtil.decodeRequestParameter(groupByColumnValue);
		filteredColumn = StringUtil.decodeRequestParameter(filteredColumn);
		filteredColumnValue = StringUtil.decodeRequestParameter(filteredColumnValue);
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(dashboardId, 10l);
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			
			dto = courseManager.get4thChartCourseStatusDetailsDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue, 
						completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
		
		//for Regular Users (hasDashboardConfig = 0)
		} else if(hasDashboardConfig == 0) {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))  
				dto = courseManager.get4thChartCourseStatusDetailsDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, 
						groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, 
						filteredColumnValue, isExport, sameDataHierCol, userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);

			else if(supervisorHierCol != null || additionalHierCol != null) 
				dto = courseManager.get4thChartCourseStatusDetailsDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue, 
					completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
			
		}
			
	    dto.setColumnNameANDColumnDisplayNameAndSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
		return dto;
	}
	
	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCourses(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		
		List<CourseAssignmentDetailsForPastDueDTO> dtoList = new ArrayList<CourseAssignmentDetailsForPastDueDTO> ();
		Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
		String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
		String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
		String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
		String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(userId, sameDataHierCol);

		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(siteId);
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite)
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(siteId, userId);
			
			dtoList = courseManager.get2ndChartIncompleteAndPastDueCoursesSuperUser(dashboardId, siteId, userId, 
					groupByColumnName, hasDashboardConfig, dataPrivacyColumnMap);
		
		} else if(hasDashboardConfig == 0) { //for regular user(hasDashboardConfig = 0)
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))
				dtoList = courseManager
				.get2ndChartIncompleteAndPastDueCoursesRegularUserSameHierarchy(dashboardId, siteId, userId, 
						groupByColumnName, hasDashboardConfig, sameDataHierCol, userColValueForSameDataHierCol);
			else if(supervisorHierCol != null || additionalHierCol != null) 
				dtoList = courseManager.get2ndChartIncompleteAndPastDueCoursesRegularUser(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig);
			
		}
		
		return dtoList;
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldown(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport) {
		
		groupByColumnName = StringUtil.decodeRequestParameter(groupByColumnName);
		groupByColumnValue = StringUtil.decodeRequestParameter(groupByColumnValue);
		filteredColumn = StringUtil.decodeRequestParameter(filteredColumn);
		filteredColumnValue = StringUtil.decodeRequestParameter(filteredColumnValue);
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = 
	    		dashboardSelectionCriteriaManager.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(dashboardId, 10l);
	    
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			dto = courseManager
					.get2ndChartIncompleteAndPastDueCoursesDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, 
							groupByColumnValue,	completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport,
							columnNameANDColumnDisplayNameAndSequenceMap);
		
		} else if(hasDashboardConfig == 0) { //for regular user(hasDashboardConfig = 0)
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))
				dto = courseManager
						.get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, 
						groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, 
						filteredColumnValue, isExport, sameDataHierCol, userColValueForSameDataHierCol,
						columnNameANDColumnDisplayNameAndSequenceMap);

			else if(supervisorHierCol != null || additionalHierCol != null) 
				dto = courseManager
						.get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, 
						groupByColumnValue,	completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport,
						columnNameANDColumnDisplayNameAndSequenceMap);
			
		}

		dto.setColumnNameANDColumnDisplayNameAndSequenceMap(columnNameANDColumnDisplayNameAndSequenceMap);
	    return dto;
	}

	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetails(
			Long dashboardId, String managerId, Long hasDashboardConfig,
			Long siteId) {
		
		//for Manager/SuperUsers (hasDashboardConfig = 1)
		if(hasDashboardConfig == 1) {
			
			boolean isDataPrivacySetupForSite = userManager.isDataPrivacySetupForSite(siteId);
			Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
			if(isDataPrivacySetupForSite) 
				dataPrivacyColumnMap = userManager.getDataPrivacyUserColumns(siteId, managerId); 
			
			return courseManager.get4thChartCourseStatusDetailsSuperUser(dashboardId, managerId, 
						hasDashboardConfig, siteId, dataPrivacyColumnMap);
		
		//for Regular Users (hasDashboardConfig = 0)
		} else if(hasDashboardConfig == 0) {
			
			Map<String, String> hierConfigMap = dashboardConfigurationManager.getDashboardHierarchyConfigs(dashboardId);
			String sameDataHierCol = hierConfigMap.get("Same_Data_Hierarchy");
			String supervisorHierCol = hierConfigMap.get("Supervisor_Hierarchy");
			String additionalHierCol = hierConfigMap.get("Additional_Hierarchy");
			String userColValueForSameDataHierCol = userManager.getUserColumnForDashboardConfig(managerId, sameDataHierCol);
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))
				return courseManager.get4thChartCourseStatusDetailsRegularUserSameHierarchy(dashboardId, managerId, hasDashboardConfig, 
						siteId, sameDataHierCol, userColValueForSameDataHierCol);

			else if(supervisorHierCol != null || additionalHierCol != null) 
				return courseManager.get4thChartCourseStatusDetailsRegularUser(dashboardId, managerId, hasDashboardConfig, siteId);
			
		}	
		return null;
	}

}
