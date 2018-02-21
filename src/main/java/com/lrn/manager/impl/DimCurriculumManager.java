package com.lrn.manager.impl;

import java.util.List;

import com.lrn.dao.IDimCurriculumDao;
import com.lrn.dao.IGenericDao;
import com.lrn.dto.DisplayDimCurriculumDTO;
import com.lrn.manager.IDimCurriculumManager;
import com.lrn.model.DimCurriculum;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public class DimCurriculumManager extends GenericManager<DimCurriculum, Long> implements IDimCurriculumManager {

	private IDimCurriculumDao dimCurriculumDao;

	public DimCurriculumManager(IGenericDao<DimCurriculum, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setDimCurriculumDao(IDimCurriculumDao dimCurriculumDao) {
		this.dimCurriculumDao = dimCurriculumDao;
	}
	
	@Override
	public List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId) {
		return dimCurriculumDao.getDashboardCampaignSelection(siteId, dashboardId);
	}
}
