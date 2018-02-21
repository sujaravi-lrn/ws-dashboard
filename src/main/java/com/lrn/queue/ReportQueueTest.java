package com.lrn.queue;

public class ReportQueueTest {

	public static void main(String args[]) {
		
		ReportQueueThread t = new ReportQueueThread();
		t.start();
	}
}