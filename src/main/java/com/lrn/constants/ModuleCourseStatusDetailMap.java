package com.lrn.constants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * @author suja.ravi
 * Jul 11, 2016
 */
public class ModuleCourseStatusDetailMap {

	private static HashMap<String, String> moduleStatusDetailMap = new HashMap<String, String> ();

	static {
		moduleStatusDetailMap.put("complete_ON_TIME", 		" = 'On Time' ");
		moduleStatusDetailMap.put("complete_PAST_DUE", 		" = 'Past Due' ");
		moduleStatusDetailMap.put("complete_NO_DUE_DATE", 	" = 'No Due Date' ");
		
		moduleStatusDetailMap.put("complete_CREDITED_ON_TIME", 		" = 'On Time' ");
		moduleStatusDetailMap.put("complete_CREDITED_PAST_DUE", 	" = 'Past Due' ");
		moduleStatusDetailMap.put("complete_CREDITED_NO_DUE_DATE", 	" = 'No Due Date' ");
		
		moduleStatusDetailMap.put("complete", 		" in ('On Time', 'Past Due', 'No Due Date') ");
		
		moduleStatusDetailMap.put("in_PROCESS_PAST_DUE", 		" = 'Past Due' ");
		moduleStatusDetailMap.put("in_PROCESS_NO_DUE_DATE", 	" = 'No Due Date' ");
		moduleStatusDetailMap.put("in_PROCESS_NOT_DUE_YET", 	" = 'Not Due Yet' ");
		
		moduleStatusDetailMap.put("inProgress", 		" in ('Past Due', 'No Due Date', 'Not Due Yet') ");
		
		moduleStatusDetailMap.put("not_STARTED_PAST_DUE", 		" = 'Past Due' ");
		moduleStatusDetailMap.put("not_STARTED_NO_DUE_DATE", 	" = 'No Due Date' ");
		moduleStatusDetailMap.put("not_STARTED_NOT_DUE_YET", 	" = 'Not Due Yet' ");
		
		moduleStatusDetailMap.put("notStarted", 		" in ('Past Due', 'No Due Date', 'Not Due Yet') ");
		
		moduleStatusDetailMap.put("inComplete", 		" in ('Past Due', 'No Due Date', 'Not Due Yet') ");
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
