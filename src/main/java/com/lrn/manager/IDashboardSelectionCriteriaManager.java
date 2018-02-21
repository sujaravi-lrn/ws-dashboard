package com.lrn.manager;

import java.util.List;
import java.util.Map;

import com.lrn.model.DashboardSelectionCriteria;

public interface IDashboardSelectionCriteriaManager extends IGenericManager<DashboardSelectionCriteria, Long> {

	List<DashboardSelectionCriteria> getDashboardSelectionCriteriaByClauseName(Long dashboardId, String clauseName);

	List<DashboardSelectionCriteria> getDashboardSelectionCriteriaForFilters(
			Long dashboardId, String clauseName, String columnName,	Long configTypeId);

	Map<String, Map<String, Long>> getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(
			Long dashBoardId, Long configTypeId);

	void saveDashboardChartSelectionCriteria(Long dashboardId, String userId,
			String colName, String colDisplayName, int sequence);

	void saveDashboardDrillDownSelectionCriteria(Long dashboardId,
			String userId, Long configTypeId, String colName,
			String colDisplayName, int sequence);

	void deleteByDashboardSelectionCriteriaList(List<DashboardSelectionCriteria> dcsListToDelete);

}
