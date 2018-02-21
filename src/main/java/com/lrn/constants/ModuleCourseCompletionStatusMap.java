package com.lrn.constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author suja.ravi
 * Jul 11, 2016
 */
public class ModuleCourseCompletionStatusMap {

	private static HashMap<String, String> moduleStatusMap = new HashMap<String, String> ();
	
	static {
		moduleStatusMap.put("complete_ON_TIME", " in ('Complete','Complete (credited)') ");
		moduleStatusMap.put("complete_PAST_DUE", " in ('Complete','Complete (credited)') ");
		moduleStatusMap.put("incomplete_NOT_DUE_YET", " in ('Not Started','In Progress') ");
		moduleStatusMap.put("incomplete_PAST_DUE", " in ('In Progress','Not Started') ");
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
