package com.lrn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LogUtils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss a");
	
	public static void logTime(Logger logger, String methodName, long startTime, long endTime) {
		
		long ms = endTime - startTime;
		long ss = ms / 1000;
		
		logger.debug(sdf.format(new Date()) + " :: " + methodName + " took " + ms + "ms (" + ss + "sec) to execute... " );
		
	}
}
