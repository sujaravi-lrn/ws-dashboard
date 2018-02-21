package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.CourseAssignmentDetailsForPastDueDTO;
import com.lrn.dto.GetCourseAssignmentDetailsForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusGroupByForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface ICourseAdapter {

	List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatus(
			Long dashboardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId);

	List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetails(Long dashboardId,
			String managerId, Long hasDashboardConfig, Long siteId);
	
	GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldown(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig, String filteredColumn, String filteredColumnValue, Long isExport);

	GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldown(Long dashboardId,
			Long siteId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, String filteredColumn,
			String filteredColumnValue, Long isExport);

	List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCourses(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig);
	
	GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldown(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport);

}
