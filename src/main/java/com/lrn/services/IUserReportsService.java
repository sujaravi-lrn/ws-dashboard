package com.lrn.services;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReportQueueRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.model.LRNResponse;

public interface IUserReportsService {
	
	public LRNResponse getReporteeAndModuleCounts(ReporteeAndModuleCountsRequestDTO requestDTO);
	
	public LRNResponse getReporteeAndModuleCountsDrilldown(ReporteeAndModuleCountsRequestDTO requestDTO);

	public LRNResponse getDirectReporteeAndModuleCountsDrilldown(ReporteeAndModuleCountsRequestDTO requestDTO);

	public LRNResponse getDirectReporteeAndUserCounts(DrilldownRequestDTO requestDTO);

	public LRNResponse getDirectReporteeAndUserReport(DrilldownRequestDTO requestDTO);

	public LRNResponse getReportQueueHistory(ReportQueueRequestDTO requestDTO);
	
	public LRNResponse getDirectReporteeCounts(ReporteeAndModuleCountsRequestDTO requestDTO);

	public LRNResponse getDirectReporteeAndUserCountsTotalRecords(DrilldownRequestDTO requestDTO);

	public LRNResponse getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO);

}
