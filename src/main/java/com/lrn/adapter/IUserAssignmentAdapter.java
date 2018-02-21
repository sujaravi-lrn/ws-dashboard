package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.AssignmentStatusForManagerDTO;
import com.lrn.dto.GetUserAssignmentDetailsForManagerDTO;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface IUserAssignmentAdapter {

	List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatus(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig);

	GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldown(Long dashboardId,
			String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport);

}
