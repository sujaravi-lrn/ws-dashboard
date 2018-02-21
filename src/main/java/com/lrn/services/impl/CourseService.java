package com.lrn.services.impl;

import com.lrn.adapter.ICourseAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.ICourseService;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class CourseService implements ICourseService {

	private ICourseAdapter courseAdapter;
	
	public void setCourseAdapter(
			ICourseAdapter courseAdapter) {
		this.courseAdapter = courseAdapter;
	}

	@Override
	public LRNResponse get1stChartCourseCompletionStatusDrilldown(Long dashBoardId, Long siteId,
			String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(courseAdapter
					.get1stChartCourseCompletionStatusDrilldown(dashBoardId, siteId, managerId, groupByColumnName, groupByColumnValue, completionStatus,
							hasDashBoardConfig, filteredColumn, filteredColumnValue, isExport));
			
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

	@Override
	public LRNResponse get1stChartCourseCompletionStatus(Long dashBoardId,
			String managerId, String groupByColumnName,Long hasDashBoardConfig,Long siteId) {

		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(courseAdapter
					.get1stChartCourseCompletionStatus(dashBoardId, managerId, groupByColumnName, hasDashBoardConfig, siteId));
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}
	
	@Override
	public LRNResponse get2ndChartIncompleteAndPastDueCourses(Long dashboardId, Long siteId,
			String userId, String groupByColumnName, Long hasDashboardConfig) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {
			lrnResponse.setDataObject(courseAdapter
					.get2ndChartIncompleteAndPastDueCourses(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig));
			lrnResponse.setSuccess(true);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

	@Override
	public LRNResponse get2ndChartIncompleteAndPastDueCoursesDrilldown(Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {
			lrnResponse.setDataObject(courseAdapter
					.get2ndChartIncompleteAndPastDueCoursesDrilldown(dashboardId, siteId, managerId, groupByColumnName, groupByColumnValue,
							completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport));
			lrnResponse.setSuccess(true);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

	@Override
	public LRNResponse get4thChartCourseStatusDetails(Long dashboardId,
			String managerId, Long hasDashboardConfig, Long siteId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {
			lrnResponse.setDataObject(courseAdapter
					.get4thChartCourseStatusDetails(dashboardId, managerId, hasDashboardConfig, siteId));
			lrnResponse.setSuccess(true);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

	@Override
	public LRNResponse get4thChartCourseStatusDetailsDrilldown(Long dashboardId,
			Long siteId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, String filteredColumn,
			String filteredColumnValue, Long isExport) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {
			lrnResponse.setDataObject(courseAdapter
					.get4thChartCourseStatusDetailsDrilldown(dashboardId, siteId, managerId, groupByColumnName,
							groupByColumnValue, completionStatus, hasDashboardConfig,filteredColumn,filteredColumnValue, isExport));
			lrnResponse.setSuccess(true);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}
}
