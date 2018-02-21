package com.lrn.queue;

import com.lrn.adapter.IReportQueueAdapter;

public class ReportQueueProcessor {

	private String runReportQueueThread;
	private IReportQueueAdapter reportQueueAdapter;
	private boolean stop;
	
	ReportQueueThread thread1 = new ReportQueueThread();

	public void setRunReportQueueThread(String runReportQueueThread) {
		this.runReportQueueThread = runReportQueueThread;
	}
	
	public void setReportQueueAdapter(IReportQueueAdapter reportQueueAdapter) {
		this.reportQueueAdapter = reportQueueAdapter;
		thread1.setReportQueueAdapter(reportQueueAdapter);
	}

	public void init() {
		
		System.out.println("Initializing ReportQueueProcessor ... " + runReportQueueThread);
		
		//initiate the Threads only if the runReportQueueThread = true
		while("true".equalsIgnoreCase(runReportQueueThread) && !stop) {
			thread1.run();
		}
	}
	
	public void destroy() {
		stop = true;
	}
}
