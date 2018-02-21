package com.lrn.services;

import com.lrn.model.LRNResponse;
public interface ICurriculumService {
	
	LRNResponse getDashboardCampaignSelection(Long siteId, Long dashboardId);
	
	LRNResponse saveDashboardCampaignSelection(Long siteId, Long dashboardId, String curriculumIds, String userId);

}
