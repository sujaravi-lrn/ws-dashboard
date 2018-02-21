package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsDrilldownResponseDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserReportResponseDTO;

public interface IUserReportsAdapter {
	
	ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCounts(
			ReporteeAndModuleCountsRequestDTO requestDTO);
	
	List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO);

	List<ReporteeAndModuleCountsDrilldownResponseDTO> getDirectReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO);

	ReporteeAndUserCountsResponseDTO getDirectReporteeAndUserCounts(
			DrilldownRequestDTO requestDTO);

	ReporteeAndUserReportResponseDTO getDirectReporteeAndUserReport(
			DrilldownRequestDTO requestDTO);

	Long getDirectReporteeCounts(ReporteeAndModuleCountsRequestDTO requestDTO);

	Long getDirectReporteeAndUserCountsTotalRecords(DrilldownRequestDTO requestDTO);

	Long getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO);
}
