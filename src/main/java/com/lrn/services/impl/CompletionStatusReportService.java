package com.lrn.services.impl;

import java.util.List;

import com.lrn.adapter.ICompletionStatusReportAdapter;
import com.lrn.adapter.ICourseAdapter;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.CompletionStatusReportRequestDTO;
import com.lrn.dto.response.CompletionStatusReportResponseDTO;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.ICompletionStatusReportService;

public class CompletionStatusReportService implements ICompletionStatusReportService {

	public ICompletionStatusReportAdapter completionStatusReportAdapter;
	public ICourseAdapter courseAdapter;

	public void setCompletionStatusReportAdapter(
			ICompletionStatusReportAdapter completionStatusReportAdapter) {
		this.completionStatusReportAdapter = completionStatusReportAdapter;
	}
	
	public void setCourseAdapter(ICourseAdapter courseAdapter) {
		this.courseAdapter = courseAdapter;
	}

	@Override
	public LRNResponse getCompletionStatusReport(
			CompletionStatusReportRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			
			CompletionStatusReportResponseDTO dto = new CompletionStatusReportResponseDTO();
			List<GetCourseAssignmentStatusManagerDTO> data = courseAdapter
					.get4thChartCourseStatusDetails(requestDTO.getDashboardId(), requestDTO.getManagerId(), 
							requestDTO.getHasDashboardConfig(), requestDTO.getSiteId());
			dto.setData(data);
			
			//if all return data is empty (only row with all 0 in the counts)
			if(data != null && data.size() == 1 && data.get(0).isEmpty()) 
				dto.setTotalRecords(0L);
			else
				dto.setTotalRecords(new Long(data.size()));
			
			lrnResponse.setDataObject(dto);
			
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
	public LRNResponse getCompletionStatusReportDrilldown(
			DrilldownRequestDTO requestDTO) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {	
			lrnResponse.setDataObject(completionStatusReportAdapter
					.getCompletionStatusReportDrilldown(requestDTO));
			
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
