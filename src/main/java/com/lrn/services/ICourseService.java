package com.lrn.services;

import com.lrn.model.LRNResponse;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface ICourseService {
	
	LRNResponse get1stChartCourseCompletionStatus(Long dashBoardId,
			String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId);

	LRNResponse get1stChartCourseCompletionStatusDrilldown(Long dashBoardId, Long siteId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport);

	LRNResponse get2ndChartIncompleteAndPastDueCourses(Long dashBoardId, Long siteId,
			String userId, String groupByColumnName, Long hasDashBoardConfig);

	LRNResponse get2ndChartIncompleteAndPastDueCoursesDrilldown(Long dashboardId,
			Long siteId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, String filteredColumn,
			String filteredColumnValue, Long isExport);

	LRNResponse get4thChartCourseStatusDetails(Long dashboardId,
			String managerId, Long hasDashboardConfig, Long siteId);

	LRNResponse get4thChartCourseStatusDetailsDrilldown(Long dashboardId,
			Long siteId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, String filteredColumn,
			String filteredColumnValue, Long isExport);
}
