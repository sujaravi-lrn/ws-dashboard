package com.lrn.services.impl;

import com.lrn.adapter.IUserAssignmentAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IUserAssignmentService;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class UserAssignmentService implements IUserAssignmentService {

	private IUserAssignmentAdapter userAssignmentAdapter;
	
	public void setUserAssignmentAdapter(
			IUserAssignmentAdapter userAssignmentAdapter) {
		this.userAssignmentAdapter = userAssignmentAdapter;
	}

	@Override
	public LRNResponse get3rdChartUserCompletionStatus(Long dashboardId, Long siteId,
			String userId, String groupByColumnName, Long hasDashboardConfig) {

		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(userAssignmentAdapter
						.get3rdChartUserCompletionStatus(dashboardId, siteId, userId, groupByColumnName, hasDashboardConfig));
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
	public LRNResponse get3rdChartUserCompletionStatusDrilldown(Long dashboardId,
			String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport) {

		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(userAssignmentAdapter
						.get3rdChartUserCompletionStatusDrilldown(dashboardId, managerId, groupByColumnName, groupByColumnValue,
								completionStatus, hasDashboardConfig, siteId,filteredColumn,filteredColumnValue, isExport));
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
