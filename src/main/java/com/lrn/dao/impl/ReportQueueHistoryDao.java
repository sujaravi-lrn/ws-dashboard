package com.lrn.dao.impl;

import com.lrn.dao.IReportQueueHistoryDao;
import com.lrn.model.ReportQueue;
import com.lrn.model.ReportQueueHistory;

public class ReportQueueHistoryDao extends GenericDao<ReportQueueHistory, Long> 
		implements IReportQueueHistoryDao {

	public ReportQueueHistoryDao(Class<ReportQueueHistory> persistentClass) {
		super(persistentClass);
	}

	@Override
	public boolean moveReportQueueRequestToHistory(ReportQueue reportQueue) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
