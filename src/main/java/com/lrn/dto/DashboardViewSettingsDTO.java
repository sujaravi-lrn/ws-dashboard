package com.lrn.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class DashboardViewSettingsDTO implements Serializable {

	private static final long serialVersionUID = -3628585041231601371L;

	private List<ColumnDetailsDTO>  columnDetailsDTO = new ArrayList<ColumnDetailsDTO> ();
	
	private List<AssignedProxyUsersDTO> assignedProxyUsersDTO = new ArrayList<AssignedProxyUsersDTO> ();
	
	private Long assignProxyPermission;
	
	/**
	 * @return the columnDetailsDTO
	 */
	public List<ColumnDetailsDTO> getColumnDetailsDTO() {
		return columnDetailsDTO;
	}

	/**
	 * @param columnDetailsDTO the columnDetailsDTO to set
	 */
	public void setColumnDetailsDTO(List<ColumnDetailsDTO> columnDetailsDTO) {
		this.columnDetailsDTO = columnDetailsDTO;
	}

	/**
	 * @return the assignedProxyUsersDTO
	 */
	public List<AssignedProxyUsersDTO> getAssignedProxyUsersDTO() {
		return assignedProxyUsersDTO;
	}

	/**
	 * @param assignedProxyUsersDTO the assignedProxyUsersDTO to set
	 */
	public void setAssignedProxyUsersDTO(
			List<AssignedProxyUsersDTO> assignedProxyUsersDTO) {
		this.assignedProxyUsersDTO = assignedProxyUsersDTO;
	}

	/**
	 * @return the assignProxyPermission
	 */
	public Long getAssignProxyPermission() {
		return assignProxyPermission;
	}

	/**
	 * @param assignProxyPermission the assignProxyPermission to set
	 */
	public void setAssignProxyPermission(Long assignProxyPermission) {
		this.assignProxyPermission = assignProxyPermission;
	}
	
}
