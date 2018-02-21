/**
 * 
 */
package com.lrn.dao;

import java.util.List;

import com.lrn.dto.DisplayDimCurriculumDTO;
import com.lrn.model.DimCurriculum;


public interface IDimCurriculumDao extends IGenericDao<DimCurriculum, Long> {
	
	List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId);
}
