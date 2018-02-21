package com.lrn.dao;

import java.util.List;
import java.util.Map;

import com.lrn.dto.DashboardAndConfigurationDTO;
import com.lrn.model.DashboardConfiguration;

public interface IDashboardConfigurationDao extends IGenericDao<DashboardConfiguration, Long> {

	String getSameHierachyColumn(Long dashboardId);

	String getSameDataHierarchyConfigForDashboard(Long dashboardId);

	Map<String, String> getDashboardHierarchyConfigs(Long dashboardId);

	Map<Long, DashboardConfiguration> getDashboardConfigurationForConfigTypes(Long dashboardId, List<Long> configTypes);

	DashboardAndConfigurationDTO getDashboardConfigurationForSite(Long siteId);

}