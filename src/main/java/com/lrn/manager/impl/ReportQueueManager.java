package com.lrn.manager.impl;

import java.util.Date;
import java.util.List;

import com.lrn.dao.IGenericDao;
import com.lrn.dao.IReportQueueDao;
import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.manager.IReportQueueManager;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;
import com.lrn.util.StringUtil;

public class ReportQueueManager extends GenericManager<ReportQueue, Long>
				implements IReportQueueManager {

	private IReportQueueDao reportQueueDao;

	private String dashboardQueueReportsPath;
	
	public ReportQueueManager(IGenericDao<ReportQueue, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setReportQueueDao(IReportQueueDao reportQueueDao) {
		this.reportQueueDao = reportQueueDao;
	}

	public void setDashboardQueueReportsPath(String dashboardQueueReportsPath) {
		this.dashboardQueueReportsPath = dashboardQueueReportsPath;
	}

	@Override
	public ReportQueue addToQueue(DrilldownRequestDTO requestDTO, String reportName, String fileName) {
		
		ReportQueue rq = new ReportQueue();
			rq.setId(-1L);
			rq.setReportName(reportName);
			rq.setDashboardId(requestDTO.getDashboardId());
			rq.setSiteId(requestDTO.getSiteId());
			rq.setUserId(requestDTO.getUserId());
			rq.setUserName(requestDTO.getUserName());
			rq.setHasDashboardConfig(requestDTO.getHasDashboardConfig());
			rq.setCompletionStatus(requestDTO.getCompletionStatus());
			rq.setFilterByColumnName(requestDTO.getFilteredColumn());
			rq.setFilterByColumnValue(requestDTO.getFilteredColumnValue());
			rq.setOrderByColumnName(requestDTO.getOrderByColumnName());
			rq.setOrderByDirection(requestDTO.getOrderByDirection());
			rq.setProcessed(0L);
			//rq.setFileLocation(dashboardQueueReportsPath);
			rq.setFileLocation(null);
			rq.setFileName(fileName + "-" + requestDTO.getUserId() + "-" 
					+ StringUtil.getStringFromDateInMMddyyFormat(new Date()) + "-" 
					+ StringUtil.getRandomHexString(16));
			rq.setCreatedOn(new Date());
			rq.setCreatedBy(requestDTO.getLoggedInUserId());
			rq.setCreatedByUserName(requestDTO.getLoggedInUserName());
			rq.setStartDate(new Date());
			rq.setEndDate(new Date());
			rq.setReportType(requestDTO.getReportType());
		
		rq = this.save(rq);
		
		return rq;
	}
	
	@Override
	public ReportQueue getReportQueueRequest() {
		
		return reportQueueDao.getReportQueueRequest();
	}


	@Override
	public List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId) {
		return reportQueueDao.getReportQueueHistory(siteId, dashboardId, userId);
	}
}
