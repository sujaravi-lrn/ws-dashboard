package com.lrn.queue;

import java.util.Date;

import com.lrn.adapter.IReportQueueAdapter;
import com.lrn.model.ReportQueue;

public class ReportQueueThread extends Thread {

	IReportQueueAdapter reportQueueAdapter;
	private String runReportQueueThread;
	
	public ReportQueueThread() {
		
	}
			
	public ReportQueueThread(String threadName) {
		setName(threadName);
	}
	
	public void setReportQueueAdapter(IReportQueueAdapter reportQueueAdapter) {
		this.reportQueueAdapter = reportQueueAdapter;
	}



	@Override
	public void run() {

		while(true) {
			System.out.print(new Date());
			System.out.println(" Executing thread........ " + getName());
			
			ReportQueue reportQueueRequest = reportQueueAdapter.getReportQueueRequest();
			boolean processed = processReportQueueRequest(reportQueueRequest);
			
			try {
				Thread.sleep(3000);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	private boolean processReportQueueRequest(ReportQueue reportQueueRequest) {
		
		try {
			Date startDate = new Date();
			
			//TO DO - call the web service report
			
			//generate the Excel report
			
			
			//move the report queue to report queue history
			reportQueueRequest.setStartDate(startDate);
			//reportQueueAdapter.moveReportQueueRequestToHistory(reportQueueRequest, new );
			
		} catch(Exception ex) {
			
			// TO DO - error handling - send email etc
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
}
