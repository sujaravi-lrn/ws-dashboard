package com.lrn.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IDashboardSelectionCriteriaDao;
import com.lrn.dao.IGenericDao;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.model.DashboardSelectionCriteria;

public class DashboardSelectionCriteriaManager extends GenericManager<DashboardSelectionCriteria, Long>
					implements IDashboardSelectionCriteriaManager {

	IDashboardSelectionCriteriaDao dashboardSelectionCriteriaDao;
	
	public DashboardSelectionCriteriaManager(
			IGenericDao<DashboardSelectionCriteria, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setDashboardSelectionCriteriaDao(
			IDashboardSelectionCriteriaDao dashboardSelectionCriteriaDao) {
		this.dashboardSelectionCriteriaDao = dashboardSelectionCriteriaDao;
	}

	@Override
	public Map<String, Map<String, Long>> getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(
			Long dashBoardId, Long configTypeId) {
		
		return dashboardSelectionCriteriaDao.getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(dashBoardId, configTypeId);
	}
	
	@Override 
	public List<DashboardSelectionCriteria> getDashboardSelectionCriteriaByClauseName(Long dashboardId, String clauseName) {
		return dashboardSelectionCriteriaDao.getDashboardSelectionCriteriaByClauseName(dashboardId, clauseName);
	}
	
	@Override
	public List<DashboardSelectionCriteria> getDashboardSelectionCriteriaForFilters(
			Long dashboardId, String clauseName, String columnName,	Long configTypeId) {
		
		return dashboardSelectionCriteriaDao.getDashboardSelectionCriteriaForFilters(dashboardId, clauseName, columnName, configTypeId);
	}

	
	@Override
	public void saveDashboardChartSelectionCriteria(Long dashboardId,
			String userId, String colName, String colDisplayName, int sequence) {
		
		saveDashboardSelectionCriteria(dashboardId, userId, null, colName, colDisplayName, sequence, "Group By");
	}
	
	@Override
	public void saveDashboardDrillDownSelectionCriteria(Long dashboardId,
			String userId, Long configTypeId, String colName, String colDisplayName, int sequence) {
		
		saveDashboardSelectionCriteria(dashboardId, userId, configTypeId, colName, colDisplayName, sequence, "Select");
	}
	
	private void saveDashboardSelectionCriteria(Long dashboardId,
			String userId, Long configTypeId, String colName, String colDisplayName, int sequence, String clauseName) {
		
		DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
			dsc.setDashSelectionCriteriaId(-1L);
			dsc.setDashboardId(dashboardId);
			dsc.setActive(1L);
			dsc.setConfigTypeId(configTypeId);
			dsc.setColumnName(colName);
			dsc.setSelectionValue(colDisplayName);
			dsc.setSelectionSeq(String.valueOf(sequence));
			dsc.setClauseName(clauseName);
			dsc.setConditionOperator(null);
			dsc.setFilterOperator(null);
			dsc.setTypeOfValue("Static");
			dsc.setCreatedBy(userId);
			dsc.setCreatedOn(new Date());
		
		this.save(dsc);
	}

	@Override
	public void deleteByDashboardSelectionCriteriaList(List<DashboardSelectionCriteria> dcsListToDelete) {
		dashboardSelectionCriteriaDao.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
	}
}
