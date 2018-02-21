package com.lrn.services;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.CompletionStatusReportRequestDTO;
import com.lrn.model.LRNResponse;

public interface ICompletionStatusReportService {

	public LRNResponse getCompletionStatusReport(
				CompletionStatusReportRequestDTO requestDTO);

	public LRNResponse getCompletionStatusReportDrilldown(
			DrilldownRequestDTO requestDTO);

}
