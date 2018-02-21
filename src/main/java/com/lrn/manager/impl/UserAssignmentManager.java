package com.lrn.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IUserAssignmentDao;
import com.lrn.dto.AssignmentStatusForManagerDTO;
import com.lrn.dto.GetUserAssignmentDetailsForManagerDTO;
import com.lrn.manager.IUserAssignmentManager;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class UserAssignmentManager implements IUserAssignmentManager {

	private IUserAssignmentDao userAssignmentDao;
	
	public void setUserAssignmentDao(IUserAssignmentDao userAssignmentDao) {
		this.userAssignmentDao = userAssignmentDao;
	}

	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusSuperUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig, Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userAssignmentDao.get3rdChartUserCompletionStatusSuperUser(dashboardId, siteId, userId, 
				groupByColumnName, hasDashboardConfig, dataPrivacyColumnMap);
	}

	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownSuperUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return userAssignmentDao
				.get3rdChartUserCompletionStatusDrilldownSuperUser(dashboardId, managerId, groupByColumnName, groupByColumnValue,
						completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return userAssignmentDao
				.get3rdChartUserCompletionStatusDrilldownRegularUser(dashboardId, managerId, groupByColumnName, groupByColumnValue,
						completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	
	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return userAssignmentDao
				.get3rdChartUserCompletionStatusDrilldownRegularUserSameHierarchy(dashboardId, managerId, groupByColumnName, groupByColumnValue,
						completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport,
						dashboardConfig, userColumnForDashboardConfig, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig) {
		
		return userAssignmentDao
				.get3rdChartUserCompletionStatusRegularUser(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig);
	}

	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		return userAssignmentDao
				.get3rdChartUserCompletionStatusRegularUserSameHierarchy(dashboardId, siteId, userId, 
						groupByColumnName, hasDashboardConfig, dashboardConfig, userColumnForDashboardConfig);
	}

}
