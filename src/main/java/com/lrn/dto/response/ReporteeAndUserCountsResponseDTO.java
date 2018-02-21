package com.lrn.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReporteeAndUserCountsResponseDTO implements Serializable {

	private static final long serialVersionUID = -2124815923953176508L;
	
//	private Long totalRecords;

	List<ReporteeAndUserCountsData> data = new ArrayList<ReporteeAndUserCountsData> ();

	public List<ReporteeAndUserCountsData> getData() {
		return data;
	}
	public void setData(List<ReporteeAndUserCountsData> data) {
		this.data = data;
	}
	
}
