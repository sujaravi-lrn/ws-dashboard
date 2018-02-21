package com.lrn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dto.AssignmentStatusForManagerDTO;
import com.lrn.dto.GetUserAssignmentDetailsForManagerDTO;
import com.lrn.model.FactModuleStatus;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface IUserAssignmentDao extends IGenericDao<FactModuleStatus, Long> {

	List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusSuperUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig, Map<String, ArrayList<String>> dataPrivacyColumnMap);

	GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownSuperUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig);
	
	List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			String dashboardConfig, String userColumnForDashboardConfig);

	GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, String dashboardConfig,
			String userColumnForDashboardConfig, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

}
