package com.lrn.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dto.CourseAssignmentDetailsForPastDueDTO;
import com.lrn.dto.GetCourseAssignmentDetailsForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusGroupByForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;

public interface ICourseManager {

	List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusSuperUser(
			Long dashBoardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId, 
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUser(
			Long dashBoardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId);
	
	GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownSuperUser(
			Long dashBoardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);
	
	GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUser(
			Long dashBoardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashBoardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	
	List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUserSameHierarchy(
			Long dashBoardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId, 
			String dashboardConfig, String userColumnForDashboardConfig);


	List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesSuperUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig);
	
	List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig, 
			String dashboardConfig, String userColumnForDashboardConfig);

	
	GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);
	
	GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport, 
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsSuperUser(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId,
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUser(
			Long dashboardId, String managerId, Long hasDashboardConfig,
			Long siteId);

	List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUserSameHierarchy(
			Long dashboardId, String managerId, Long hasDashboardConfig,
			Long siteId, String dashboardConfig, String userColumnForDashboardConfig);

	GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

}
