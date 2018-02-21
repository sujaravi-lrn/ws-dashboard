package com.lrn.adapter.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.lrn.adapter.ICurriculumAdapter;
import com.lrn.dto.DisplayDimCurriculumDTO;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IDimCurriculumManager;
import com.lrn.model.DashboardSelectionCriteria;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public class CurriculumAdapter implements ICurriculumAdapter {
	
	private IDimCurriculumManager dimCurriculumManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	
	public void setDimCurriculumManager(IDimCurriculumManager dimCurriculumManager) {
		this.dimCurriculumManager = dimCurriculumManager;
	}

	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}

	@Override
	public List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId) {
		return dimCurriculumManager.getDashboardCampaignSelection(siteId, dashboardId);
	}

	@Override
	public void saveDashboardCampaignSelection(Long siteId,
			Long dashboardId, String curriculumIds, String userId) {

		//first delete all the existing selected dashboard_selection_criteria curriculumIds - cause we are going to reinsert them
		List<DashboardSelectionCriteria> dcsListToDelete = 
				dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "where", "CURRICULUM_ID", null);
		dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
		
		List<String> curriculumIdList = Arrays.asList(curriculumIds.split(","));
		
		int seq = 1;
		//insert all the new curriculumIds
		for(String curriculumId : curriculumIdList) {
			DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
				dsc.setDashSelectionCriteriaId(-1L);
				dsc.setDashboardId(dashboardId);
				dsc.setSelectionValue(curriculumId);
				dsc.setSelectionSeq(String.valueOf(seq++));
				dsc.setActive(1L);
				dsc.setClauseName("where");
				dsc.setColumnName("CURRICULUM_ID");
				dsc.setFilterOperator("AND");
				dsc.setConditionOperator("IN");
				dsc.setTypeOfValue("Static");
				dsc.setCreatedBy(userId);
				dsc.setCreatedOn(new Date());
				
			dashboardSelectionCriteriaManager.save(dsc);
		}		
		
	}
}
