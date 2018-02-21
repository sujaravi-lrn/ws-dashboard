package com.lrn.manager.impl;

import java.util.ArrayList;
import java.util.Map;

import com.lrn.dao.ICompletionStatusReportDao;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.response.CompletionStatusReportDrilldownResponseDTO;
import com.lrn.manager.ICompletionStatusReportManager;

public class CompletionStatusReportManager implements ICompletionStatusReportManager {
	
	private ICompletionStatusReportDao completionStatusReportDao;
	
	public void setCompletionStatusReportDao(
			ICompletionStatusReportDao completionStatusReportDao) {
		this.completionStatusReportDao = completionStatusReportDao;
	}


	@Override
	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownSuperUser(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return completionStatusReportDao.getCompletionStatusReportDrilldownSuperUser(requestDTO, 
							columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
	}

	@Override
	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownRegularUser(
			DrilldownRequestDTO requestDTO, 
			String sameDataHierCol, String supervisorHierCol, String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return completionStatusReportDao.getCompletionStatusReportDrilldownRegularUser(requestDTO, 
							sameDataHierCol, supervisorHierCol, additionalHierCol,
							userColValueForSameDataHierCol, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	
	

	@Override
	public Long getCompletionStatusReportDrilldownSuperUserTotalRecords(DrilldownRequestDTO requestDTO,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		return completionStatusReportDao.getCompletionStatusReportDrilldownSuperUserTotalRecords(requestDTO,
				dataPrivacyColumnMap);
	}

	@Override
	public Long getCompletionStatusReportDrilldownRegularUserTotalRecords(DrilldownRequestDTO requestDTO, 
			String sameDataHierCol, String supervisorHierCol, String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return completionStatusReportDao.getCompletionStatusReportDrilldownRegularUserTotalRecords(requestDTO,
				sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol,
				columnNameANDColumnDisplayNameAndSequenceMap);
	}

}
