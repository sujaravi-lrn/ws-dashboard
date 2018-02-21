package com.lrn.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IDashboardConfigurationDao;
import com.lrn.dao.IGenericDao;
import com.lrn.dto.DashboardAndConfigurationDTO;
import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.model.DashboardConfiguration;

public class DashboardConfigurationManager extends GenericManager<DashboardConfiguration, Long>
					implements IDashboardConfigurationManager {

	private IDashboardConfigurationDao dashboardConfigurationDao;
	
	public DashboardConfigurationManager(IGenericDao<DashboardConfiguration, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setDashboardConfigurationDao(
			IDashboardConfigurationDao dashboardConfigurationDao) {
		this.dashboardConfigurationDao = dashboardConfigurationDao;
	}

	@Override
	public DashboardAndConfigurationDTO getDashboardConfigurationForSite(Long siteId) {
		return dashboardConfigurationDao.getDashboardConfigurationForSite(siteId);
	}

	@Override
	public String getSameHierachyColumn(Long dashboardId) {
		return dashboardConfigurationDao.getSameHierachyColumn(dashboardId);
	}

	@Override
	public String getSameDataHierarchyConfigForDashboard(Long dashboardId) {
		return dashboardConfigurationDao.getSameDataHierarchyConfigForDashboard(dashboardId);
	}

	@Override
	public Map<String, String> getDashboardHierarchyConfigs(Long dashboardId) {
		return dashboardConfigurationDao.getDashboardHierarchyConfigs(dashboardId);
	}

	@Override
	public Map<Long, DashboardConfiguration> getDashboardConfigurationForConfigTypes(Long dashboardId, List<Long> configTypes) {
		return dashboardConfigurationDao.getDashboardConfigurationForConfigTypes(dashboardId, configTypes);
	}
	
	@Override
	public void saveDashboardConfiguration(DashboardConfiguration dc, Long dashboardId, String userId,
				Long configTypeId, String configValue) {
		
		//check to see if the configValue is null first - if it is, then there is nothing to update, so delete the row from the DB table
		if(configValue == null && dc != null) {
			this.remove(dc.getDashboardConfigurationId());
			
		} else {
			if(dc == null) { 
				dc = new DashboardConfiguration();
				dc.setDashboardConfigurationId(-1L);
				dc.setDashboardId(dashboardId);
				dc.setConfigTypeId(configTypeId);
				dc.setCreatedBy(userId);
				dc.setCreatedOn(new Date());
			}
			dc.setConfigValue(configValue);
			dc.setUpdatedBy(userId);
			dc.setUpdatedOn(new Date());
			
			this.save(dc);
		}
	}


	@Override
	public void saveDashboardConfigurationFromDto(Long active, String userID,
			Long dashboardId, DashboardAndConfigurationDTO dashboardConfigDTO) {
		
		DashboardConfiguration dashboardConfiguration = new DashboardConfiguration();
		if(dashboardConfigDTO == null) {
			dashboardConfiguration.setDashboardConfigurationId(-1L);
			dashboardConfiguration.setDashboardId(dashboardId);
			dashboardConfiguration.setConfigTypeId(1L);
			dashboardConfiguration.setConfigValue(String.valueOf(active));
			dashboardConfiguration.setCreatedBy(userID);
			dashboardConfiguration.setCreatedOn(new Date());
			dashboardConfiguration.setUpdatedBy(userID);
			dashboardConfiguration.setUpdatedOn(new Date());
		} else {
			dashboardConfiguration.setDashboardConfigurationId(dashboardConfigDTO.getDashboardConfigurationId());
			dashboardConfiguration.setDashboardId(dashboardId);
			dashboardConfiguration.setConfigTypeId(dashboardConfigDTO.getConfigTypeId());
			dashboardConfiguration.setConfigValue(String.valueOf(active));
			dashboardConfiguration.setUpdatedBy(userID);
			dashboardConfiguration.setUpdatedOn(new Date());
		}
		
		this.save(dashboardConfiguration);
	}

	@Override
	public ShowExportReportDTO saveShowExportReport(ShowExportReportDTO requestDTO) {
		
		Long [] configTypeIdsArr = {16L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationDao
				.getDashboardConfigurationForConfigTypes(requestDTO.getDashboardId(), Arrays.asList(configTypeIdsArr));
		
		DashboardConfiguration dc = dcMap.get(16L);
		
		if(dc == null) {
			dc = new DashboardConfiguration();
			dc.setDashboardConfigurationId(-1L);
			dc.setDashboardId(requestDTO.getDashboardId());
			dc.setConfigTypeId(16L);
		}
		
		dc.setConfigValue(requestDTO.getShowExport() == 1 ? "1" : "0");
		dc.setUpdatedBy(requestDTO.getUserId());
		dc.setCreatedBy(requestDTO.getUserId());
		dc.setCreatedOn(new Date());
		dc.setUpdatedOn(new Date());

		dc = this.save(dc);
		
		return requestDTO;
	}
}
