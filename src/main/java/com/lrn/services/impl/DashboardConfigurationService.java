package com.lrn.services.impl;

import com.lrn.adapter.IDashboardConfigurationAdapter;
import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IDashboardConfigurationService;

public class DashboardConfigurationService implements IDashboardConfigurationService{

	private IDashboardConfigurationAdapter dashboardConfigurationAdapter;

	public void setDashboardConfigurationAdapter(IDashboardConfigurationAdapter dashboardConfigurationAdapter) {
		this.dashboardConfigurationAdapter = dashboardConfigurationAdapter;
	}

	@Override
	public LRNResponse getHierarchySelection(Long siteId, Long dashBoardId) {
			
			LRNResponse lrnResponse = new LRNResponse();
			try {			
				lrnResponse.setDataObject(dashboardConfigurationAdapter.getHierarchySelection(siteId, dashBoardId));
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
	public LRNResponse saveHierarchySelection(Long siteId, Long dashboardId,
			String hierarchy1Value, String hierarchy1Updated,
			String hierarchy2Value, String hierarchy2Updated,
			String hierarchy3Value, String hierarchy3Updated, String userId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			dashboardConfigurationAdapter.saveHierarchySelection(siteId,  dashboardId,
					 hierarchy1Value,  hierarchy1Updated,
					 hierarchy2Value,  hierarchy2Updated,
					 hierarchy3Value,  hierarchy3Updated,  userId);
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
	public LRNResponse getChartSelection(Long siteId, Long dashboardId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardConfigurationAdapter.getChartSelection(siteId, dashboardId));
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
	public LRNResponse saveChartSelection(Long siteId, Long dashBoardId,
			String userId, String includedColumnsDisplayName,
			String columnsUpdated, String assignmentStatusSelected,
			String incompleteAndPastDueSelected,
			String courseCompletionSatusSelected,
			String courseCompletionStatusWithGroupBySelected,
			String statusReportCompletionSatusSelected, 
			String statusReportUserProgressSelected) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			dashboardConfigurationAdapter.saveChartSelection(siteId, dashBoardId,
					userId, includedColumnsDisplayName, columnsUpdated, assignmentStatusSelected, 
					incompleteAndPastDueSelected, courseCompletionSatusSelected, courseCompletionStatusWithGroupBySelected,
					statusReportCompletionSatusSelected, 
					statusReportUserProgressSelected);
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
	public LRNResponse getDrillDownChartSelection(Long siteId, Long dashBoardId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardConfigurationAdapter.getDrillDownChartSelection(siteId, dashBoardId));
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
	public LRNResponse saveDrillDownChartSelection(Long siteId, Long dashBoardId,
			String userId, String drillDownReportSelected,
			String includedColumnListForDrillDown,
			String includedColumnDisplayNameListForDrillDown,
			String includedColumnListForDrillDownUpdated,
			
			String drillDownUserSummaryDetailsSelected,
			String includedColumnListForSummaryDetailsDrillDown,
			String includedColumnDisplayNameListForSummaryDetailsDrillDown,
			String includedColumnListForSummaryDetailsDrillDownUpdated, 
			
			String statusReportCompletionStatusDrilldownSelected, 
			String includedColumnListForStatusReportCompletionStatusDrilldown, 
			String includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown, 
			String includedColumnListForStatusReportCompletionStatusDrilldownUpdated, 
			
			String statusReportUserProgressDrilldownSelected, 
			String includedColumnListForStatusReportUserProgressDrilldown, 
			String includedColumnDisplayNameListForStatusReportUserProgressDrilldown, 
			String includedColumnListForStatusReportUserProgressDrilldownUpdated) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			dashboardConfigurationAdapter.saveDrillDownChartSelection( siteId,  dashBoardId,
					 userId,  drillDownReportSelected,
					 includedColumnListForDrillDown,
					 includedColumnDisplayNameListForDrillDown,
					 includedColumnListForDrillDownUpdated,
					 
					 drillDownUserSummaryDetailsSelected,
					 includedColumnListForSummaryDetailsDrillDown,
					 includedColumnDisplayNameListForSummaryDetailsDrillDown,
					 includedColumnListForSummaryDetailsDrillDownUpdated,
					 
					 statusReportCompletionStatusDrilldownSelected, 
					 includedColumnListForStatusReportCompletionStatusDrilldown,
					 includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown,
					 includedColumnListForStatusReportCompletionStatusDrilldownUpdated,
					
					 statusReportUserProgressDrilldownSelected, 
					 includedColumnListForStatusReportUserProgressDrilldown,
					 includedColumnDisplayNameListForStatusReportUserProgressDrilldown,
					 includedColumnListForStatusReportUserProgressDrilldownUpdated);
		
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
	public LRNResponse saveShowExportReport(ShowExportReportDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardConfigurationAdapter.saveShowExportReport(requestDTO));
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
	public LRNResponse getShowExportReport(ShowExportReportDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(dashboardConfigurationAdapter.getShowExportReport(requestDTO));
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
