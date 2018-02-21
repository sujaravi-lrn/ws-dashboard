package com.lrn.services;

import com.lrn.model.LRNResponse;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface IUserAssignmentService {

	LRNResponse get3rdChartUserCompletionStatus(
			Long dashBoardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig);

	LRNResponse get3rdChartUserCompletionStatusDrilldown(Long dashboardId,
			String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport);

}
