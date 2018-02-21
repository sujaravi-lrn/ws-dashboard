package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.ColumnDetailsDTO;
import com.lrn.dto.DashBoardViewConfigDTO;
import com.lrn.dto.DashboardViewSettingsDTO;
import com.lrn.dto.GetDashboardDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.model.Dashboard;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public interface IDashboardAdapter {
	
	public Long saveDashboard(Long siteId, String userId, Long active);

	public GetDashboardDTO getDashboardConfigurationForSite(Long siteId);

	public GetDashboardDTO getDashboardForSite(Long siteId);

	public void saveDashboardConfiguration(Long siteId, Long active, String userID, Long dashBoardId);

	public DashBoardViewConfigDTO getDashboardConfigsForCharts(Long siteId);

	public List<ColumnDetailsDTO> getGroupByDashboardSelectionCriteria(Long dashboardId);

	public Dashboard getDashboardById(Long dashboardId);

	public DashboardViewSettingsDTO getDashboardViewSettings(Long dashboardId, String userId);

	public DashboardUpdateDateDTO getLastUpdatedDate(Long siteId);
}
