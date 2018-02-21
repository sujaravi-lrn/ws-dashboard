package com.lrn.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lrn.constants.ModuleCourseStatusDetailMap;
import com.lrn.constants.ModuleCourseStatusMap;
import com.lrn.constants.UserReportsColumnMap;
import com.lrn.dao.IUserReportsDao;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsDrilldownResponseDTO;
import com.lrn.dto.response.ReporteeAndModuleCountsResponseDTO;
import com.lrn.dto.response.ReporteeAndUserCountsData;
import com.lrn.dto.response.ReporteeAndUserReportResponseDTO;
import com.lrn.model.FactModuleStatus;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

public class UserReportsDao extends	GenericDao<FactModuleStatus, Long> 
			implements IUserReportsDao {

	private static final Logger logger = Logger.getLogger(UserReportsDao.class);

	public UserReportsDao(Class<FactModuleStatus> persistentClass) {
		super(persistentClass);
	}
	
	@Override
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT count(distinct USER_ID) as total_reportees, ");
			queryBuilder.append("count(distinct MODULE_ID) as total_modules FROM ");
			queryBuilder.append("MV_FACT_MODULE_STATUS fad ");
			queryBuilder.append("WHERE fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory' ");
			//add the completionStatus filter
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
			if(dataPrivacyColumnMap.size() > 0) {
				Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
				while(keySetIterator.hasNext()) {
					String columnName = keySetIterator.next();
					List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
					String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
					queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
				}
			}
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getReporteeAndModuleCountsSuperUser :\n" + queryBuilder.toString());
		logger.debug("getReporteeAndModuleCountsSuperUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		
		ReporteeAndModuleCountsResponseDTO responseDTO = new ReporteeAndModuleCountsResponseDTO();
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setTotalMandatoryModuleCounts(DBUtils.getLongFromResultMapObject(resultMap.get("total_modules")));
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");
		
		return responseDTO;
	}

	@Override
	public ReporteeAndModuleCountsResponseDTO getReporteeAndModuleCountsRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {

		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT count(distinct fad.USER_ID) as total_reportees, ");
			queryBuilder.append("count(distinct MODULE_ID) as total_modules ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad WHERE ");

			queryBuilder.append("fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE ='Mandatory' ");
			
			queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE MANAGER_ID = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null)
					&& !requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
					queryBuilder.append("or fad.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
			queryBuilder.append(") ");
				
			//add the completionStatus filter
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getDashboardId());	
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getReporteeAndModuleCountsRegularUser :\n" + queryBuilder.toString());
		logger.debug("getReporteeAndModuleCountsRegularUser Query Took :: " + (endTime-startTime)/1000 + " sec");

		ReporteeAndModuleCountsResponseDTO responseDTO = new ReporteeAndModuleCountsResponseDTO();
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setTotalMandatoryModuleCounts(DBUtils.getLongFromResultMapObject(resultMap.get("total_modules")));
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return responseDTO;
	}
	
	@Override
	public Long getTotalDirectReportees(ReporteeAndModuleCountsRequestDTO requestDTO,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT COUNT(DISTINCT duh.USER_ID) as total_direct_reportees ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad, DIM_USER_HIERARCHY DUH ");
			queryBuilder.append("WHERE fad.USER_ID = DUH.user_id ");
			queryBuilder.append("AND fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND duh.MANAGER_ID = ? ");
			queryBuilder.append("and duh.PATH_LENGTH = 1 ");
			//add the completionStatus filter
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
			if(dataPrivacyColumnMap.size() > 0) {
				Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
				while(keySetIterator.hasNext()) {
					String columnName = keySetIterator.next();
					List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
					String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
					queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
				}
			}
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getTotalDirectReportees :\n" + queryBuilder.toString());
		logger.debug("getTotalDirectReportees Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	return DBUtils.getLongFromResultMapObject(resultMap.get("total_direct_reportees"));
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return 0L;
	}
	
	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownSuperUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT base_catalog_id, module_title, ");
			queryBuilder.append("count(distinct USER_ID) as total_reportees, ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1, user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1, user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad ");
			queryBuilder.append("WHERE fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory' ");
			
			if(dataPrivacyColumnMap.size() > 0) {
				Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
				while(keySetIterator.hasNext()) {
					String columnName = keySetIterator.next();
					List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
					String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
					queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
				}
			}
			
			queryBuilder.append("group by base_catalog_id, module_title");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getReporteeAndModuleCountsDrilldownSuperUser :\n" + queryBuilder.toString());
		logger.debug("getReporteeAndModuleCountsDrilldownSuperUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		
		List<ReporteeAndModuleCountsDrilldownResponseDTO> dtoList = new ArrayList<ReporteeAndModuleCountsDrilldownResponseDTO> ();
		
		while(itr.hasNext()) {
			ReporteeAndModuleCountsDrilldownResponseDTO responseDTO = new ReporteeAndModuleCountsDrilldownResponseDTO();

			Map<String, Object> resultMap = itr.next();
	    	responseDTO.setBaseCatalogId((String) resultMap.get("base_catalog_id"));
	    	responseDTO.setModuleTitle((String) resultMap.get("module_title"));
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setCompleteCount(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    	Long incompleteCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT"));
	    	Long pastDueCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE"));
	    	responseDTO.setIncompleteCount(incompleteCount + pastDueCount);
	    	
	    	dtoList.add(responseDTO);
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return dtoList;
	}

	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getReporteeAndModuleCountsDrilldownRegularUser(
			ReporteeAndModuleCountsRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {

		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT base_catalog_id, module_title, ");
			queryBuilder.append("count(distinct fad.USER_ID) as total_reportees, ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1, fad.user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1, fad.user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE = 1 or MOD_IN_PROCESS_PAST_DUE =1 ) then fad.user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad WHERE ");
			queryBuilder.append("fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE ='Mandatory' ");
			
			queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE MANAGER_ID = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null)
					&& !requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
					queryBuilder.append("or fad.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
				queryBuilder.append(") ");
			
			queryBuilder.append("group by base_catalog_id, module_title");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getDashboardId());	

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getReporteeAndModuleCountsDrilldownRegularUser :\n" + queryBuilder.toString());
		logger.debug("getReporteeAndModuleCountsDrilldownRegularUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

		List<ReporteeAndModuleCountsDrilldownResponseDTO> dtoList = new ArrayList<ReporteeAndModuleCountsDrilldownResponseDTO> ();
		
		while(itr.hasNext()) {
			ReporteeAndModuleCountsDrilldownResponseDTO responseDTO = new ReporteeAndModuleCountsDrilldownResponseDTO();

			Map<String, Object> resultMap = itr.next();
	    	responseDTO.setBaseCatalogId((String) resultMap.get("base_catalog_id"));
	    	responseDTO.setModuleTitle((String) resultMap.get("module_title"));
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setCompleteCount(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    	Long incompleteCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT"));
	    	Long pastDueCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE"));
	    	responseDTO.setIncompleteCount(incompleteCount + pastDueCount);

	    	dtoList.add(responseDTO);
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return dtoList;
	}
	

	@Override
	public List<ReporteeAndModuleCountsDrilldownResponseDTO> getDirectReporteeAndModuleCountsDrilldown(
			ReporteeAndModuleCountsRequestDTO requestDTO) {

		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT base_catalog_id, module_title, ");
			queryBuilder.append("count(distinct duh.USER_ID) as total_reportees, ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1, duh.user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1, duh.user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then duh.user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad, DIM_USER_HIERARCHY DUH ");
			queryBuilder.append("WHERE fad.USER_ID = DUH.user_id ");
			queryBuilder.append("AND fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND duh.MANAGER_ID = ? ");
			queryBuilder.append("and duh.PATH_LENGTH = 1 ");
			queryBuilder.append("group by base_catalog_id, module_title");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getDirectReporteeAndModuleCountsDrilldown :\n" + queryBuilder.toString());
		logger.debug("getDirectReporteeAndModuleCountsDrilldown Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

		List<ReporteeAndModuleCountsDrilldownResponseDTO> dtoList = new ArrayList<ReporteeAndModuleCountsDrilldownResponseDTO> ();
		while(itr.hasNext()) {
			ReporteeAndModuleCountsDrilldownResponseDTO responseDTO = new ReporteeAndModuleCountsDrilldownResponseDTO();
	    	Map<String, Object> resultMap = itr.next();
	    	responseDTO.setBaseCatalogId((String) resultMap.get("base_catalog_id"));
	    	responseDTO.setModuleTitle((String) resultMap.get("module_title"));
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setCompleteCount(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    	Long incompleteCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT"));
	    	Long pastDueCount = DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE"));
	    	responseDTO.setIncompleteCount(incompleteCount + pastDueCount);
	    	dtoList.add(responseDTO);
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return dtoList;
	}
	

	/*@Override
	public Long getDirectReporteeAndUserCountsTotalRecords(ReporteeAndUserCountsRequestDTO requestDTO) {

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT COUNT(*) as total_records FROM");
		queryBuilder.append(" (SELECT duh.USER_ID, FIRSTNAME,LASTNAME ");
		queryBuilder.append(" FROM MV_FACT_MODULE_STATUS fad, DIM_USER_HIERARCHY DUH");
		queryBuilder.append(" WHERE fad.DASHBOARD_ID = ?");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory'");
		queryBuilder.append(" AND FAD.user_id = duh.user_id");
		queryBuilder.append(" and path_length = 1");
		queryBuilder.append(" and duh.manager_id = ?");
		queryBuilder.append(" GROUP BY duh.USER_ID, FIRSTNAME,LASTNAME");
		queryBuilder.append(")");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());

		logger.debug("Query for getDirectReporteeAndUserCountsTotalRecords :\n" + queryBuilder.toString());

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	return DBUtils.getLongFromResultMapObject(resultMap.get("total_records"));
		}
		
		return 0L;
	}*/
	
	@Override
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsSuperUser(
			DrilldownRequestDTO requestDTO,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();

		String filterColumn = null;
		
		if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
				&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
			filterColumn = UserReportsColumnMap.getColumnValue(requestDTO.getFilteredColumn());
		
		if(StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName()))
			requestDTO.setGroupByColumnName("USER_ID, FIRSTNAME, LASTNAME, LOGIN_NAME");
		
		StringBuilder queryBuilder = new StringBuilder();
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			queryBuilder.append("SELECT * FROM (");
			queryBuilder.append("SELECT ROWNUM row_id, ");
			if(requestDTO.getGroupByColumnName().startsWith("USER_ID"))
				queryBuilder.append("USER_ID, FIRSTNAME, LASTNAME, LOGIN_NAME");
			else
				queryBuilder.append(requestDTO.getGroupByColumnName());
			queryBuilder.append(", NOT_STARTED_COUNT, IN_PROGRESS_COUNT, COMPLETE_COUNT FROM (");
		}
		
		queryBuilder.append("SELECT ").append(requestDTO.getGroupByColumnName());
		queryBuilder.append(", SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE) as NOT_STARTED_COUNT,");
		queryBuilder.append(" SUM(MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROGRESS_COUNT,");
		queryBuilder.append(" SUM(MOD_COMPLETE_ON_TIME + MOD_COMPLETE_PAST_DUE + MOD_COMPLETE_NO_DUE_DATE + MOD_COMPLETE_CREDITED_ON_TIME");
		queryBuilder.append(" + MOD_COMPLETE_CREDIT_PAST_DUE + MOD_COMP_CREDIT_NO_DUE_DATE) as COMPLETE_COUNT");
		queryBuilder.append(" FROM MV_FACT_MODULE_STATUS fad");
		queryBuilder.append(" WHERE fad.DASHBOARD_ID = ?");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory'");

		if(dataPrivacyColumnMap.size() > 0) {
			Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
			while(keySetIterator.hasNext()) {
				String columnName = keySetIterator.next();
				List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
				String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
				queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
			}
		}
		
		if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
			queryBuilder.append(" AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
			queryBuilder.append(" AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
		}
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn)) {
			queryBuilder.append(" AND");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}
		
		queryBuilder.append(" GROUP BY ").append(requestDTO.getGroupByColumnName());
		
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) 
			queryBuilder.append(")) WHERE row_id BETWEEN ? AND ?");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());

		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			Long rowsStart = requestDTO.getRowsStart();
			Long rowsEnd = rowsStart + requestDTO.getRowsSize();
			params.add(rowsStart + 1);
			params.add(rowsEnd);
		}
	
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getDirectReporteeAndUserCountsSuperUser :\n" + queryBuilder.toString());
		logger.debug("getDirectReporteeAndUserCountsSuperUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

		List<ReporteeAndUserCountsData> dtoList = new ArrayList<ReporteeAndUserCountsData> ();
		while(itr.hasNext()) {
			ReporteeAndUserCountsData responseDTO = new ReporteeAndUserCountsData();
	    	Map<String, Object> resultMap = itr.next();
	    	responseDTO.setUserId((String) resultMap.get("user_id"));
	    	responseDTO.setFirstName((String) resultMap.get("firstname"));
	    	responseDTO.setLastName((String) resultMap.get("lastname"));
	    	responseDTO.setLoginName((String) resultMap.get("login_name"));
	    	responseDTO.setGroupByColumnValue((String) resultMap.get(requestDTO.getGroupByColumnName()));
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setCompleteCount(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    	responseDTO.setInProgressCount(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROGRESS_COUNT")));
	    	responseDTO.setNotStartedCount(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_COUNT")));
	    	dtoList.add(responseDTO);
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");
		
		return dtoList;
	}
	
	@Override
	public List<ReporteeAndUserCountsData> getDirectReporteeAndUserCountsRegularUser(DrilldownRequestDTO requestDTO,
			String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {

		long startTime = System.currentTimeMillis();

		String filterColumn = null;
		
		if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
				&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
			filterColumn = UserReportsColumnMap.getColumnValue(requestDTO.getFilteredColumn());
		
		if(StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName()))
			requestDTO.setGroupByColumnName("fad.USER_ID, FIRSTNAME, LASTNAME, LOGIN_NAME");
		
		StringBuilder queryBuilder = new StringBuilder();
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			queryBuilder.append("SELECT * FROM (");
			queryBuilder.append("SELECT ROWNUM row_id, ");
			if(requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
				queryBuilder.append("USER_ID, FIRSTNAME, LASTNAME, LOGIN_NAME");
			else
				queryBuilder.append(requestDTO.getGroupByColumnName());
			queryBuilder.append(", NOT_STARTED_COUNT, IN_PROGRESS_COUNT, COMPLETE_COUNT FROM (");
		}
		
		queryBuilder.append("SELECT ").append(requestDTO.getGroupByColumnName());
		queryBuilder.append(", SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE) as NOT_STARTED_COUNT,");
		queryBuilder.append(" SUM(MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROGRESS_COUNT,");
		queryBuilder.append(" SUM(MOD_COMPLETE_ON_TIME + MOD_COMPLETE_PAST_DUE + MOD_COMPLETE_NO_DUE_DATE + MOD_COMPLETE_CREDITED_ON_TIME");
		queryBuilder.append(" + MOD_COMPLETE_CREDIT_PAST_DUE + MOD_COMP_CREDIT_NO_DUE_DATE) as COMPLETE_COUNT ");
		
		queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad WHERE");
		queryBuilder.append(" fad.DASHBOARD_ID = ? ");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory' ");
		
		queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
		queryBuilder.append("WHERE MANAGER_ID = ? ");
		if(requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
			queryBuilder.append(" and path_length = 1");
		queryBuilder.append("AND SITE_ID = ? ");
		queryBuilder.append("AND DASHBOARD_ID = ?)) ");
		if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null)
			&& !requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
			queryBuilder.append("or fad.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
		queryBuilder.append(") ");

		if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
			queryBuilder.append(" AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
			queryBuilder.append(" AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
		}
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn)) {
			queryBuilder.append(" AND");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}
		
		queryBuilder.append(" GROUP BY ").append(requestDTO.getGroupByColumnName());
		
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) 
			queryBuilder.append(")) WHERE row_id BETWEEN ? AND ?");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getDashboardId());	
		
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			Long rowsStart = requestDTO.getRowsStart();
			Long rowsEnd = rowsStart + requestDTO.getRowsSize();
			params.add(rowsStart + 1);
			params.add(rowsEnd);
		}
	
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getDirectReporteeAndUserCountsRegularUser :\n" + queryBuilder.toString());
		logger.debug("getDirectReporteeAndUserCountsRegularUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

		List<ReporteeAndUserCountsData> dtoList = new ArrayList<ReporteeAndUserCountsData> ();
		while(itr.hasNext()) {
			ReporteeAndUserCountsData responseDTO = new ReporteeAndUserCountsData();
	    	Map<String, Object> resultMap = itr.next();
	    	responseDTO.setUserId((String) resultMap.get("user_id"));
	    	responseDTO.setFirstName((String) resultMap.get("firstname"));
	    	responseDTO.setLastName((String) resultMap.get("lastname"));
	    	responseDTO.setLoginName((String) resultMap.get("login_name"));
	    	responseDTO.setGroupByColumnValue((String) resultMap.get(requestDTO.getGroupByColumnName()));
	    	responseDTO.setTotalReportees(DBUtils.getLongFromResultMapObject(resultMap.get("total_reportees")));
	    	responseDTO.setCompleteCount(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    	responseDTO.setInProgressCount(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROGRESS_COUNT")));
	    	responseDTO.setNotStartedCount(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_COUNT")));
	    	dtoList.add(responseDTO);
		}
		
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return dtoList;
	}

	@Override
	public Long getDirectReporteeAndUserReportTotalRecordsSuperUser(DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();

		//set the default group by column to duh.USER_ID if it is empty in the request
		if(StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName()))
			requestDTO.setGroupByColumnName("USER_ID");
		
		String filterColumn = null;
		String calculatedFilterColumn = null;
		
		if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
				&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) {
			filterColumn = UserReportsColumnMap.getColumnValue(requestDTO.getFilteredColumn());
			calculatedFilterColumn = UserReportsColumnMap.getCalculatedColumnValue(requestDTO.getFilteredColumn());
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT COUNT(*) as total_records FROM ");
		//add the groupBy column in the SELECT column list
		queryBuilder.append(" (SELECT DISTINCT ").append(requestDTO.getGroupByColumnName());
		
		//add the filterByColumn in the SELECT column list
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn))
			queryBuilder.append(", ").append(filterColumn);
		else if(!StringUtil.isDashboardGroupByColumnValueNull(calculatedFilterColumn))
				queryBuilder.append(", ").append(calculatedFilterColumn);
					
		queryBuilder.append(" FROM MV_FACT_MODULE_STATUS fad");
		queryBuilder.append(" WHERE fad.DASHBOARD_ID = ?");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory'");
		
		if(dataPrivacyColumnMap.size() > 0) {
			Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
			while(keySetIterator.hasNext()) {
				String columnName = keySetIterator.next();
				List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
				String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
				queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
			}
		}
		
		if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
			queryBuilder.append(" AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
			queryBuilder.append(" AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
		}
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn)) {
			queryBuilder.append(" AND");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}
		
		queryBuilder.append(" group by ");
		queryBuilder.append(requestDTO.getGroupByColumnName());
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn))
			queryBuilder.append(", ").append(requestDTO.getFilteredColumn());
		
		queryBuilder.append(" )");
		
		//do this only if filteredColumn != isNullValue
		if(!StringUtil.isDashboardGroupByColumnValueNull(calculatedFilterColumn)) {
			queryBuilder.append(" WHERE");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}

		ArrayList<Object> params = new ArrayList<Object> ();
		params.add(requestDTO.getDashboardId());	
		params.add(requestDTO.getSiteId());
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getDirectReporteeAndUserReportTotalRecordsSuperUser :\n" + queryBuilder.toString());
		logger.debug("getDirectReporteeAndUserReportTotalRecordsSuperUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		

	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	return DBUtils.getLongFromResultMapObject(resultMap.get("total_records"));
	    }
	    
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

	    return 0L;
	}
	
	@Override
	public Long getDirectReporteeAndUserReportTotalRecords(DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap,
			String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol) {

		long startTime = System.currentTimeMillis();

		//set the default group by column to fad.USER_ID if it is empty in the request
		if(StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName()))
				requestDTO.setGroupByColumnName("fad.USER_ID");
		
		String filterColumn = null;
		String calculatedFilterColumn = null;
		
		if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
				&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) {
			filterColumn = UserReportsColumnMap.getColumnValue(requestDTO.getFilteredColumn());
			calculatedFilterColumn = UserReportsColumnMap.getCalculatedColumnValue(requestDTO.getFilteredColumn());
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT COUNT(*) as total_records FROM ");
		//add the groupBy column in the SELECT column list
		queryBuilder.append(" (SELECT DISTINCT ").append(requestDTO.getGroupByColumnName());
		
		//add the filterByColumn in the SELECT column list
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn))
			queryBuilder.append(", ").append(filterColumn);
		else if(!StringUtil.isDashboardGroupByColumnValueNull(calculatedFilterColumn))
				queryBuilder.append(", ").append(calculatedFilterColumn);
					
		queryBuilder.append(" FROM MV_FACT_MODULE_STATUS fad WHERE ");
		queryBuilder.append("fad.DASHBOARD_ID = ? ");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory' ");
		
		queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
		queryBuilder.append("WHERE MANAGER_ID = ? ");
		if(requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
			queryBuilder.append(" and path_length = 1 ");
		queryBuilder.append("AND SITE_ID = ? ");
		queryBuilder.append("AND DASHBOARD_ID = ?)) ");
		if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null)
				&& !requestDTO.getGroupByColumnName().startsWith("fad.USER_ID"))
				queryBuilder.append("or fad.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
		queryBuilder.append(") ");

		
		if(dataPrivacyColumnMap.size() > 0) {
			Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
			while(keySetIterator.hasNext()) {
				String columnName = keySetIterator.next();
				List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
				String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
				queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
			}
		}
		
		if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
			queryBuilder.append(" AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
			queryBuilder.append(" AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
		}
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn)) {
			queryBuilder.append(" AND");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}
		
		queryBuilder.append(" group by ");
		queryBuilder.append(requestDTO.getGroupByColumnName());
		if(!StringUtil.isDashboardGroupByColumnValueNull(filterColumn))
			queryBuilder.append(", ").append(requestDTO.getFilteredColumn());
		
		queryBuilder.append(" )");
		
		//do this only if filteredColumn != isNullValue
		if(!StringUtil.isDashboardGroupByColumnValueNull(calculatedFilterColumn)) {
			queryBuilder.append(" WHERE");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}

		ArrayList<Object> params = new ArrayList<Object> ();
		params.add(requestDTO.getDashboardId());	
		params.add(requestDTO.getSiteId());
		params.add(requestDTO.getUserId());
		params.add(requestDTO.getSiteId());
		params.add(requestDTO.getDashboardId());	
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getDirectReporteeAndUserReportTotalRecords :\n" + queryBuilder.toString());
		logger.debug("getDirectReporteeAndUserReportTotalRecords Query Took :: " + (endTime-startTime)/1000 + " sec");

	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	return DBUtils.getLongFromResultMapObject(resultMap.get("total_records"));
	    }
	    
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

	    return 0L;
	}
	
	@Override
	public ReporteeAndUserReportResponseDTO getDirectReporteeAndUserReport(
			DrilldownRequestDTO requestDTO, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		long startTime = System.currentTimeMillis();

		//set the default group by column to duh.USER_ID if it is empty in the request
		if(StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getGroupByColumnName()))
			requestDTO.setGroupByColumnName("duh.USER_ID");
		
		String orderBy = requestDTO.getOrderByColumnName();

		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			//set the 1st column as default orderBy
			if(orderBy == null || (StringUtils.isEmpty(orderBy))) {
				Map<String, Long> colMap = columnNameANDColumnDisplayNameAndSequenceMap.get(col);
				for(String colCol : colMap.keySet()) {
					Long sequence = colMap.get(colCol);
					if(StringUtils.isEmpty(orderBy) && sequence == 1)
						orderBy = col;
				}
			}
			
			if(!(col.toUpperCase().equals("NOT_STARTED_PAST_DUE") || col.toUpperCase().equals("IN_PROCESS_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_COUNT") || col.toUpperCase().equals("IN_PROCESS_NOT_DUE_YET")
					|| col.toUpperCase().equals("NOT_STARTED_NO_DUE_DATE") || col.toUpperCase().equals("NOT_STARTED_NOT_DUE_YET")
					|| col.toUpperCase().equals("COMPLETE_NO_DUE_DATE") || col.toUpperCase().equals("COMPLETE_PAST_DUE")
					|| col.toUpperCase().equals("INCOMPLETE_COUNT") || col.toUpperCase().equals("COMPLETE_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_ON_TIME") || col.toUpperCase().equals("IN_PROCESS_NO_DUE_DATE")
					|| col.toUpperCase().equals("ISNULLVALUE")
					|| col.toUpperCase().equals("FIRSTNAME") || col.toUpperCase().equals("LASTNAME"))) //don't add NOT_STARTED_PAST_DUE - its calculated below...
				
				columnsFromColumnsMap.append(col).append(", ");
		}
				
		
		StringBuilder queryBuilder = new StringBuilder();
//		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			queryBuilder.append("SELECT * FROM (");
			queryBuilder.append("SELECT ROWNUM row_id, USER_ID, FIRSTNAME, LASTNAME, ");
			if(columnsFromColumnsMap.length() > 0) 
				queryBuilder.append(columnsFromColumnsMap);
			
			queryBuilder.append("NOT_STARTED_PAST_DUE, IN_PROCESS_PAST_DUE, COMPLETE_COUNT, NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("NOT_STARTED_NOT_DUE_YET, IN_PROCESS_NOT_DUE_YET, COMPLETE_NO_DUE_DATE, COMPLETE_PAST_DUE, ");
			queryBuilder.append("COMPLETE_ON_TIME, IN_PROCESS_NO_DUE_DATE, INCOMPLETE_COUNT ");
			queryBuilder.append(" FROM (");
//		}
		
		queryBuilder.append("SELECT DISTINCT duh.USER_ID, FIRSTNAME,LASTNAME, ");
		if(columnsFromColumnsMap.length() > 0)
			queryBuilder.append(columnsFromColumnsMap);

		queryBuilder.append("SUM(fad.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE ,SUM(fad.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
		queryBuilder.append("SUM(fad.mod_complete_on_time + fad.mod_complete_no_due_date ");
		queryBuilder.append("+ fad.mod_complete_past_due ");
		queryBuilder.append("+ fad.MOD_COMPLETE_CREDITED_ON_TIME ");
		queryBuilder.append("+ fad.MOD_COMPLETE_CREDIT_PAST_DUE ");
		queryBuilder.append("+ fad.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
		queryBuilder.append("SUM(fad.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
		queryBuilder.append("SUM(fad.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
		queryBuilder.append("SUM(fad.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
		queryBuilder.append("SUM(fad.MOD_COMPLETE_NO_DUE_DATE + fad.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
		queryBuilder.append("SUM(fad.MOD_COMPLETE_PAST_DUE + fad.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
		queryBuilder.append("SUM(fad.MOD_COMPLETE_ON_TIME + fad.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
		queryBuilder.append("SUM(fad.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
		queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");

		queryBuilder.append(" FROM MV_FACT_MODULE_STATUS fad, DIM_USER_HIERARCHY DUH");
		queryBuilder.append(" WHERE fad.DASHBOARD_ID = ?");
		queryBuilder.append(" AND FAD.SITE_ID = ? AND ASSIGNMENT_TYPE ='Mandatory'");
		queryBuilder.append(" AND FAD.user_id = duh.user_id");
		queryBuilder.append(" and path_length = 1");
		queryBuilder.append(" and duh.manager_id = ?");
		
		if(dataPrivacyColumnMap.size() > 0) {
			Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
			while(keySetIterator.hasNext()) {
				String columnName = keySetIterator.next();
				List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
				String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
				queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
			}
		}
		
		if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
			queryBuilder.append(" AND fad.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
			queryBuilder.append(" AND fad.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
		}
		
		queryBuilder.append(" group by ");
		queryBuilder.append(" duh.USER_ID, FIRSTNAME,LASTNAME,");
		columnsFromColumnsMap = new StringBuffer(columnsFromColumnsMap.substring(0, columnsFromColumnsMap.length() - 2));
		if(columnsFromColumnsMap.length() > 0)
			queryBuilder.append(columnsFromColumnsMap);

		queryBuilder.append(" ORDER BY ").append(orderBy).append(" ").append(requestDTO.getOrderByDirection());
		queryBuilder.append(")");
		
		//do this only if filteredColumn != isNullValue
		if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
				&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) {
			queryBuilder.append(" WHERE");
			addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		}
		queryBuilder.append(")");
		
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) 
			queryBuilder.append(" WHERE row_id BETWEEN ? AND ?");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());
			
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) {
			Long rowsStart = requestDTO.getRowsStart();
			Long rowsEnd = rowsStart + requestDTO.getRowsSize();
			params.add(rowsStart + 1);
			params.add(rowsEnd);
		}
		
//		logger.debug("Query for getDirectReporteeAndUserReport :\n" + queryBuilder.toString());
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
		long endTime = System.currentTimeMillis();
		logger.debug("getDirectReporteeAndUserReport Query Took :: " + (endTime-startTime)/1000 + " sec");

		ReporteeAndUserReportResponseDTO dto = new ReporteeAndUserReportResponseDTO();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");

		return dto;
	}
	
	private void addFilterColumn(String filteredColumn,	String filteredColumnValue, StringBuilder queryBuilder) {
		
		if(StringUtil.isDashboardGroupByColumnValueNull(filteredColumnValue))
			queryBuilder.append(filteredColumn).append(" IS NULL ");
		else {
			queryBuilder.append(" upper(").append(filteredColumn).append(") like '%").append(filteredColumnValue.toUpperCase()).append("%' ");
		}
	}
}
