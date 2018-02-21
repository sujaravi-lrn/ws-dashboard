package com.lrn.adapter;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.response.CompletionStatusReportDrilldownResponseDTO;


public interface ICompletionStatusReportAdapter {

	public CompletionStatusReportDrilldownResponseDTO 
		getCompletionStatusReportDrilldown(DrilldownRequestDTO requestDTO) ;

}
