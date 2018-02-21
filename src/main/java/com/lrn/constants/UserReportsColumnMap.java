package com.lrn.constants;

import java.util.HashMap;

public class UserReportsColumnMap {

	private static HashMap<String, String> columnMap = new HashMap<String, String> ();
	private static HashMap<String, String> calculatedColumnMap = new HashMap<String, String> ();
	
	static {
		columnMap.put("FIRSTNAME", "FIRSTNAME");
		columnMap.put("LASTNAME", "LASTNAME");
		columnMap.put("SUPERFIRSTNAME", "SUPERFIRSTNAME");
		columnMap.put("MIDDLENAME", "MIDDLENAME");
		columnMap.put("EMAIL", "EMAIL");
		columnMap.put("SUPERMIDDLENAME", "SUPERMIDDLENAME");
		columnMap.put("LANGUAGE", "LANGUAGE");
		columnMap.put("LOGIN_NAME", "LOGIN_NAME");
		
		
		calculatedColumnMap.put("NOT_STARTED_PAST_DUE", "SUM(fad.MOD_NOT_STARTED_PAST_DUE) AS NOT_STARTED_PAST_DUE");
		calculatedColumnMap.put("IN_PROCESS_PAST_DUE", "SUM(fad.MOD_IN_PROCESS_PAST_DUE) AS IN_PROCESS_PAST_DUE");
		calculatedColumnMap.put("COMPLETE_COUNT", "SUM(fad.mod_complete_on_time + fad.mod_complete_no_due_date + fad.mod_complete_past_due + fad.MOD_COMPLETE_CREDITED_ON_TIME + fad.MOD_COMPLETE_CREDIT_PAST_DUE + fad.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_COUNT");
		calculatedColumnMap.put("NOT_STARTED_NO_DUE_DATE", "SUM(fad.MOD_NOT_STARTED_NO_DUE_DATE) AS NOT_STARTED_NO_DUE_DATE");
		calculatedColumnMap.put("NOT_STARTED_NOT_DUE_YET", "SUM(fad.MOD_NOT_STARTED_NOT_DUE_YET) AS NOT_STARTED_NOT_DUE_YET");
		calculatedColumnMap.put("IN_PROCESS_NOT_DUE_YET", "SUM(fad.MOD_IN_PROCESS_NOT_DUE_YET) AS IN_PROCESS_NOT_DUE_YET");
		calculatedColumnMap.put("COMPLETE_NO_DUE_DATE", "SUM(fad.MOD_COMPLETE_NO_DUE_DATE + fad.MOD_COMP_CREDIT_NO_DUE_DATE) AS COMPLETE_NO_DUE_DATE");
		calculatedColumnMap.put("COMPLETE_PAST_DUE", "SUM(fad.MOD_COMPLETE_PAST_DUE + fad.MOD_COMPLETE_CREDIT_PAST_DUE) AS COMPLETE_PAST_DUE");
		calculatedColumnMap.put("COMPLETE_ON_TIME", "SUM(fad.MOD_COMPLETE_ON_TIME + fad.MOD_COMPLETE_CREDITED_ON_TIME) AS COMPLETE_ON_TIME");
		calculatedColumnMap.put("IN_PROCESS_NO_DUE_DATE", "SUM(fad.MOD_IN_PROCESS_NO_DUE_DATE) AS IN_PROCESS_NO_DUE_DATE");
		calculatedColumnMap.put("INCOMPLETE_COUNT", "SUM(MOD_NOT_STARTED_NOT_DUE_YET + MOD_NOT_STARTED_PAST_DUE + MOD_NOT_STARTED_NO_DUE_DATE + MOD_IN_PROCESS_NOT_DUE_YET + MOD_IN_PROCESS_PAST_DUE + MOD_IN_PROCESS_NO_DUE_DATE) AS INCOMPLETE_COUNT");
	}
	
	public static String getColumnValue(String key) {
		return columnMap.get(key);
	}
	
	public static String getCalculatedColumnValue(String key) {
		return calculatedColumnMap.get(key);
	}
}
