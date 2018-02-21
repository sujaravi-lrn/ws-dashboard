package com.lrn.manager.impl;

import java.util.Date;

import com.lrn.dao.IGenericDao;
import com.lrn.dao.IReportQueueHistoryDao;
import com.lrn.manager.IReportQueueHistoryManager;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public class ReportQueueHistoryManager extends GenericManager<ReportQueueHistory, Long>
				implements IReportQueueHistoryManager {

	private IReportQueueHistoryDao reportQueueHistoryDao;

	public ReportQueueHistoryManager(IGenericDao<ReportQueueHistory, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setReportQueueHistoryDao(IReportQueueHistoryDao reportQueueHistoryDao) {
		this.reportQueueHistoryDao = reportQueueHistoryDao;
	}

	@Override
	public ReportQueueHistory moveReportQueueRequestToHistory(ReportQueue reportQueue, Date startDate) {
		
		ReportQueueHistory reportQueueHistory = getReportQueueHistoryFromReportQueue(reportQueue, startDate);
		
		return this.save(reportQueueHistory);
	}

	private ReportQueueHistory getReportQueueHistoryFromReportQueue(ReportQueue reportQueue, Date startDate) {
		
		ReportQueueHistory history = new ReportQueueHistory();
			history.setId(-1L);
			history.setProcessed(1L);
			
			history.setReportName(reportQueue.getReportName());
			history.setSiteId(reportQueue.getSiteId());
			history.setDashboardId(reportQueue.getDashboardId());
			history.setUserId(reportQueue.getUserId());
			history.setUserName(reportQueue.getUserName());
			history.setHasDashboardConfig(reportQueue.getHasDashboardConfig());
			history.setCompletionStatus(reportQueue.getCompletionStatus());
			history.setFilterByColumnName(reportQueue.getFilterByColumnName());
			history.setFilterByColumnValue(reportQueue.getFilterByColumnValue());
			history.setOrderByColumnName(reportQueue.getOrderByColumnName());
			history.setOrderByDirection(reportQueue.getOrderByDirection());
			history.setFileName(reportQueue.getFileName());
			history.setFileLocation(reportQueue.getFileLocation());
			history.setCreatedOn(new Date());
			history.setCreatedBy(reportQueue.getCreatedBy());
			history.setCreatedByUserName(reportQueue.getCreatedByUserName());
			history.setStartDate(startDate);
			history.setEndDate(new Date());
			history.setReportType(reportQueue.getReportType());
			
		return history;
	}
}
