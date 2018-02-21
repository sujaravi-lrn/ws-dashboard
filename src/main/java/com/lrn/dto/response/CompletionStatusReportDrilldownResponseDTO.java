package com.lrn.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CompletionStatusReportDrilldownResponseDTO implements Serializable {

	private static final long serialVersionUID = 5568153800508671748L;

	private Long totalRecords;
	
	private List<Map<String, Object>> data;

	private Map<String, Map<String, Long>> columnNameColumnDisplayNameSequenceMap;

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

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

	public Map<String, Map<String, Long>> getColumnNameColumnDisplayNameSequenceMap() {
		return columnNameColumnDisplayNameSequenceMap;
	}

	public void setColumnNameColumnDisplayNameSequenceMap(
			Map<String, Map<String, Long>> columnNameColumnDisplayNameSequenceMap) {
		this.columnNameColumnDisplayNameSequenceMap = columnNameColumnDisplayNameSequenceMap;
	}
}
