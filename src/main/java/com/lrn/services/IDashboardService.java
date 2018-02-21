package com.lrn.services;

import com.lrn.model.LRNResponse;


public interface IDashboardService {

	public LRNResponse saveDashboardConfiguration(Long siteId,Long active,String userID,Long dashBoardId);
	
	public LRNResponse getDashboardConfigurationForSite(Long siteId);
	
	public LRNResponse saveDashboard(Long siteId,String userId,Long active);

	public LRNResponse getDashboardForSite(Long siteId);

	public LRNResponse getDashboardConfigsForCharts(Long siteId);

	public LRNResponse getGroupByDashboardSelectionCriteria(Long dashboardId);
	
	public LRNResponse getDashboardById(Long dashboardId);

	public LRNResponse getDashboardViewSettings(Long dashBoardId, String userId);

	public LRNResponse getLastUpdatedDate(Long siteId);
	
}
