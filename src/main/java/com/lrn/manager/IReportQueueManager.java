package com.lrn.manager;

import java.util.List;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public interface IReportQueueManager extends IGenericManager<ReportQueue, Long> {

	ReportQueue addToQueue(DrilldownRequestDTO requestDTO, String reportName, String fileName);

	ReportQueue getReportQueueRequest();

	List<ReportQueueHistory> getReportQueueHistory(Long siteId, Long dashboardId, String userId);

}
