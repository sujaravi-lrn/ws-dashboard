package com.lrn.manager;

import java.util.Date;

import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public interface IReportQueueHistoryManager extends IGenericManager<ReportQueueHistory, Long> {

	ReportQueueHistory moveReportQueueRequestToHistory(ReportQueue reportQueue, Date startDate);

}
