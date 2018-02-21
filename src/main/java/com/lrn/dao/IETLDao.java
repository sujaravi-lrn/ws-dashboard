package com.lrn.dao;

import java.util.List;

import com.lrn.dto.FirstTimeETLDTO;
import com.lrn.model.Dashboard;

public interface IETLDao {
	
	public List<FirstTimeETLDTO> getETLDeatils(Long siteId);
	public Dashboard getDashboardForSite(Long siteId);
	
	public void runETL(Long siteId);

}
