package com.lrn.manager;

import java.util.ArrayList;
import java.util.Map;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.response.CompletionStatusReportDrilldownResponseDTO;

public interface ICompletionStatusReportManager {

	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownSuperUser(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownRegularUser(
			DrilldownRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	
	
	public Long getCompletionStatusReportDrilldownSuperUserTotalRecords(DrilldownRequestDTO requestDTO, 
			Map<String, ArrayList<String>> dataPrivacyColumnMap);

	public Long getCompletionStatusReportDrilldownRegularUserTotalRecords(DrilldownRequestDTO requestDTO, 
			String sameDataHierCol, String supervisorHierCol, String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap);

	
}

