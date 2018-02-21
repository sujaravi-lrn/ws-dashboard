package com.lrn.adapter;

import com.lrn.dto.ChartSelectionDTO;
import com.lrn.dto.DashBoardHierarchyDTO;
import com.lrn.dto.DrillDownDTO;
import com.lrn.dto.request.ShowExportReportDTO;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public interface IDashboardConfigurationAdapter {

	DashBoardHierarchyDTO getHierarchySelection(Long siteId, Long dashBoardId);

	void saveHierarchySelection(Long siteId, Long dashboardId,
			String hierarchy1Value, String hierarchy1Updated,
			String hierarchy2Value, String hierarchy2Updated,
			String hierarchy3Value, String hierarchy3Updated, String userId);

	ChartSelectionDTO getChartSelection(Long siteId, Long dashboardId);

	void saveChartSelection(Long siteId, Long dashBoardId, String userId,
			String includedColumnsDisplayName, String columnsUpdated,
			String assignmentStatusSelected,
			String incompleteAndPastDueSelected,
			String courseCompletionSatusSelected,
			String courseCompletionStatusWithGroupBySelected, 
			String statusReportCompletionSatusSelected, 
			String statusReportUserProgressSelected);

	DrillDownDTO getDrillDownChartSelection(Long siteId, Long dashBoardId);

	void saveDrillDownChartSelection(Long siteId, Long dashBoardId, String userId,
			String drillDownReportSelected,
			String includedColumnListForDrillDown,
			String includedColumnDisplayNameListForDrillDown,
			String includedColumnListForDrillDownUpdated,
			
			String drillDownUserSummaryDetailsSelected,
			String includedColumnListForSummaryDetailsDrillDown,
			String includedColumnDisplayNameListForSummaryDetailsDrillDown,
			String includedColumnListForSummaryDetailsDrillDownUpdated, 
			
			String statusReportCompletionStatusDrilldownSelected, 
			String includedColumnListForStatusReportCompletionStatusDrilldown, 
			String includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown, 
			String includedColumnListForStatusReportCompletionStatusDrilldownUpdated, 
			
			String statusReportUserProgressDrilldownSelected, 
			String includedColumnListForStatusReportUserProgressDrilldown, 
			String includedColumnDisplayNameListForStatusReportUserProgressDrilldown, 
			String includedColumnListForStatusReportUserProgressDrilldownUpdated);

	ShowExportReportDTO saveShowExportReport(ShowExportReportDTO requestDTO);

	ShowExportReportDTO getShowExportReport(ShowExportReportDTO requestDTO);

}
