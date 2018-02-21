package com.lrn.constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author suja.ravi
 * Jul 11, 2016
 */
public class ModuleCourseCompletionStatusDetailMap {

	private static HashMap<String, String> moduleStatusDetailMap = new HashMap<String, String> ();

	static {
		moduleStatusDetailMap.put("complete_ON_TIME", " in ('On Time','No Due Date') ");
		moduleStatusDetailMap.put("incomplete_NOT_DUE_YET", " in ('No Due Date','Not Due Yet') ");
		moduleStatusDetailMap.put("complete_PAST_DUE", " = 'Past Due' ");
		moduleStatusDetailMap.put("incomplete_PAST_DUE", " = 'Past Due' ");
	}
	
	public static Set<String> getKeys() {
		return moduleStatusDetailMap.keySet();
	}

	public static Collection<String> getValues() {
		return moduleStatusDetailMap.values();
	}
	
	public static String getValue(String key) {
		return moduleStatusDetailMap.get(key);
	}
}
