package com.lrn.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IUserReportsDao;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsDrilldownResponseDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserCountsData;
import com.lrn.dto.response.ReporteeAndUserReportResponseDTO;
import com.lrn.manager.IUserReportsManager;

public class UserReportsManager implements IUserReportsManager {
	
	public IUserReportsDao userReportsDao;
	
	public void setUserReportsDao(IUserReportsDao userReportsDao) {
		this.userReportsDao = userReportsDao;
	}
	
	

	@Override
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userReportsDao.getReporteeAndModuleCountsSuperUser(requestDTO, dataPrivacyColumnMap);
	}

	
	@Override
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {
		
		return userReportsDao.getReporteeAndModuleCountsRegularUser(requestDTO, sameDataHierCol,
				supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
	}
	
	@Override
	public Long getTotalDirectReportees(ReporteeAndModuleCountsRequestDTO requestDTO,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		return userReportsDao.getTotalDirectReportees(requestDTO, dataPrivacyColumnMap);
	}
	
	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userReportsDao.getReporteeAndModuleCountsDrilldownSuperUser(requestDTO, dataPrivacyColumnMap);
	}


	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {
		
		return userReportsDao.getReporteeAndModuleCountsDrilldownRegularUser(requestDTO, sameDataHierCol,
					supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
	}

	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getDirectReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO) {
		return userReportsDao.getDirectReporteeAndModuleCountsDrilldown(requestDTO);
	}
	
	@Override
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsSuperUser(
			DrilldownRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userReportsDao.getDirectReporteeAndUserCountsSuperUser(requestDTO, dataPrivacyColumnMap);
	}
	
	@Override
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsRegularUser(
			DrilldownRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {
		
		return userReportsDao.getDirectReporteeAndUserCountsRegularUser(requestDTO,
				sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);
	}

	@Override
	public ReporteeAndUserReportResponseDTO getDirectReporteeAndUserReport(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userReportsDao.getDirectReporteeAndUserReport(requestDTO, 
				columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
	}

	@Override
	public Long getDirectReporteeAndUserReportTotalRecordsSuperUser(DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		return userReportsDao.getDirectReporteeAndUserReportTotalRecordsSuperUser(requestDTO, 
				columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap);
	}
	
	@Override
	public Long getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap,
			String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {
		
		return userReportsDao.getDirectReporteeAndUserReportTotalRecords(requestDTO, 
				columnNameANDColumnDisplayNameAndSequenceMap, dataPrivacyColumnMap,
				sameDataHierCol, supervisorHierCol, additionalHierCol, userColValueForSameDataHierCol);

	}

	/*@Override
	public Long getDirectReporteeAndUserCountsTotalRecords(ReporteeAndUserCountsRequestDTO requestDTO) {
		return userReportsDao.getDirectReporteeAndUserCountsTotalRecords(requestDTO);

	}*/
}
