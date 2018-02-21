package com.lrn.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsDrilldownResponseDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserCountsData;
import com.lrn.dto.response.ReporteeAndUserReportResponseDTO;

public interface IUserReportsManager {
	
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap);

	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol);

	
	
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap);

	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol);

	
	

	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getDirectReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO);
	
	public Long getTotalDirectReportees(ReporteeAndModuleCountsRequestDTO requestDTO, 
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	
	
	
//	public Long getDirectReporteeAndUserCountsTotalRecords(ReporteeAndUserCountsRequestDTO requestDTO);
	
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsSuperUser(
			DrilldownRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap);
	
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsRegularUser(
			DrilldownRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol);


	
	

	public Long getDirectReporteeAndUserReportTotalRecordsSuperUser(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap);
	
	public Long getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap, 
			Map<String, ArrayList<String>> dataPrivacyColumnMap,
			String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol);

	public ReporteeAndUserReportResponseDTO getDirectReporteeAndUserReport(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap);



}
