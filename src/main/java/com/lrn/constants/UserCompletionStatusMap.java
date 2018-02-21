package com.lrn.constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author suja.ravi
 * Jul 11, 2016
 */
public class UserCompletionStatusMap {

	private static HashMap<String, String> userCompletionStatusMap = new HashMap<String, String> ();
	
	static {
		userCompletionStatusMap.put("complete", " in ('Complete (credited)','Complete') ");
		userCompletionStatusMap.put("Incomplete", " in ('Not Started','In Progress') ");
	}
	
	public static Set<String> getKeys() {
		return userCompletionStatusMap.keySet();
	}

	public static Collection<String> getValues() {
		return userCompletionStatusMap.values();
	}
	
	public static String getValue(String key) {
		return userCompletionStatusMap.get(key);
	}
}
