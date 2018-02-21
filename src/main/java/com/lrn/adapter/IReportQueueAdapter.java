package com.lrn.adapter;

import java.util.List;

import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public interface IReportQueueAdapter {

	public ReportQueue getReportQueueRequest();
	
	public List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId);

}
