package com.lrn.manager;

import java.util.List;
import java.util.Map;

import com.lrn.dto.DashboardAndConfigurationDTO;
import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.model.DashboardConfiguration;

public interface IDashboardConfigurationManager extends IGenericManager<DashboardConfiguration, Long> {

	String getSameHierachyColumn(Long dashboardId);

	String getSameDataHierarchyConfigForDashboard(Long dashboardId);

	Map<String, String> getDashboardHierarchyConfigs(Long dashboardId);
	
	Map<Long, DashboardConfiguration> getDashboardConfigurationForConfigTypes(Long dashboardId, List<Long> configTypes);

	DashboardAndConfigurationDTO getDashboardConfigurationForSite(Long siteId);

	void saveDashboardConfiguration(DashboardConfiguration dc,
			Long dashboardId, String userId, Long configTypeId, String configValue);

	void saveDashboardConfigurationFromDto(Long active, String userID,
			Long dashboardId, DashboardAndConfigurationDTO dashboardConfigDTO);

	ShowExportReportDTO saveShowExportReport(ShowExportReportDTO requestDTO);
}
