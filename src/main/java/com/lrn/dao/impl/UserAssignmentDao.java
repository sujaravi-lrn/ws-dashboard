package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.constants.UserCompletionStatusMap;
import com.lrn.dao.IUserAssignmentDao;
import com.lrn.dto.AssignmentStatusForManagerDTO;
import com.lrn.dto.GetUserAssignmentDetailsForManagerDTO;
import com.lrn.model.FactModuleStatus;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class UserAssignmentDao extends
	GenericDao<FactModuleStatus, Long> implements IUserAssignmentDao {

	public UserAssignmentDao(Class<FactModuleStatus> persistentClass) {
		super(persistentClass);
	}

	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusSuperUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig, Map<String, ArrayList<String>> dataPrivacyColumnMap) {

		List<AssignmentStatusForManagerDTO> dtoList = new ArrayList<AssignmentStatusForManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {
			
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1,user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1,user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then user_id end) AS INCOMPLETE_PAST_DUE ");
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
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");
			
			params.add(dashboardId);
			params.add(siteId);
		
		} else {

			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=0 AND MAX_MODULE_COMPLETE=1) THEN FS.user_id END) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=1) THEN FS.user_id END) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then FS.user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs, ");
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) MAX_MODULE_COMPLETE, ");
			queryBuilder.append(" max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE ");
			queryBuilder.append(" from MV_FACT_MODULE_STATUS fs1 ");
			queryBuilder.append("WHERE fs1.DASHBOARD_ID = ? AND fs1.site_id = ? AND ");
			queryBuilder.append("fs1.ASSIGNMENT_TYPE ='Mandatory'  group by user_id) TMP ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? AND fs.site_id = ? AND ");
			queryBuilder.append("fs.ASSIGNMENT_TYPE ='Mandatory' AND TMP.USER_ID=FS.USER_ID ");
			
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
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");
			
			params.add(dashboardId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(siteId);
		}
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
        while(itr.hasNext()) {
            Map<String, Object> resultMap = itr.next();
            AssignmentStatusForManagerDTO dto = new AssignmentStatusForManagerDTO();
            	dto.setCompleted(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
            	dto.setIncomplete(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT")));
            	dto.setPastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
            	dto.setName((String) resultMap.get(groupByColumnName));
            dtoList.add(dto);
        }
		return dtoList;
	}
	
	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownSuperUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		GetUserAssignmentDetailsForManagerDTO dto = new GetUserAssignmentDetailsForManagerDTO();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("NOT_STARTED_PAST_DUE") || col.toUpperCase().equals("IN_PROCESS_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_COUNT") || col.toUpperCase().equals("IN_PROCESS_NOT_DUE_YET")
					|| col.toUpperCase().equals("NOT_STARTED_NO_DUE_DATE") || col.toUpperCase().equals("NOT_STARTED_NOT_DUE_YET")
					|| col.toUpperCase().equals("COMPLETE_NO_DUE_DATE") || col.toUpperCase().equals("COMPLETE_PAST_DUE")
					|| col.toUpperCase().equals("INCOMPLETE_COUNT") || col.toUpperCase().equals("COMPLETE_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_ON_TIME") || col.toUpperCase().equals("IN_PROCESS_NO_DUE_DATE")
					|| col.toUpperCase().equals("ISNULLVALUE"))) //don't add NOT_STARTED_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {

			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE ,SUM(fs.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs.mod_complete_on_time + fs.mod_complete_no_due_date ");
			queryBuilder.append("+ fs.mod_complete_past_due ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_NO_DUE_DATE + fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_PAST_DUE + fs.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_ON_TIME + fs.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
				
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");

			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(dashboardId);	
						
		} else {
			
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			
			queryBuilder.append("max(tmp.NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("max(tmp.IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_COUNT) AS COMPLETE_COUNT, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.COMPLETE_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("max(tmp.INCOMPLETE_COUNT) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs, ");
			
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) MAX_MODULE_COMPLETE, ");
			queryBuilder.append("max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.mod_complete_on_time + fs1.mod_complete_no_due_date ");
			queryBuilder.append("+ fs1.mod_complete_past_due ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_NO_DUE_DATE + fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_PAST_DUE + fs1.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_ON_TIME + fs1.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
					
			//queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs1 ");
			queryBuilder.append("WHERE fs1.DASHBOARD_ID = ? AND ");
			queryBuilder.append("fs1.site_id = ? AND ");
			queryBuilder.append("fs1.ASSIGNMENT_TYPE ='Mandatory' group by user_id) TMP ");
			queryBuilder.append("WHERE fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
			queryBuilder.append("AND TMP.USER_ID=FS.USER_ID ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("AND TMP.MAX_MODULE_INCOMPLETE=decode(MODULE_STATUS,'Not Started',1,'In Progress',1,0) ");
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");
			
			params.add(dashboardId);
			params.add(siteId);
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(dashboardId);	
		
		}
		
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

	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUser(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport, Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		GetUserAssignmentDetailsForManagerDTO dto = new GetUserAssignmentDetailsForManagerDTO();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("NOT_STARTED_PAST_DUE") || col.toUpperCase().equals("IN_PROCESS_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_COUNT") || col.toUpperCase().equals("IN_PROCESS_NOT_DUE_YET")
					|| col.toUpperCase().equals("NOT_STARTED_NO_DUE_DATE") || col.toUpperCase().equals("NOT_STARTED_NOT_DUE_YET")
					|| col.toUpperCase().equals("COMPLETE_NO_DUE_DATE") || col.toUpperCase().equals("COMPLETE_PAST_DUE")
					|| col.toUpperCase().equals("INCOMPLETE_COUNT") || col.toUpperCase().equals("COMPLETE_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_ON_TIME") || col.toUpperCase().equals("IN_PROCESS_NO_DUE_DATE")
					|| col.toUpperCase().equals("ISNULLVALUE"))) //don't add NOT_STARTED_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {

			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs.mod_complete_on_time + fs.mod_complete_no_due_date ");
			queryBuilder.append("+ fs.mod_complete_past_due ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_NO_DUE_DATE + fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_PAST_DUE + fs.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_ON_TIME + fs.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("AND fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
		
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");
			
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(dashboardId);
			
		} else {
			
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);

			queryBuilder.append("max(tmp.NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("max(tmp.IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_COUNT) AS COMPLETE_COUNT, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.COMPLETE_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("max(tmp.INCOMPLETE_COUNT) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs, ");
			
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) MAX_MODULE_COMPLETE, ");
			queryBuilder.append("max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.mod_complete_on_time + fs1.mod_complete_no_due_date ");
			queryBuilder.append("+ fs1.mod_complete_past_due ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_NO_DUE_DATE + fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_PAST_DUE + fs1.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_ON_TIME + fs1.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");

			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs1 ");
			queryBuilder.append("WHERE fs1.DASHBOARD_ID = ? AND ");
			queryBuilder.append("fs1.site_id = ? AND ");
			queryBuilder.append("fs1.ASSIGNMENT_TYPE ='Mandatory' group by user_id) TMP ");
			queryBuilder.append("WHERE fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("AND fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
		
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append(" AND TMP.USER_ID=FS.USER_ID ");
			queryBuilder.append("AND TMP.MAX_MODULE_INCOMPLETE=decode(MODULE_STATUS,'Not Started',1,'In Progress',1,0) ");
	
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");
			
			params.add(dashboardId);
			params.add(siteId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(dashboardId);
		}
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
			
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}
	
	@Override
	public GetUserAssignmentDetailsForManagerDTO get3rdChartUserCompletionStatusDrilldownRegularUserSameHierarchy(
			Long dashboardId, String managerId, String groupByColumnName,
			String groupByColumnValue, String completionStatus,
			Long hasDashboardConfig, Long siteId, String filteredColumn,
			String filteredColumnValue, Long isExport,
			String dashboardConfig, String userColumnForDashboardConfig,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		
		GetUserAssignmentDetailsForManagerDTO dto = new GetUserAssignmentDetailsForManagerDTO();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			if(!(col.toUpperCase().equals("NOT_STARTED_PAST_DUE") || col.toUpperCase().equals("IN_PROCESS_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_COUNT") || col.toUpperCase().equals("IN_PROCESS_NOT_DUE_YET")
					|| col.toUpperCase().equals("NOT_STARTED_NO_DUE_DATE") || col.toUpperCase().equals("NOT_STARTED_NOT_DUE_YET")
					|| col.toUpperCase().equals("COMPLETE_NO_DUE_DATE") || col.toUpperCase().equals("COMPLETE_PAST_DUE")
					|| col.toUpperCase().equals("INCOMPLETE_COUNT") || col.toUpperCase().equals("COMPLETE_PAST_DUE") 
					|| col.toUpperCase().equals("COMPLETE_ON_TIME") || col.toUpperCase().equals("IN_PROCESS_NO_DUE_DATE")
					|| col.toUpperCase().equals("ISNULLVALUE"))) //don't add NOT_STARTED_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {
			
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs.mod_complete_on_time + fs.mod_complete_no_due_date ");
			queryBuilder.append("+ fs.mod_complete_past_due ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_NO_DUE_DATE + fs.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_PAST_DUE + fs.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs.MOD_COMPLETE_ON_TIME + fs.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs ");
			queryBuilder.append("WHERE fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			
			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
			
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("or fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");
		
			params.add(siteId);
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
			params.add(dashboardId);	
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);	

		} else {
			
			queryBuilder.append("SELECT ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);

			queryBuilder.append("max(tmp.NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("max(tmp.IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_COUNT) AS COMPLETE_COUNT, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("max(tmp.COMPLETE_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("max(tmp.COMPLETE_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("max(tmp.COMPLETE_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("max(tmp.IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			queryBuilder.append("DECODE(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS) AS Completion_status, ");
			queryBuilder.append("max(tmp.INCOMPLETE_COUNT) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs, ");
	
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) MAX_MODULE_COMPLETE, ");
			queryBuilder.append("max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.mod_complete_on_time + fs1.mod_complete_no_due_date ");
			queryBuilder.append("+ fs1.mod_complete_past_due ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDITED_ON_TIME ");
			queryBuilder.append("+ fs1.MOD_COMPLETE_CREDIT_PAST_DUE ");
			queryBuilder.append("+ fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_NO_DUE_DATE + fs1.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_PAST_DUE + fs1.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE, ");
			queryBuilder.append("SUM(fs1.MOD_COMPLETE_ON_TIME + fs1.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME, ");
			queryBuilder.append("SUM(fs1.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE, ");
			
			queryBuilder.append("SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs1 ");
			queryBuilder.append("WHERE fs1.DASHBOARD_ID = ? AND ");
			queryBuilder.append("fs1.site_id = ? AND ");
			queryBuilder.append("fs1.ASSIGNMENT_TYPE ='Mandatory' group by user_id) TMP ");
			queryBuilder.append("WHERE ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("or fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
	
			//do this only if groupByColumnName != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName))) {
				if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" is null ");
				else
					queryBuilder.append("AND fs.").append(groupByColumnName).append(" = ? ");
			}
			//do this only if filteredColumn != isNullValue
			if(!(StringUtil.isDashboardGroupByColumnValueNull(filteredColumn))) 
				addFilterColumn(filteredColumn, filteredColumnValue, queryBuilder);
		
			queryBuilder.append("and fs.MODULE_STATUS ").append(UserCompletionStatusMap.getValue(completionStatus));
			queryBuilder.append(" AND TMP.USER_ID=FS.USER_ID ");
			queryBuilder.append("AND TMP.MAX_MODULE_INCOMPLETE=decode(MODULE_STATUS,'Not Started',1,'In Progress',1,0) ");
			queryBuilder.append("group by ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("decode(MODULE_STATUS,'Not Started','Incomplete','In Progress','Incomplete', 'Complete (credited)','Complete',MODULE_STATUS)");

			params.add(dashboardId);
			params.add(siteId);
			params.add(managerId);
			params.add(siteId);
			params.add(dashboardId);	
			params.add(dashboardId);	
			if(!StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName) && !StringUtil.isDashboardGroupByColumnValueNull(groupByColumnValue))
				params.add(groupByColumnValue);
		}
		
		List<Map<String, Object>> resultSetMapList = new ArrayList<Map<String, Object>> ();
		if(isExport == 1L)
			resultSetMapList = queryForListByFetchSize(queryBuilder.toString(), params, 1000);
		else
			resultSetMapList = queryForListByMaxRow(queryBuilder.toString(), params, 1000);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
	    while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	if(checkDaysPastDueFilter(filteredColumn, filteredColumnValue,	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
		return dto;
	}
	
	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUser(
			Long dashboardId, Long siteId, String userId,
			String groupByColumnName, Long hasDashboardConfig) {
		
		List<AssignmentStatusForManagerDTO> dtoList = new ArrayList<AssignmentStatusForManagerDTO> ();
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {
			
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1,user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1,user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS FS ");
			queryBuilder.append("WHERE FS.dashboard_id = ? ");
			queryBuilder.append("AND FS.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND FS.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("GROUP BY FS.").append(groupByColumnName).append(") ");
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");
	
			params.add(dashboardId);	
			params.add(siteId);
			params.add(userId);
			params.add(siteId);
			params.add(dashboardId);
			
		} else {
			
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=0 AND MAX_MODULE_COMPLETE=1) THEN FS.user_id END) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=1) THEN FS.user_id END) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then fs.user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS FS, ");
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) MAX_MODULE_COMPLETE,max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE ");
			queryBuilder.append("from MV_FACT_MODULE_STATUS fs1 ");
			queryBuilder.append("WHERE FS1.dashboard_id = ? ");
			queryBuilder.append("AND FS1.SITE_ID = ? ");
			queryBuilder.append("AND fs1.ASSIGNMENT_TYPE ='Mandatory' group by user_id) TMP ");
			queryBuilder.append("WHERE FS.dashboard_id = ? ");
			queryBuilder.append("AND FS.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory'" );
			queryBuilder.append("AND TMP.USER_ID=FS.USER_ID ");
			queryBuilder.append("AND FS.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?) ");
			queryBuilder.append("GROUP BY FS.").append(groupByColumnName).append(") ");
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");

			params.add(dashboardId);	
			params.add(siteId);
			params.add(dashboardId);	
			params.add(siteId);
			params.add(userId);
			params.add(siteId);
			params.add(dashboardId);
		}
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	AssignmentStatusForManagerDTO dto = new AssignmentStatusForManagerDTO();
	    		dto.setCompleted(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    		dto.setIncomplete(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT")));
	    		dto.setPastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	    		dto.setName((String) resultMap.get(groupByColumnName));
	    	dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public List<AssignmentStatusForManagerDTO> get3rdChartUserCompletionStatusRegularUserSameHierarchy(
			Long dashboardId, Long siteId, String userId, String groupByColumnName, Long hasDashboardConfig,
			String dashboardConfig, String userColumnForDashboardConfig) {
		
		List<AssignmentStatusForManagerDTO> dtoList = new ArrayList<AssignmentStatusForManagerDTO> ();
		
		StringBuilder queryBuilder = new StringBuilder();
		ArrayList<Object> params = new ArrayList<Object> ();
		
		if(groupByColumnName.equalsIgnoreCase("BASE_CATALOG_ID") || groupByColumnName.equalsIgnoreCase("MODULE_TITLE")) {
			
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct decode(MODULE_COMPLETE,1,user_id)) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct decode(MODULE_INCOMPLETE,1,user_id)) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS FS ");
			queryBuilder.append("WHERE FS.dashboard_id = ? ");
			queryBuilder.append("AND FS.SITE_ID = ? ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("or fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("GROUP BY FS.").append(groupByColumnName).append(") ");
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");

			params.add(dashboardId);	
			params.add(siteId);
			params.add(userId);
			params.add(siteId);
			params.add(dashboardId);
			
		} else { 
			
			queryBuilder.append("SELECT * FROM ");
			queryBuilder.append("(SELECT DISTINCT fs.").append(groupByColumnName).append(" AS ").append(groupByColumnName).append(", ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=0 AND MAX_MODULE_COMPLETE=1) THEN FS.user_id END) AS COMPLETE_COUNT, ");
			queryBuilder.append("count(distinct CASE WHEN (MAX_MODULE_INCOMPLETE=1) THEN FS.user_id END) AS INCOMPLETE_COUNT, ");
			queryBuilder.append("count(distinct case when (MOD_NOT_STARTED_PAST_DUE =1 or MOD_IN_PROCESS_PAST_DUE =1 ) then fs.user_id end) AS INCOMPLETE_PAST_DUE ");
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS FS, ");
			queryBuilder.append("(select user_id,max(MODULE_COMPLETE) ");
			queryBuilder.append("MAX_MODULE_COMPLETE,max(MODULE_INCOMPLETE) MAX_MODULE_INCOMPLETE from ");
			queryBuilder.append("MV_FACT_MODULE_STATUS fs1 WHERE fs1.DASHBOARD_ID = ? AND fs1.site_id = ? AND ");
			queryBuilder.append("fs1.ASSIGNMENT_TYPE ='Mandatory'  group by user_id) TMP ");
			queryBuilder.append("WHERE FS.dashboard_id = ? ");
			queryBuilder.append("AND FS.SITE_ID = ? ");
			queryBuilder.append("AND TMP.USER_ID=FS.USER_ID ");
			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE manager_id = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			queryBuilder.append("or fs.").append(dashboardConfig).append(" = '").append(userColumnForDashboardConfig).append("') ");
			queryBuilder.append("GROUP BY FS.").append(groupByColumnName).append(") ");
			queryBuilder.append("WHERE INCOMPLETE_PAST_DUE IS NOT NULL ");
			queryBuilder.append("OR (COMPLETE_COUNT IS NOT NULL AND COMPLETE_COUNT <> 0) ");
			queryBuilder.append("OR (INCOMPLETE_COUNT IS NOT NULL AND INCOMPLETE_COUNT<> 0)");

			params.add(dashboardId);	
			params.add(siteId);
			params.add(dashboardId);	
			params.add(siteId);
			params.add(userId);
			params.add(siteId);
			params.add(dashboardId);
		}
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();	
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	AssignmentStatusForManagerDTO dto = new AssignmentStatusForManagerDTO();
	    		dto.setCompleted(DBUtils.getLongFromResultMapObject(resultMap.get("COMPLETE_COUNT")));
	    		dto.setIncomplete(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_COUNT")));
	    		dto.setPastDue(DBUtils.getLongFromResultMapObject(resultMap.get("INCOMPLETE_PAST_DUE")));
	    		dto.setName((String) resultMap.get(groupByColumnName));
	    	dtoList.add(dto);
		}
		
		return dtoList;
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
}
