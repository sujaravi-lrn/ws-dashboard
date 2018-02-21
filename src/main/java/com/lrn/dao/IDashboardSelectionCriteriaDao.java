/**
 * 
 */
package com.lrn.dao;

import java.util.List;
import java.util.Map;

import com.lrn.model.DashboardSelectionCriteria;

public interface IDashboardSelectionCriteriaDao extends IGenericDao<DashboardSelectionCriteria, Long> {

	List<DashboardSelectionCriteria> getDashboardSelectionCriteriaByClauseName(Long dashboardId, String clauseName);

	List<DashboardSelectionCriteria> getDashboardSelectionCriteriaForFilters(
			Long dashboardId, String clauseName, String columnName,	Long configTypeId);

	Map<String, Map<String, Long>> getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(
			Long dashBoardId, Long configTypeId);

	void deleteByDashboardSelectionCriteriaList(List<DashboardSelectionCriteria> dcsListToDelete);
}
