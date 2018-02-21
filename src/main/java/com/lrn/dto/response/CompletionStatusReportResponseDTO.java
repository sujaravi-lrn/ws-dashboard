package com.lrn.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lrn.dto.GetCourseAssignmentStatusManagerDTO;

public class CompletionStatusReportResponseDTO implements Serializable {

	private static final long serialVersionUID = 8881761426569121117L;
	
	private Long totalRecords;
	
	List<GetCourseAssignmentStatusManagerDTO> data = new ArrayList<GetCourseAssignmentStatusManagerDTO> ();

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<GetCourseAssignmentStatusManagerDTO> getData() {
		return data;
	}

	public void setData(List<GetCourseAssignmentStatusManagerDTO> data) {
		this.data = data;
	}
	
}
