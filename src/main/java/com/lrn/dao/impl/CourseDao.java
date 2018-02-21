package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.constants.ModuleCourseCompletionStatusDetailMap;
import com.lrn.constants.ModuleCourseCompletionStatusMap;
import com.lrn.constants.ModuleCourseStatusDetailMap;
import com.lrn.constants.ModuleCourseStatusMap;
import com.lrn.dao.ICourseDao;
import com.lrn.dao.IDashboardSelectionCriteriaDao;
import com.lrn.dto.CourseAssignmentDetailsForPastDueDTO;
import com.lrn.dto.GetCourseAssignmentDetailsForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusGroupByForManagerDTO;
import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;
import com.lrn.model.FactModuleStatus;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

public class CourseDao extends
		GenericDao<FactModuleStatus, Long> implements ICourseDao {
	
	IDashboardSelectionCriteriaDao dashboardSelectionCriteriaDao;

	public void setDashboardSelectionCriteriaDao(
			IDashboardSelectionCriteriaDao dashboardSelectionCriteriaDao) {
		this.dashboardSelectionCriteriaDao = dashboardSelectionCriteriaDao;
	}

	public CourseDao(Class<FactModuleStatus> persistentClass) {
		super(persistentClass);

	}

	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusSuperUser(
			Long dashboardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		List<GetCourseAssignmentStatusGroupByForManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusGroupByForManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * ");
			queryBuilder.append("FROM ");
			queryBuilder.append("(SELECT DISTINCT ").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME+MOD_COMPLETE_NO_DUE_DATE+MOD_COMPLETE_CREDITED_ON_TIME+MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_PAST_DUE+MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE+MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE+MOD_IN_PROCESS_NO_DUE_DATE+MOD_NOT_STARTED_NOT_DUE_YET+MOD_IN_PROCESS_NOT_DUE_YET) AS INCOMPLETE_NOT_DUE_YET ");
			queryBuilder.append("FROM MV_fact_module_status ");
			queryBuilder.append("WHERE  SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ? ");
			queryBuilder.append("AND ASSIGNMENT_TYPE = 'Mandatory' ");
			
			if(dataPrivacyColumnMap.size() > 0) {
				Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
				while(keySetIterator.hasNext()) {
					String columnName = keySetIterator.next();
					List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
					String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
					queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
				}
			}

			queryBuilder.append("GROUP BY ").append(groupByColumnName).append(" ");
			queryBuilder.append(") tmp WHERE COMPLETE_ON_TIME IS NOT NULL ");
			queryBuilder.append("OR COMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR INCOMPLETE_NOT_DUE_YET IS NOT NULL");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			params.add(dashboardId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
        while(itr.hasNext()) {
            Map<String, Object> resultMap = itr.next();
            GetCourseAssignmentStatusGroupByForManagerDTO dto = new GetCourseAssignmentStatusGroupByForManagerDTO();
            	dto.setComplete_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
            	dto.setComplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
            	dto.setIncomplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
            	dto.setIncomplete_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_NOT_DUE_YET")));
            	dto.setDivision((String) resultMap.get(groupByColumnName));
            dtoList.add(dto);
        }
        
		return dtoList;
	}

	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUser(
			Long dashboardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId) {
		
		List<GetCourseAssignmentStatusGroupByForManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusGroupByForManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT DISTINCT fad.").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME)+SUM(MOD_COMPLETE_CREDITED_ON_TIME)+SUM(MOD_COMPLETE_NO_DUE_DATE)+SUM(MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_ON_TIME , ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDIT_PAST_DUE) +SUM(MOD_COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE ,SUM(MOD_NOT_STARTED_PAST_DUE)+");
			queryBuilder.append("SUM(MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE)+SUM(MOD_IN_PROCESS_NO_DUE_DATE)+SUM(MOD_NOT_STARTED_NOT_DUE_YET)+");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NOT_DUE_YET) AS INCOMPLETE_NOT_DUE_YET ");
			queryBuilder.append("FROM MV_fact_module_status fad ");
			queryBuilder.append("WHERE fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("AND fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE = 'Mandatory' ");
			queryBuilder.append("GROUP BY fad.").append(groupByColumnName).append(" ORDER BY 1");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(dashboardId);
			params.add(siteId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
        while(itr.hasNext()) {
            Map<String, Object> resultMap = itr.next();
            GetCourseAssignmentStatusGroupByForManagerDTO dto = new GetCourseAssignmentStatusGroupByForManagerDTO();
            	dto.setComplete_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
            	dto.setComplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
            	dto.setIncomplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
            	dto.setIncomplete_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_NOT_DUE_YET")));
            	dto.setDivision((String) resultMap.get(groupByColumnName));
            dtoList.add(dto);
        }
        
		return dtoList;
	}
	

	@Override
	public List<GetCourseAssignmentStatusGroupByForManagerDTO> get1stChartCourseCompletionStatusRegularUserSameHierarchy(
			Long dashboardId, String managerId, String groupByColumnName, Long hasDashBoardConfig, Long siteId,
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		List<GetCourseAssignmentStatusGroupByForManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusGroupByForManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT DISTINCT fad.").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME)+SUM(MOD_COMPLETE_CREDITED_ON_TIME)+SUM(MOD_COMPLETE_NO_DUE_DATE)+SUM(MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDIT_PAST_DUE) +SUM(MOD_COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE)+SUM(MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE)+SUM(MOD_IN_PROCESS_NO_DUE_DATE)+SUM(MOD_NOT_STARTED_NOT_DUE_YET)+");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NOT_DUE_YET) AS INCOMPLETE_NOT_DUE_YET ");
			queryBuilder.append("FROM MV_fact_module_status fad ");
			queryBuilder.append("WHERE fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("OR fad.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("AND fad.SITE_ID = ? ");
			queryBuilder.append("AND fad.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("GROUP BY fad.").append(groupByColumnName).append(" ORDER BY 1 ");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(siteId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
	
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			GetCourseAssignmentStatusGroupByForManagerDTO dto = new GetCourseAssignmentStatusGroupByForManagerDTO();
	        	dto.setComplete_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
	        	dto.setComplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
	        	dto.setIncomplete_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	        	dto.setIncomplete_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_NOT_DUE_YET")));
	        	dto.setDivision((String) resultMap.get(groupByColumnName));
	        dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS days_past_due ");
			queryBuilder.append("FROM MV_fact_module_status fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseCompletionStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);	
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		List<String> columnNames = new ArrayList<String> (); 
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) { //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
				columnNames.add(col); //add all the columns to this list
			}
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS days_past_due ");
			queryBuilder.append("FROM MV_fact_module_status fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);	
			params.add(siteId);
		
//		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params, 1000); - this takes a long time eg. 32 secs for 8000 records
		//add the additional columns DUE_DATE & days_past_due
//		columnNames.add("DUE_DATE");
//		columnNames.add("days_past_due");
//		List<Map<String, Object>> resultSetMapList = queryForLargeList(queryBuilder.toString(), params, columnNames); --  this takes a long time as well eg. 32 secs for 8000 records
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
		return dto;
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();

		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("fs.MODULE_LANGUAGE, fs.MODULE_TITLE, DECODE(fs.MODULE_STATUS, 'In Progress', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0), 0 ) AS days_past_due ");
			queryBuilder.append("FROM MV_fact_module_status fs ");
			queryBuilder.append("WHERE fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(dashboardId);	
			params.add(siteId);
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get4thChartCourseStatusDetailsDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("fs.MODULE_LANGUAGE, fs.MODULE_TITLE, DECODE(fs.MODULE_STATUS, 'In Progress', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0), 0 ) AS days_past_due ");
			queryBuilder.append("FROM MV_fact_module_status fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND ASSIGNMENT_TYPE = 'Mandatory' ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("OR fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);	
			params.add(siteId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);	
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) || StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue)) )
				queryBuilder.append("fs.").append(groupByColumnName).append(", ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress',DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS DAYS_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			
			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" IS NULL ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseCompletionStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE = 'Mandatory' ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(dashboardId);
			params.add(siteId);
		if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
			params.add(groupByColumnValue);
			
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}

	private void addFilterColumn(String filteredColumn,	String filteredColumnValue, StringBuilder queryBuilder) {
		
		if(StringUtil.isDashboardGroupByColumnValueNull(filteredColumnValue))
			queryBuilder.append("AND fs.").append(filteredColumn).append(" IS NULL ");
		else {
			if(filteredColumn.contains("DUE_DATE")) //due_date - 1
				queryBuilder.append("AND to_char(fs.").append(filteredColumn).append("-1,'mm-dd-yyyy') = '").append(filteredColumnValue).append("' ");
			else if(filteredColumn.contains("DATE")) //date as is
				queryBuilder.append("AND to_char(fs.").append(filteredColumn).append(",'mm-dd-yyyy') = '").append(filteredColumnValue).append("' ");
			else if(filteredColumn.contains("TIME_SPENT") || filteredColumn.contains("SCORE")) //NUMBER columns
				queryBuilder.append("AND fs.").append(filteredColumn).append(" = ").append(filteredColumnValue).append(" ");
			else if(!filteredColumn.contains("DAYS_PAST_DUE")) {
				if(filteredColumnValue.contains("'"))
					filteredColumnValue = filteredColumnValue.replaceAll("'", "''");
				queryBuilder.append("AND upper(fs.").append(filteredColumn).append(") like '%").append(filteredColumnValue).append("%' ");
			}
		}
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashBoardConfig, String filteredColumn, String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		GetCourseAssignmentDetailsForManagerDTO dto = new GetCourseAssignmentDetailsForManagerDTO();
	    
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress',DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS days_past_due ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");

			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" IS NULL ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			
			queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseCompletionStatusDetailMap.getValue(completionStatus));
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("OR fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
		return dto;
	}

	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesSuperUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		List<CourseAssignmentDetailsForPastDueDTO> dtoList = new ArrayList<CourseAssignmentDetailsForPastDueDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" as ").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE+MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			
			if(dataPrivacyColumnMap.size() > 0) {
				Iterator<String> keySetIterator = dataPrivacyColumnMap.keySet().iterator();
				while(keySetIterator.hasNext()) {
					String columnName = keySetIterator.next();
					List<String> dataPrivacyColumnList = dataPrivacyColumnMap.get(columnName);
					String dataPrivacyColumnListString = StringUtil.getStringListAsAsString(dataPrivacyColumnList);
					queryBuilder.append(" AND ").append(columnName).append(" in (").append(dataPrivacyColumnListString).append(") ");
				}
			}
			
			queryBuilder.append("GROUP BY fs.").append(groupByColumnName).append(") ");
			queryBuilder.append("where coalesce(INCOMPLETE_PAST_DUE,0) > 0 ");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);	
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	CourseAssignmentDetailsForPastDueDTO dto = new CourseAssignmentDetailsForPastDueDTO();
	    	dto.setName((String) resultMap.get(groupByColumnName));
	    	dto.setIncompletePastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	    	dtoList.add(dto);
	    }
	    
		return dtoList;
	}

	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUser(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig) {

		List<CourseAssignmentDetailsForPastDueDTO> dtoList = new ArrayList<CourseAssignmentDetailsForPastDueDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" as ").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE+MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("GROUP BY fs.").append(groupByColumnName).append(") ");
			queryBuilder.append("where coalesce(INCOMPLETE_PAST_DUE,0) > 0");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);
			params.add(userId);	
			params.add(siteId);
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	CourseAssignmentDetailsForPastDueDTO dto = new CourseAssignmentDetailsForPastDueDTO();
	    	dto.setName((String) resultMap.get(groupByColumnName));
	    	dto.setIncompletePastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	    	dtoList.add(dto);
	    }
	    
		return dtoList;
	}

	@Override
	public List<CourseAssignmentDetailsForPastDueDTO> get2ndChartIncompleteAndPastDueCoursesRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig, 
			String dashboardConfig, String userColumnForDashboardConfig) {

		List<CourseAssignmentDetailsForPastDueDTO> dtoList = new ArrayList<CourseAssignmentDetailsForPastDueDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" as ").append(groupByColumnName).append(", ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE)+SUM(MOD_IN_PROCESS_PAST_DUE) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("OR fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("GROUP BY fs.").append(groupByColumnName).append(") ");
			queryBuilder.append("where coalesce(INCOMPLETE_PAST_DUE,0) > 0 ");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);
			params.add(userId);	
			params.add(siteId);
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	CourseAssignmentDetailsForPastDueDTO dto = new CourseAssignmentDetailsForPastDueDTO();
	    	dto.setName((String) resultMap.get(groupByColumnName));
	    	dto.setIncompletePastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	    	dtoList.add(dto);
	    }
	    
		return dtoList;
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownSuperUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		return get1stChartCourseCompletionStatusDrilldownSuperUser(dashboardId, siteId, managerId, groupByColumnName, 
				groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUser(
			Long dashboardId, Long siteId, String managerId,
			String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig,
			String filteredColumn, String filteredColumnValue, Long isExport,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return get1stChartCourseCompletionStatusDrilldownRegularUser(dashboardId, siteId, managerId, 
				groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, 
				filteredColumn, filteredColumnValue, isExport, columnNameANDColumnDisplayNameAndSequenceMap);
	}
	
	@Override
	public GetCourseAssignmentDetailsForManagerDTO get2ndChartIncompleteAndPastDueCoursesDrilldownRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String managerId, String groupByColumnName, String groupByColumnValue,
			String completionStatus, Long hasDashboardConfig, String filteredColumn, String filteredColumnValue, 
			Long isExport, String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		return get1stChartCourseCompletionStatusDrilldownRegularUserSameHierarchy(dashboardId, siteId, managerId, 
				groupByColumnName, groupByColumnValue, completionStatus, hasDashboardConfig, filteredColumn, 
				filteredColumnValue, isExport, dashboardConfig, userColumnForDashboardConfig, columnNameANDColumnDisplayNameAndSequenceMap);
	}

	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsSuperUser(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		List<GetCourseAssignmentStatusManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT DISTINCT SUM(MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_CREDITED_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_CREDITED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMP_CREDIT_NO_DUE_DATE)  AS COMPLETE_CREDITED_NO_DUE_DATE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad ");
			queryBuilder.append("WHERE fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND FAD.SITE_ID = ? ");
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory'");
			
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
			params.add(dashboardId);
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	GetCourseAssignmentStatusManagerDTO dto = new GetCourseAssignmentStatusManagerDTO();
	    	dto.setCOMPLETE_CREDITED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_NO_DUE_DATE")));
	    	dto.setCOMPLETE_CREDITED_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_ON_TIME")));
	    	dto.setCOMPLETE_CREDITED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_PAST_DUE")));
	    	dto.setCOMPLETE_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_NO_DUE_DATE")));
	    	dto.setCOMPLETE_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
	    	dto.setCOMPLETE_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
	    	dto.setIN_PROCESS_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NO_DUE_DATE")));
	    	dto.setIN_PROCESS_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NOT_DUE_YET")));
	    	dto.setIN_PROCESS_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_PAST_DUE")));
	    	dto.setNOT_STARTED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NO_DUE_DATE")));
	    	dto.setNOT_STARTED_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NOT_DUE_YET")));
	    	dto.setNOT_STARTED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_PAST_DUE")));
	    	
	    	dtoList.add(dto);
	    }
		return dtoList;
	}
	
	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUser(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId) {
		
		List<GetCourseAssignmentStatusManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET) NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE) NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE) NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NOT_DUE_YET) IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_PAST_DUE) IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NO_DUE_DATE) IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME) COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_PAST_DUE) COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_NO_DUE_DATE) COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDITED_ON_TIME) COMPLETE_CREDITED_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDIT_PAST_DUE) COMPLETE_CREDITED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMP_CREDIT_NO_DUE_DATE) COMPLETE_CREDITED_NO_DUE_DATE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad ");
			queryBuilder.append("WHERE FAD.SITE_ID = ? ");
			queryBuilder.append("AND fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND ASSIGNMENT_TYPE ='Mandatory'");
			queryBuilder.append("AND fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE MANAGER_ID = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			params.add(dashboardId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	GetCourseAssignmentStatusManagerDTO dto = new GetCourseAssignmentStatusManagerDTO();
	    	dto.setCOMPLETE_CREDITED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_NO_DUE_DATE")));
	    	dto.setCOMPLETE_CREDITED_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_ON_TIME")));
	    	dto.setCOMPLETE_CREDITED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_PAST_DUE")));
	    	dto.setCOMPLETE_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_NO_DUE_DATE")));
	    	dto.setCOMPLETE_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
	    	dto.setCOMPLETE_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
	    	dto.setIN_PROCESS_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NO_DUE_DATE")));
	    	dto.setIN_PROCESS_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NOT_DUE_YET")));
	    	dto.setIN_PROCESS_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_PAST_DUE")));
	    	dto.setNOT_STARTED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NO_DUE_DATE")));
	    	dto.setNOT_STARTED_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NOT_DUE_YET")));
	    	dto.setNOT_STARTED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_PAST_DUE")));
	    	
	    	dtoList.add(dto);
	    }
		return dtoList;
	}
	
	@Override
	public List<GetCourseAssignmentStatusManagerDTO> get4thChartCourseStatusDetailsRegularUserSameHierarchy(
			Long dashboardId, String managerId, Long hasDashboardConfig, Long siteId,
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		List<GetCourseAssignmentStatusManagerDTO> dtoList = new ArrayList<GetCourseAssignmentStatusManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT SUM(MOD_NOT_STARTED_NOT_DUE_YET) NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_PAST_DUE) NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NO_DUE_DATE) NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NOT_DUE_YET) IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_PAST_DUE) IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_IN_PROCESS_NO_DUE_DATE) IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_ON_TIME) COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_PAST_DUE) COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_NO_DUE_DATE) COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDITED_ON_TIME) COMPLETE_CREDITED_ON_TIME, ");
			queryBuilder.append("SUM(MOD_COMPLETE_CREDIT_PAST_DUE) COMPLETE_CREDITED_PAST_DUE, ");
			queryBuilder.append("SUM(MOD_COMP_CREDIT_NO_DUE_DATE) COMPLETE_CREDITED_NO_DUE_DATE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fad ");
			queryBuilder.append("WHERE fad.DASHBOARD_ID = ? ");
			queryBuilder.append("AND FAD.SITE_ID = ? ");
			queryBuilder.append("AND ((fad.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("OR fad.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append(" AND ASSIGNMENT_TYPE ='Mandatory'");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	GetCourseAssignmentStatusManagerDTO dto = new GetCourseAssignmentStatusManagerDTO();
	    	dto.setCOMPLETE_CREDITED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_NO_DUE_DATE")));
	    	dto.setCOMPLETE_CREDITED_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_ON_TIME")));
	    	dto.setCOMPLETE_CREDITED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_CREDITED_PAST_DUE")));
	    	dto.setCOMPLETE_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_NO_DUE_DATE")));
	    	dto.setCOMPLETE_ON_TIME(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_ON_TIME")));
	    	dto.setCOMPLETE_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_PAST_DUE")));
	    	dto.setIN_PROCESS_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NO_DUE_DATE")));
	    	dto.setIN_PROCESS_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_NOT_DUE_YET")));
	    	dto.setIN_PROCESS_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("IN_PROCESS_PAST_DUE")));
	    	dto.setNOT_STARTED_NO_DUE_DATE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NO_DUE_DATE")));
	    	dto.setNOT_STARTED_NOT_DUE_YET(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_NOT_DUE_YET")));
	    	dto.setNOT_STARTED_PAST_DUE(DBUtils.getLongFromResultMapObject(resultMap.get("NOT_STARTED_PAST_DUE")));
	    	
	    	dtoList.add(dto);
	    }
		return dtoList;
	}
	
	private boolean checkDaysPastDueFilter(String filteredColumn,
			String filteredColumnValue, List<Map<String, Object>> dataMap,
			Map<String, Object> resultMap) {
		
		if("DAYS_PAST_DUE".equals(filteredColumn) && !StringUtil.isDashboardGroupByColumnValueNull(filteredColumnValue)
				&& resultMap.get("DAYS_PAST_DUE") != null && (((BigDecimal) resultMap.get("DAYS_PAST_DUE")).longValue() == Long.parseLong(filteredColumnValue))) 
			return true;
		else if(!"DAYS_PAST_DUE".equals(filteredColumn)) //if the filterColumn is NOT days_past_due, then just add the entire resultMap
			return true;
		
		return false;
	}
}
