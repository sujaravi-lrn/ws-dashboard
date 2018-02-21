package com.lrn.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lrn.constants.ModuleCourseStatusDetailMap;
import com.lrn.constants.ModuleCourseStatusMap;
import com.lrn.dao.ICompletionStatusReportDao;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.response.CompletionStatusReportDrilldownResponseDTO;
import com.lrn.model.FactModuleStatus;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

public class CompletionStatusReportDao 
			extends	GenericDao<FactModuleStatus, Long> 
			implements ICompletionStatusReportDao {

	private static final Logger logger = Logger.getLogger(CompletionStatusReportDao.class);

	public CompletionStatusReportDao(Class<FactModuleStatus> persistentClass) {
		super(persistentClass);
	}
	
	@Override
	public Long getCompletionStatusReportDrilldownSuperUserTotalRecords(
			DrilldownRequestDTO requestDTO, Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		long startTime = System.currentTimeMillis();

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT COUNT(*) as total_records FROM ");
			
			//add this part only if the filter/search column is DAYS_PAST_DUE - this is to avoid Oracle Invalid Identifier error in the where condition
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) 
					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE")) { 
				queryBuilder.append("( SELECT DISTINCT CURRICULUM_ID, "); 
				queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS DAYS_PAST_DUE FROM ");
			}
			
			queryBuilder.append("MV_fact_module_status fs ");
			queryBuilder.append("WHERE fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.site_id = ? ");
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
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
			
			//do this only if filteredColumn != isNullValue
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
				addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
		
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) 
					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE")) 
				queryBuilder.append(" ) WHERE DAYS_PAST_DUE = ").append(requestDTO.getFilteredColumnValue());
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
					
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getCompletionStatusReportDrilldownSuperUserTotalRecords :\n" + queryBuilder.toString());
		logger.debug("getCompletionStatusReportDrilldownSuperUserTotalRecords Query Took :: " + (endTime-startTime)/1000 + " sec");
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
	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownSuperUser(
			DrilldownRequestDTO requestDTO,
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap,
			Map<String, ArrayList<String>> dataPrivacyColumnMap) {
		
		long startTime = System.currentTimeMillis();

		CompletionStatusReportDrilldownResponseDTO dto = new CompletionStatusReportDrilldownResponseDTO();
		String orderBy = requestDTO.getOrderByColumnName();

		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		List<String> columnNames = new ArrayList<String> (); 
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			//set the 1st column as default orderBy
			Map<String, Long> colMap = columnNameANDColumnDisplayNameAndSequenceMap.get(col);
			for(String colCol : colMap.keySet()) {
				Long sequence = colMap.get(colCol);
				if(StringUtils.isEmpty(orderBy) && sequence == 1)
					orderBy = col;
			}

			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) { //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
				columnNames.add(col); //add all the columns to this list
			}
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * FROM (");
			queryBuilder.append("SELECT ROWNUM row_id, ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap).append(" DUE_DATE, days_past_due FROM ");
			queryBuilder.append("(SELECT CURRICULUM_ID, ");
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
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
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
			
			//do this only if filteredColumn != isNullValue
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
				addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
			
			queryBuilder.append("ORDER BY ").append(orderBy).append(" ").append(requestDTO.getOrderByDirection()).append(" ) ");
			
			//DAYS_PAST_DUE has to be added here and not in the filterColumn above. otherwise you'll get invalid identifier oracle error
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE"))
				queryBuilder.append("WHERE DAYS_PAST_DUE like '%").append(requestDTO.getFilteredColumnValue()).append("%' ");
		
			queryBuilder.append(" )");
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) 
			queryBuilder.append(" WHERE row_id BETWEEN ? AND ?");
			
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
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getCompletionStatusReportDrilldownSuperUser :\n" + queryBuilder.toString());
		logger.debug("getCompletionStatusReportDrilldownSuperUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	//if(checkDaysPastDueFilter(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(),	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");
		
		return dto;
	}

	@Override
	public Long getCompletionStatusReportDrilldownRegularUserTotalRecords(
			DrilldownRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		long startTime = System.currentTimeMillis();
		
		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			
			//set the 1st column as default orderBy
			Map<String, Long> colMap = columnNameANDColumnDisplayNameAndSequenceMap.get(col);
			for(String colCol : colMap.keySet())
				colMap.get(colCol);
			
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT COUNT(*) as total_records FROM ");
			
			//add this part only if the filter/search column is DAYS_PAST_DUE - this is to avoid Oracle Invalid Identifier error in the where condition
//			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
//					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) 
//					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE")) {
				queryBuilder.append("( SELECT DISTINCT CURRICULUM_ID, "); 
				if(columnsFromColumnsMap.length() > 0)
					queryBuilder.append(columnsFromColumnsMap);
				queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
				queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),0) AS DAYS_PAST_DUE FROM ");
//			}
			
			queryBuilder.append("MV_FACT_MODULE_STATUS fs, DIM_USER_HIERARCHY DUH ");
			queryBuilder.append("WHERE fs.USER_ID = DUH.user_id ");
			queryBuilder.append("AND fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");
			queryBuilder.append("AND (duh.MANAGER_ID = ? ");
			
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null)) 
				queryBuilder.append("or fs.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
			queryBuilder.append(") ");
			
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
			//do this only if filteredColumn != isNullValue
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
				addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
			
			queryBuilder.append(" ) ");
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) 
					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE")) 
				queryBuilder.append(" WHERE DAYS_PAST_DUE = ").append(requestDTO.getFilteredColumnValue());
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(requestDTO.getDashboardId());	
			params.add(requestDTO.getSiteId());
			params.add(requestDTO.getUserId());
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getCompletionStatusReportDrilldownRegularUserTotalRecords :\n" + queryBuilder.toString());
		logger.debug("getCompletionStatusReportDrilldownRegularUserTotalRecords Query Took :: " + (endTime-startTime)/1000 + " sec");
//		long startTime2 = System.currentTimeMillis();
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	return DBUtils.getLongFromResultMapObject(resultMap.get("total_records"));
	    }
	    
		endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");
		
		return 0L;
	}
	
	@Override
	public CompletionStatusReportDrilldownResponseDTO getCompletionStatusReportDrilldownRegularUser(
			DrilldownRequestDTO requestDTO, String sameDataHierCol, String supervisorHierCol, 
			String additionalHierCol, String userColValueForSameDataHierCol, 
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {

		long startTime = System.currentTimeMillis();

		CompletionStatusReportDrilldownResponseDTO dto = new CompletionStatusReportDrilldownResponseDTO();
		String orderBy = requestDTO.getOrderByColumnName();

		//append the columns from the Map
		StringBuffer columnsFromColumnsMap = new StringBuffer();
		for(String col : columnNameANDColumnDisplayNameAndSequenceMap.keySet()) {
			
			//set the 1st column as default orderBy
			Map<String, Long> colMap = columnNameANDColumnDisplayNameAndSequenceMap.get(col);
			for(String colCol : colMap.keySet()) {
				Long sequence = colMap.get(colCol);
				if(StringUtils.isEmpty(orderBy) && sequence == 1)
					orderBy = col;
			}
			
			if(!(col.toUpperCase().equals("DAYS_PAST_DUE") || col.toUpperCase().equals("DUE_DATE") || col.toUpperCase().equals("ISNULLVALUE"))) //don't add DAYS_PAST_DUE - its calculated below...
				columnsFromColumnsMap.append(col).append(", ");
		}
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT * FROM (");
			queryBuilder.append("SELECT ROWNUM row_id, ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap).append(" DUE_DATE, days_past_due FROM ");
			queryBuilder.append("(SELECT DISTINCT CURRICULUM_ID, ");
			if(columnsFromColumnsMap.length() > 0)
				queryBuilder.append(columnsFromColumnsMap);
			queryBuilder.append("fs.DUE_DATE - 1 AS DUE_DATE, ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS, 'In Progress', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Not Started', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(SYSDATE)-TRUNC(due_date-1),0),'Complete', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0),'Complete (credited)', ");
			queryBuilder.append("DECODE(fs.MODULE_STATUS_DETAIL,'Past Due',TRUNC(fs.COMPLETION_DATE)-TRUNC(due_date-1),0), 0 ) AS days_past_due ");
			
			queryBuilder.append("FROM MV_FACT_MODULE_STATUS fs WHERE ");

			queryBuilder.append("fs.DASHBOARD_ID = ? ");
			queryBuilder.append("AND fs.SITE_ID = ? ");
			queryBuilder.append("AND fs.ASSIGNMENT_TYPE ='Mandatory' ");

			queryBuilder.append("AND ((fs.USER_ID IN (SELECT DISTINCT user_id FROM DIM_USER_HIERARCHY ");
			queryBuilder.append("WHERE MANAGER_ID = ? ");
			queryBuilder.append("AND SITE_ID = ? ");
			queryBuilder.append("AND DASHBOARD_ID = ?)) ");
			if(sameDataHierCol != null && (supervisorHierCol != null || additionalHierCol != null))
					queryBuilder.append("or fad.").append(sameDataHierCol).append(" = '").append(userColValueForSameDataHierCol).append("' ");
				queryBuilder.append(") ");
					
			
			if(!StringUtils.isEmpty(requestDTO.getCompletionStatus()) && !"null".equalsIgnoreCase(requestDTO.getCompletionStatus())) {
				queryBuilder.append("AND fs.MODULE_STATUS ").append(ModuleCourseStatusMap.getValue(requestDTO.getCompletionStatus()));
				queryBuilder.append("AND fs.MODULE_STATUS_DETAIL ").append(ModuleCourseStatusDetailMap.getValue(requestDTO.getCompletionStatus()));
			}
			
			//do this only if filteredColumn != isNullValue
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& !StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumnValue()) ) 
				addFilterColumn(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(), queryBuilder);
			
			queryBuilder.append("ORDER BY ").append(orderBy).append(" ").append(requestDTO.getOrderByDirection()).append(" ) ");
			
			//DAYS_PAST_DUE has to be added here and not in the filterColumn above. otherwise you'll get invalid identifier oracle error
			if(!StringUtil.isDashboardGroupByColumnValueNull(requestDTO.getFilteredColumn()) 
					&& requestDTO.getFilteredColumn().equalsIgnoreCase("DAYS_PAST_DUE"))
				queryBuilder.append("WHERE DAYS_PAST_DUE like '%").append(requestDTO.getFilteredColumnValue()).append("%' ");
			
		queryBuilder.append(" )");
		if(requestDTO.getRowsSize() != null && requestDTO.getRowsSize() != 0L) 
			queryBuilder.append(" WHERE row_id BETWEEN ? AND ?");
			
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
		List<Map<String, Object>> dataMap = new ArrayList<Map<String, Object>>();
		
		long endTime = System.currentTimeMillis();
//		logger.debug("Query for getCompletionStatusReportDrilldownRegularUser :\n" + queryBuilder.toString());
		logger.debug("getCompletionStatusReportDrilldownRegularUser Query Took :: " + (endTime-startTime)/1000 + " sec");
		long startTime2 = System.currentTimeMillis();
		
		while(itr.hasNext()) {
	    	Map<String, Object> resultMap = itr.next();
	    	//check to see if filtered column is DAYS_PAST_DUE, then filter it with the value
	    	//if(checkDaysPastDueFilter(requestDTO.getFilteredColumn(), requestDTO.getFilteredColumnValue(),	dataMap, resultMap))
	    		dataMap.add(resultMap);
	    }
	    
	    dto.setData(dataMap);
	    
	    endTime = System.currentTimeMillis();
//		logger.debug("Data processing after query took:: " + (endTime-startTime2)/1000 + " sec");
		
		return dto;
	}
	

	private void addFilterColumn(String filteredColumn,	String filteredColumnValue, StringBuilder queryBuilder) {
		
		if(StringUtil.isDashboardGroupByColumnValueNull(filteredColumnValue))
			queryBuilder.append("AND fs.").append(filteredColumn).append(" IS NULL ");
		else {
			if(filteredColumn.contains("DUE_DATE")) { //due_date - 1
				String fromDate = filteredColumnValue.substring(0, 10);
				String toDate = filteredColumnValue.substring(12);
				queryBuilder.append("AND fs.").append(filteredColumn).append("-1 BETWEEN TO_DATE('").append(fromDate).append("','mm-dd-yyyy') AND TO_DATE('").append(toDate).append(" 23:59:59','mm-dd-yyyy HH24:MI:SS') ");
				//queryBuilder.append("AND to_char(fs.").append(filteredColumn).append("-1,'mm-dd-yyyy') = '").append(filteredColumnValue).append("' ");
			} else if(filteredColumn.contains("DATE")) { //date as is
				String fromDate = filteredColumnValue.substring(0, 10);
				String toDate = filteredColumnValue.substring(12);
				queryBuilder.append("AND fs.").append(filteredColumn).append(" BETWEEN TO_DATE('").append(fromDate).append("','mm-dd-yyyy') AND TO_DATE('").append(toDate).append(" 23:59:59','mm-dd-yyyy HH24:MI:SS') ");
				//queryBuilder.append("AND to_char(fs.").append(filteredColumn).append(",'mm-dd-yyyy') = '").append(filteredColumnValue).append("' ");
			} else if(filteredColumn.contains("TIME_SPENT")) { // HH:MM:SS
				String[] timeArr = filteredColumnValue.split(":");
				if(timeArr.length == 3) {
					Long seconds = Long.parseLong(timeArr[0]) * 60 * 60 + Long.parseLong(timeArr[1]) * 60 + Long.parseLong(timeArr[2]); 
					queryBuilder.append("AND fs.").append(filteredColumn).append(" = ").append(seconds).append(" ");
				}
			} else if(filteredColumn.contains("SCORE")) //NUMBER columns
				queryBuilder.append("AND fs.").append(filteredColumn).append(" = ").append(filteredColumnValue).append(" ");
			else if(!filteredColumn.contains("DAYS_PAST_DUE")) {
				if(filteredColumnValue.contains("'"))
					filteredColumnValue = filteredColumnValue.replaceAll("'", "''");
				queryBuilder.append("AND upper(fs.").append(filteredColumn).append(") like '%").append(filteredColumnValue.toUpperCase()).append("%' ");
			}
		}
	}
}
