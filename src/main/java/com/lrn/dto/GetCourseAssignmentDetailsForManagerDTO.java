package com.lrn.dto;

import java.util.List;
import java.util.Map;

public class GetCourseAssignmentDetailsForManagerDTO {
	
	private List<Map<String, Object>> data;

	private Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap;

	/**
	 * @return the data
	 */
	public List<Map<String, Object>> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	/**
	 * @return the columnNameANDColumnDisplayNameAndSequenceMap
	 */
	public Map<String, Map<String, Long>> getColumnNameANDColumnDisplayNameAndSequenceMap() {
		return columnNameANDColumnDisplayNameAndSequenceMap;
	}

	/**
	 * @param columnNameANDColumnDisplayNameAndSequenceMap the columnNameANDColumnDisplayNameAndSequenceMap to set
	 */
	public void setColumnNameANDColumnDisplayNameAndSequenceMap(
			Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap) {
		this.columnNameANDColumnDisplayNameAndSequenceMap = columnNameANDColumnDisplayNameAndSequenceMap;
	}

}
