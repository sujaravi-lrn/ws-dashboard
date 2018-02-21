package com.lrn.manager;

import java.util.List;

import com.lrn.dto.DisplayDimCurriculumDTO;
import com.lrn.model.DimCurriculum;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public interface IDimCurriculumManager extends IGenericManager<DimCurriculum, Long> {

	List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId);

}
