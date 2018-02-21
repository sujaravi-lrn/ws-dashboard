package com.lrn.services.impl;

import com.lrn.adapter.IReportQueueAdapter;
import com.lrn.adapter.IUserReportsAdapter;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReportQueueRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IUserReportsService;

public class UserReportsService implements IUserReportsService {

	public IUserReportsAdapter userReportsAdapter;
	public IReportQueueAdapter reportQueueAdapter;
	
	public void setUserReportsAdapter(IUserReportsAdapter userReportsAdapter) {
		this.userReportsAdapter = userReportsAdapter;
	}

	public void setReportQueueAdapter(IReportQueueAdapter reportQueueAdapter) {
		this.reportQueueAdapter = reportQueueAdapter;
	}


	@Override
	public LRNResponse getDirectReporteeCounts(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeCounts(requestDTO));
			
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
	public LRNResponse getReporteeAndModuleCounts(ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getReporteeAndModuleCounts(requestDTO));
			
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
	public LRNResponse getReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getReporteeAndModuleCountsDrilldown(requestDTO));
			
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
	public LRNResponse getDirectReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeAndModuleCountsDrilldown(requestDTO));
			
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
	public LRNResponse getDirectReporteeAndUserCountsTotalRecords(
			DrilldownRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeAndUserCountsTotalRecords(requestDTO));
			
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
	public LRNResponse getDirectReporteeAndUserCounts(
			DrilldownRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeAndUserCounts(requestDTO));
			
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
	public LRNResponse getDirectReporteeAndUserReportTotalRecords(
			DrilldownRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeAndUserReportTotalRecords(requestDTO));
			
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
	public LRNResponse getDirectReporteeAndUserReport(
			DrilldownRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(userReportsAdapter
					.getDirectReporteeAndUserReport(requestDTO));
			
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
	public LRNResponse getReportQueueHistory(ReportQueueRequestDTO requestDTO) {
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(reportQueueAdapter.getReportQueueHistory(
					requestDTO.getSiteId(), requestDTO.getDashboardId(), requestDTO.getUserId()));
			
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
