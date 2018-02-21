package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.DisplayDimCurriculumDTO;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public interface ICurriculumAdapter {

	List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId);

	void saveDashboardCampaignSelection(Long siteId, Long dashBoardId,
			String curriculumIds, String userId);

}
