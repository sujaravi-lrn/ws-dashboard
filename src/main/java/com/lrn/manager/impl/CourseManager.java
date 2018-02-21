package com.lrn.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dao.ICourseDao;
import com.lrn.dto.CourseAssignmentDetailsForPastDueDTO;
import com.lrn.dto.GetCourseAssignmentDetailsForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusGroupByForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;
import com.lrn.manager.ICourseManager;

public class CourseManager implements ICourseManager {

	private ICourseDao courseDao;

	public void setCourseDao(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}


	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusSuperUser(
			Long dashboardId, String managerId, String groupByColumnName,
			Long hasDashboardConfig, Long siteId, Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		return courseDao.get1stChartCourseCompletionStatusSuperUser(dashboardId, managerId, groupByColumnName, hasDashboardConfig, siteId, dataPrivacyColumnMap);
	}


	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUser(
			Long dashboardId, String managerId, String groupByColumnName,
			Long hasDashboardConfig, Long siteId) {

		return courseDao.get1stChartCourseCompletionStatusRegularUser(dashboardId, managerId, groupByColumnName, hasDashboardConfig, siteId);
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return courseDao.get1stChartCourseCompletionStatusDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, 
				columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return courseDao.get4thChartCourseStatusDetailsDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport,
				columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return courseDao.get4thChartCourseStatusDetailsDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport,
				columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return courseDao.get4thChartCourseStatusDetailsDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, 
				groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, 
				filteredColumnValue, isExport, dashboardConfig, userColumnForDashboardConfig, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return courseDao.get1stChartCourseCompletionStatusDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return courseDao.get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, dashboardConfig, 
				userColumnForDashboardConfig, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUserSameHierarchy(
			Long dashboardId, String managerId, String groupByColumnName, Long hasDashboardConfig, Long siteId,
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		return courseDao
				.get1stChartCourseCompletionStatusRegularUserSameHierarchy(dashboardId, managerId, groupByColumnName, hasDashboardConfig, 
						siteId, dashboardConfig, userColumnForDashboardConfig);
	}

	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesSuperUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return courseDao.get2ndChartIncompleteAndPastDueCoursesSuperUser(dashboardId, siteId, userId, 
				groupByColumnName, hasDashboardConfig, dataPrivacyColumnMap);
	}

	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig) {
		
		return courseDao.get2ndChartIncompleteAndPastDueCoursesRegularUser(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig);
	}
	
	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig, 
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		return courseDao.get2ndChartIncompleteAndPastDueCoursesRegularUserSameHierarchy(dashboardId, siteId, 
				userId, groupByColumnName, hasDashboardConfig, dashboardConfig, userColumnForDashboardConfig);
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
	
		return courseDao.get2ndChartIncompleteAndPastDueCoursesDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue,
				completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return courseDao.get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUser(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue,
				completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport, 
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return courseDao
				.get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, 
						groupByColumnName, groupByColumnValue,	completionStatus, hasDashboardConfig, filteredColumn, 
						filteredColumnValue, isExport, dashboardConfig, userColumnForDashboardConfig, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsSuperUser(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

			return courseDao.get4thChartCourseStatusDetailsSuperUser(dashboardId, managerId, 
					hasDashboardConfig, siteId, dataPrivacyColumnMap);
	}

	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUser(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId) {

			return courseDao.get4thChartCourseStatusDetailsRegularUser(dashboardId, managerId, hasDashboardConfig, siteId);
	}
	
	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUserSameHierarchy(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId,
			String dashboardConfig, String userColumnForDashboardConfig) {

			return courseDao.get4thChartCourseStatusDetailsRegularUserSameHierarchy(dashboardId, managerId, hasDashboardConfig, 
					siteId, dashboardConfig, userColumnForDashboardConfig);
	}
}
