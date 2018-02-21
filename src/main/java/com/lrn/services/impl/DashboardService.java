package com.lrn.services.impl;

import com.lrn.adapter.IDashboardAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IDashboardService;

public class DashboardService implements IDashboardService {

	private IDashboardAdapter dashboardAdapter;
	
	public void setDashboardAdapter(IDashboardAdapter dashboardAdapter) {
		this.dashboardAdapter = dashboardAdapter;
	}

	@Override
	public LRNResponse getDashboardConfigurationForSite(Long siteId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getDashboardConfigurationForSite(siteId));
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
	public LRNResponse getDashboardForSite(Long siteId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getDashboardForSite(siteId));
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
	public LRNResponse saveDashboard(Long siteId, String userId, Long active) {

		LRNResponse lrnResponse = new LRNResponse();
		try {
			lrnResponse.setDataObject(dashboardAdapter.saveDashboard(siteId, userId, active));
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
	public LRNResponse saveDashboardConfiguration(Long siteId, Long active, String userID, Long dashBoardId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			dashboardAdapter.saveDashboardConfiguration(siteId, active, userID, dashBoardId);
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
	public LRNResponse getDashboardConfigsForCharts(Long siteId) {
	
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getDashboardConfigsForCharts(siteId));
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
	public LRNResponse getGroupByDashboardSelectionCriteria(Long dashboardId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getGroupByDashboardSelectionCriteria(dashboardId));
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
	public LRNResponse getDashboardById(Long dashboardId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getDashboardById(dashboardId));
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
	public LRNResponse getDashboardViewSettings(Long dashboardId, String userId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getDashboardViewSettings(dashboardId, userId));
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
	public LRNResponse getLastUpdatedDate(Long siteId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardAdapter.getLastUpdatedDate(siteId));
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
