package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IReportQueueDao;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public class ReportQueueDao extends GenericDao<ReportQueue, Long> 
		implements IReportQueueDao {

	public ReportQueueDao(Class<ReportQueue> persistentClass) {
		super(persistentClass);
	}

	@Override
	public ReportQueue getReportQueueRequest() {
		
        ReportQueue rq = null;

        StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select rownum, ID, REPORT_NAME, DASHBOARD_ID, SITE_ID, USER_ID, USER_NAME, HAS_DASHBOARD_CONFIG, COMPLETION_STATUS, ");
			queryBuilder.append("FILTERBY_COLUMNNAME, FILTERBY_COLUMNVALUE, ORDERBY_COLUMN_NAME, ORDERBY_DIRECTION, PROCESSED, ");
			queryBuilder.append("FILE_LOCATION, FILE_NAME, CREATED_ON, CREATED_BY, CREATED_BY_USER_NAME,START_DATE, END_DATE, REPORT_TYPE ");
			queryBuilder.append("from report_queue where rownum = ? ");
			queryBuilder.append("order by id desc");
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(1);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		
        while(itr.hasNext()) {
            Map<String, Object> rs = itr.next();
            
            rq = new ReportQueue();
            BigDecimal bd = (BigDecimal) rs.get("ID");
            	rq.setId(bd != null ? bd.longValue() : null);
            rq.setReportName((String) rs.get("REPORT_NAME"));
            bd = (BigDecimal) rs.get("DASHBOARD_ID");
        		rq.setDashboardId(bd != null ? bd.longValue() : null);
        	bd = (BigDecimal) rs.get("SITE_ID");
        		rq.setSiteId(bd != null ? bd.longValue() : null);
            rq.setUserId((String) rs.get("USER_ID"));
            rq.setUserName((String) rs.get("USER_NAME"));
            bd = (BigDecimal) rs.get("HAS_DASHBOARD_CONFIG");
    			rq.setHasDashboardConfig(bd != null ? bd.longValue() : null);
            rq.setCompletionStatus((String) rs.get("COMPLETION_STATUS"));
            rq.setFilterByColumnName((String) rs.get("FILTERBY_COLUMNNAME"));
            rq.setFilterByColumnValue((String) rs.get("FILTERBY_COLUMNVALUE"));
            rq.setOrderByColumnName((String) rs.get("ORDERBY_COLUMN_NAME"));
            rq.setOrderByDirection((String) rs.get("ORDERBY_DIRECTION"));
            bd = (BigDecimal) rs.get("PROCESSED");
				rq.setProcessed(bd != null ? bd.longValue() : null);
            rq.setFileLocation((String) rs.get("FILE_LOCATION"));
            rq.setFileName((String) rs.get("FILE_NAME"));
            rq.setReportType((String) rs.get("REPORT_TYPE"));
            rq.setCreatedBy((String) rs.get("CREATED_BY"));
            rq.setCreatedByUserName((String) rs.get("CREATED_BY_USER_NAME"));
            rq.setCreatedOn((Date) rs.get("CREATED_ON"));
            rq.setStartDate((Date) rs.get("START_DATE"));
            rq.setEndDate((Date) rs.get("END_DATE"));
            
        }
        
		return rq;
	}

	@Override
	public List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId) {

        StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select ID, REPORT_NAME, DASHBOARD_ID, SITE_ID, USER_ID, USER_NAME, HAS_DASHBOARD_CONFIG, COMPLETION_STATUS, ");
			queryBuilder.append("FILTERBY_COLUMNNAME, FILTERBY_COLUMNVALUE, ORDERBY_COLUMN_NAME, ORDERBY_DIRECTION, PROCESSED, ");
			queryBuilder.append("FILE_LOCATION, FILE_NAME, CREATED_ON, CREATED_BY, CREATED_BY_USER_NAME, START_DATE, END_DATE, REPORT_TYPE ");
			queryBuilder.append("from report_queue_history where site_id = ? ");
			queryBuilder.append("and dashboard_id = ? ");
			queryBuilder.append("and CREATED_BY = ? ");
			queryBuilder.append("and rownum < 100 ");
			queryBuilder.append("order by id desc");

		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			params.add(dashboardId);
			params.add(userId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		List<ReportQueueHistory> rqList = new ArrayList<ReportQueueHistory> ();
        while(itr.hasNext()) {
            Map<String, Object> rs = itr.next();
            
            ReportQueueHistory rq = new ReportQueueHistory();
            BigDecimal bd = (BigDecimal) rs.get("ID");
            	rq.setId(bd != null ? bd.longValue() : null);
            rq.setReportName((String) rs.get("REPORT_NAME"));
            bd = (BigDecimal) rs.get("DASHBOARD_ID");
        		rq.setDashboardId(bd != null ? bd.longValue() : null);
        	bd = (BigDecimal) rs.get("SITE_ID");
        		rq.setSiteId(bd != null ? bd.longValue() : null);
            rq.setUserId((String) rs.get("USER_ID"));
            rq.setUserName((String) rs.get("USER_NAME"));
            bd = (BigDecimal) rs.get("HAS_DASHBOARD_CONFIG");
    			rq.setHasDashboardConfig(bd != null ? bd.longValue() : null);
            rq.setCompletionStatus((String) rs.get("COMPLETION_STATUS"));
            rq.setFilterByColumnName((String) rs.get("FILTERBY_COLUMNNAME"));
            rq.setFilterByColumnValue((String) rs.get("FILTERBY_COLUMNVALUE"));
            rq.setOrderByColumnName((String) rs.get("ORDERBY_COLUMN_NAME"));
            rq.setOrderByDirection((String) rs.get("ORDERBY_DIRECTION"));
            bd = (BigDecimal) rs.get("PROCESSED");
				rq.setProcessed(bd != null ? bd.longValue() : null);
            rq.setFileLocation((String) rs.get("FILE_LOCATION"));
            rq.setFileName((String) rs.get("FILE_NAME"));
            rq.setReportType((String) rs.get("REPORT_TYPE"));
            rq.setCreatedBy((String) rs.get("CREATED_BY"));
            rq.setCreatedByUserName((String) rs.get("CREATED_BY_USER_NAME"));
            rq.setCreatedOn((Date) rs.get("CREATED_ON"));
            rq.setStartDate((Date) rs.get("START_DATE"));
            rq.setEndDate((Date) rs.get("END_DATE"));
            
            rqList.add(rq);
        }
        
		return rqList;
	}

	
}
