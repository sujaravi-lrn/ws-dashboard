package com.lrn.dao;

import java.util.List;

import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public interface IReportQueueDao {

	ReportQueue getReportQueueRequest();

	List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId);

}
