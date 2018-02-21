package com.lrn.services;

import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.model.LRNResponse;

public interface IDashboardConfigurationService {

	LRNResponse getHierarchySelection(Long siteId, Long dashBoardId);

	LRNResponse saveHierarchySelection(Long siteId, Long dashboardId,
			String hierarchy1Value, String hierarchy1Updated,
			String hierarchy2Value, String hierarchy2Updated,
			String hierarchy3Value, String hierarchy3Updated, String userId);

	LRNResponse getChartSelection(Long siteId, Long dashboardId);

	LRNResponse saveChartSelection(Long siteId, Long dashBoardId,
			String userId, String includedColumnsDisplayName,
			String columnsUpdated,String assignmentStatusSelected,
			String incompleteAndPastDueSelected,
			String courseCompletionSatusSelected,
			String courseCompletionStatusWithGroupBySelected, 
			String statusReportCompletionSatusSelected, 
			String statusReportUserProgressSelected);

	LRNResponse getDrillDownChartSelection(Long siteId, Long dashBoardId);

	LRNResponse saveDrillDownChartSelection(Long siteId, Long dashBoardId, String userId,
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

	LRNResponse saveShowExportReport(ShowExportReportDTO requestDTO);

	LRNResponse getShowExportReport(ShowExportReportDTO requestDTO);

}
