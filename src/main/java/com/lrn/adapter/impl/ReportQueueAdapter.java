package com.lrn.adapter.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.IReportQueueAdapter;
import com.lrn.constants.ModuleCourseStatusDetailMap;
import com.lrn.constants.UserReportsColumnMap;
import com.lrn.enumType.DrillDownColumns;
import com.lrn.manager.ICompanyUsersColumnManager;
import com.lrn.manager.IReportQueueHistoryManager;
import com.lrn.manager.IReportQueueManager;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public class ReportQueueAdapter implements IReportQueueAdapter {

	public IReportQueueManager reportQueueManager;
	public IReportQueueHistoryManager reportQueueHistoryManager;
	public ICompanyUsersColumnManager companyUsersColumnManager;
	
	public void setReportQueueManager(IReportQueueManager reportQueueManager) {
		this.reportQueueManager = reportQueueManager;
	}

	public void setReportQueueHistoryManager(IReportQueueHistoryManager reportQueueHistoryManager) {
		this.reportQueueHistoryManager = reportQueueHistoryManager;
	}

	public void setCompanyUsersColumnManager(
			ICompanyUsersColumnManager companyUsersColumnManager) {
		this.companyUsersColumnManager = companyUsersColumnManager;
	}

	@Override
	public ReportQueue getReportQueueRequest() {
		return reportQueueManager.getReportQueueRequest();
	}

	@Override
	public List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId) {
		
		Map<String, String> companyUsersColumnNameDisplayNameMap = companyUsersColumnManager.getCompanyColumnNameAndDisplayNameMap(siteId);
		Map<String, String> drilldownColumnMap = getDrillDownColumnMap();
		
		Map<String, String> statusReportCompletionStatusDrilldownUserColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 14L, "User Columns");
		Map<String, String> statusReportCompletionStatusDrilldownModuleReportColumnList = getDrillDownReportColumnList(drilldownColumnMap, dashboardId, 14L, "Module Report Columns");
		statusReportCompletionStatusDrilldownUserColumnList.putAll(statusReportCompletionStatusDrilldownModuleReportColumnList);
		
		Map<String, String> statusReportUserProgressDrilldownUserColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 15L, "User Columns");
		Map<String, String> statusReportUserProgressDrilldownModuleReportColumnList = getDrillDownReportColumnList(drilldownColumnMap, dashboardId, 15L, "Module Report Columns");
		statusReportUserProgressDrilldownUserColumnList.putAll(statusReportUserProgressDrilldownModuleReportColumnList);
		
		List<ReportQueueHistory> reportHistoryList = reportQueueManager.getReportQueueHistory(siteId, dashboardId, userId);
		
		for(ReportQueueHistory rqh: reportHistoryList) {
			
			if(rqh.getFilterByColumnName() != null) {
				String colName = rqh.getFilterByColumnName().toUpperCase();
				String displayName = "";
				
				if(rqh.getFileName().startsWith("UserReport")) 
					displayName = statusReportUserProgressDrilldownUserColumnList.get(colName);
				else if(rqh.getFileName().startsWith("CompletionReport")) 
					displayName = statusReportCompletionStatusDrilldownUserColumnList.get(colName);
				
				displayName = (displayName == null ? drilldownColumnMap.get(colName) : displayName);
				displayName = (displayName == null ? getDrillDownUserSummaryDetailColumnMap().get(colName) : displayName);

				rqh.setFilterByColumnDisplayName(displayName);
			}
		}
	
		return reportHistoryList;
	}

	private Map<String, String> getDrillDownReportColumnList(
			Map<String, String> companyColumnNameDisplayNameMap, Long dashboardId, long configTypeId, String columnType) {

		Map<String, String> drillDownReportColumnList = new HashMap<String, String> ();
		for(String colName : companyColumnNameDisplayNameMap.keySet()) 
			drillDownReportColumnList.put(colName.toUpperCase(), companyColumnNameDisplayNameMap.get(colName));
		
		return drillDownReportColumnList;
	}
	
	private Map<String, String> getDrillDownColumnMap() {
		Map<String, String> drillDownColumnMap = new HashMap<String, String> ();
		for(DrillDownColumns.DrillDownDetailsColumnsEnum drillDownCol: DrillDownColumns.DrillDownDetailsColumnsEnum.values()) {
			drillDownColumnMap.put(drillDownCol.getColumnName(), drillDownCol.getColumnDisplayName());
		}
		return drillDownColumnMap;
	}
	
	private Map<String, String> getDrillDownUserSummaryDetailColumnMap() {
		Map<String, String> drillDownUserSummaryDetailColumnMap = new HashMap<String, String> ();
		for(DrillDownColumns.DrillDownSummaryColumnsEnum drillDownUserSummaryDetailCol: DrillDownColumns.DrillDownSummaryColumnsEnum.values()) {
			drillDownUserSummaryDetailColumnMap.put(drillDownUserSummaryDetailCol.getColumnName(), drillDownUserSummaryDetailCol.getColumnDisplayName());
		}
		return drillDownUserSummaryDetailColumnMap;
	}

}
