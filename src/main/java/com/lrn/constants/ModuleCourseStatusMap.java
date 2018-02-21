package com.lrn.constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author suja.ravi
 * Jul 11, 2016
 */
public class ModuleCourseStatusMap {

	private static HashMap<String, String> moduleStatusMap = new HashMap<String, String> ();
	
	static {
		moduleStatusMap.put("complete_ON_TIME", 	" = 'Complete' ");
		moduleStatusMap.put("complete_PAST_DUE", 	" = 'Complete' ");
		moduleStatusMap.put("complete_NO_DUE_DATE", " = 'Complete' ");
		
		moduleStatusMap.put("complete_CREDITED_ON_TIME", 		" = 'Complete (credited)' ");
		moduleStatusMap.put("complete_CREDITED_PAST_DUE", 		" = 'Complete (credited)' ");
		moduleStatusMap.put("complete_CREDITED_NO_DUE_DATE", 	" = 'Complete (credited)' ");
		
		moduleStatusMap.put("complete", 				" in ('Complete', 'Complete (credited)') ");
		
		moduleStatusMap.put("in_PROCESS_PAST_DUE", 		" = 'In Progress' ");
		moduleStatusMap.put("in_PROCESS_NO_DUE_DATE", 	" = 'In Progress' ");
		moduleStatusMap.put("in_PROCESS_NOT_DUE_YET", 	" = 'In Progress' ");
		
		moduleStatusMap.put("inProgress", 				" = 'In Progress' ");
		
		moduleStatusMap.put("not_STARTED_PAST_DUE", 	" = 'Not Started' ");
		moduleStatusMap.put("not_STARTED_NO_DUE_DATE", 	" = 'Not Started' ");
		moduleStatusMap.put("not_STARTED_NOT_DUE_YET", 	" = 'Not Started' ");
		
		moduleStatusMap.put("notStarted", 				" = 'Not Started' ");
		
		moduleStatusMap.put("inComplete", 				" in ('In Progress', 'Not Started') ");
	}
	
	public static Set<String> getKeys() {
		return moduleStatusMap.keySet();
	}

	public static Collection<String> getValues() {
		return moduleStatusMap.values();
	}
	
	public static String getValue(String key) {
		return moduleStatusMap.get(key);
	}
}
