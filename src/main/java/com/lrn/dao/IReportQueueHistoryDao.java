package com.lrn.dao;

import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public interface IReportQueueHistoryDao extends IGenericDao<ReportQueueHistory, Long>{
	
	boolean moveReportQueueRequestToHistory(ReportQueue reportQueue);

}
