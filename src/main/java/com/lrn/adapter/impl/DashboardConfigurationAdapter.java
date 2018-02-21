package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.IDashboardConfigurationAdapter;
import com.lrn.dto.ChartSelectionDTO;
import com.lrn.dto.ColumnDetailsDTO;
import com.lrn.dto.ColumnDetailsForDrillDownDTO;
import com.lrn.dto.DashBoardHierarchyDTO;
import com.lrn.dto.DrillDownDTO;
import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.enumType.ChartSelectionColumns.ChartSelectionColumnsEnum;
import com.lrn.enumType.DrillDownColumns;
import com.lrn.manager.ICompanyUsersColumnManager;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.model.DashboardConfiguration;
import com.lrn.model.DashboardSelectionCriteria;
import com.lrn.util.StringUtil;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public class DashboardConfigurationAdapter implements IDashboardConfigurationAdapter {

	private IDashboardConfigurationManager dashboardConfigurationManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	private ICompanyUsersColumnManager companyUsersColumnManager;

	public void setDashboardConfigurationManager(
			IDashboardConfigurationManager dashboardConfigurationManager) {
		this.dashboardConfigurationManager = dashboardConfigurationManager;
	}

	public void setCompanyUsersColumnManager(
			ICompanyUsersColumnManager companyUsersColumnManager) {
		this.companyUsersColumnManager = companyUsersColumnManager;
	}

	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}

	@Override
	public DashBoardHierarchyDTO getHierarchySelection(Long siteId, Long dashboardId) {
		
		Map<String, String> companyColumnAndDisplayNamemap = companyUsersColumnManager.getCompanyColumnNameAndDisplayNameMap(siteId);
		List<String> displayNameList = new ArrayList<String> (companyColumnAndDisplayNamemap.values());
		
		Long[] configTypeIdsArr = {2L, 3L, 4L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIdsArr));

		DashBoardHierarchyDTO dashboardHierarchyDTO = new DashBoardHierarchyDTO();
			dashboardHierarchyDTO.setColumnsDisplayNameList(displayNameList);
			dashboardHierarchyDTO.setHierarchy1Value(companyColumnAndDisplayNamemap.get(dcMap.get(2L) != null ? dcMap.get(2L).getConfigValue() : null));
			dashboardHierarchyDTO.setHierarchy2Value(companyColumnAndDisplayNamemap.get(dcMap.get(3L) != null ? dcMap.get(3L).getConfigValue() : null));
			dashboardHierarchyDTO.setHierarchy3Value(companyColumnAndDisplayNamemap.get(dcMap.get(4L) != null ? dcMap.get(4L).getConfigValue() : null));
		
		return dashboardHierarchyDTO;
	}

	@Override
	public void saveHierarchySelection(Long siteId, Long dashboardId,
			String hierarchy1Value, String hierarchy1Updated,
			String hierarchy2Value, String hierarchy2Updated,
			String hierarchy3Value, String hierarchy3Updated, String userId) {

		hierarchy1Value = StringUtil.decodeRequestParameter(hierarchy1Value);
		hierarchy2Value = StringUtil.decodeRequestParameter(hierarchy2Value);
		hierarchy3Value = StringUtil.decodeRequestParameter(hierarchy3Value);

		Map<String, String> companyDisplayNameColumnNameMap = companyUsersColumnManager.getCompanyDisplayNameAndColumnNameMap(siteId);
		
		Long[] configTypeIdsArr = {2L, 3L, 4L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIdsArr));
		
		if(hierarchy1Updated.equals("1"))
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(2L), dashboardId, userId, 2L, companyDisplayNameColumnNameMap.get(hierarchy1Value));
		if(hierarchy2Updated.equals("1"))
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(3L), dashboardId, userId, 3L, companyDisplayNameColumnNameMap.get(hierarchy2Value));
		if(hierarchy3Updated.equals("1"))
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(4L), dashboardId, userId, 4L, companyDisplayNameColumnNameMap.get(hierarchy3Value));

	}
	
	@Override
	public ChartSelectionDTO getChartSelection(Long siteId, Long dashboardId) {
		
		Long[] configTypeIdsArr = {6L, 7L, 8L, 9L, 12L, 13L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIdsArr));

		ChartSelectionDTO chartSelectionDTO = new ChartSelectionDTO();
			chartSelectionDTO.setIsAssignmentStatusSelected(dcMap.get(6L) != null ? dcMap.get(6L).getConfigValue() : "0");
			chartSelectionDTO.setIsIncompleteAndPastDueSelected(dcMap.get(7L) != null ? dcMap.get(7L).getConfigValue() : "0");
			chartSelectionDTO.setIsCourseCompletionStatusSelected(dcMap.get(8L) != null ? dcMap.get(8L).getConfigValue() : "0");
			chartSelectionDTO.setIsCourseCompletionStatusWithGroupBySelected(dcMap.get(9L) != null ? dcMap.get(9L).getConfigValue() : "0");
			chartSelectionDTO.setIsStatusReportCompletionStatusSelected(dcMap.get(12L) != null ? dcMap.get(12L).getConfigValue() : "0");
			chartSelectionDTO.setIsStatusReportUserProgressSelected(dcMap.get(13L) != null ? dcMap.get(13L).getConfigValue() : "0");
			
		List<ColumnDetailsDTO> columnDetailsDTOList = getIncludedColumnsInChartSelection(siteId, dashboardId);
			chartSelectionDTO.setColumnDetailsDTOList(columnDetailsDTOList);
		
		return chartSelectionDTO;
	}
	
	@Override
	public void saveChartSelection(Long siteId, Long dashboardId,
			String userId, String includedColumnsDisplayName,
			String columnsUpdated, String assignmentStatusSelected,
			String incompleteAndPastDueSelected,
			String courseCompletionSatusSelected,
			String courseCompletionStatusWithGroupBySelected,
			String statusReportCompletionSatusSelected, 
			String statusReportUserProgressSelected) {
		
		//if columns are updated, columnsUpdated == "1", then call the update methods
		if(columnsUpdated.equals("1")) {
			//first delete all the existing selected dashboard_selection_criteria curriculumIds - cause we are going to reinsert them
			List<DashboardSelectionCriteria> dcsListToDelete = 
					dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Group By", null, null);
			dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
			
			Map<String, String> companyDisplayNameAndColumnNamemap = companyUsersColumnManager.getCompanyDisplayNameAndColumnNameMap(siteId);
			companyDisplayNameAndColumnNamemap.put(ChartSelectionColumnsEnum.CATALOG_ID.getColumnDisplayName(), ChartSelectionColumnsEnum.CATALOG_ID.getColumnName());
			companyDisplayNameAndColumnNamemap.put(ChartSelectionColumnsEnum.MODULE_TITLE.getColumnDisplayName(), ChartSelectionColumnsEnum.MODULE_TITLE.getColumnName());
			
			String[] includedColumnsDisplayNameArr = includedColumnsDisplayName.split(",");
			for(int i=0; i<includedColumnsDisplayNameArr.length; i++) {
				
				dashboardSelectionCriteriaManager.saveDashboardChartSelectionCriteria(dashboardId, userId,
						companyDisplayNameAndColumnNamemap.get(includedColumnsDisplayNameArr[i]), includedColumnsDisplayNameArr[i], i+1);
			}
		}
		
		//save the Dashboard Configurations
		Long[] configTypeArr = {6L, 7L, 8L, 9L, 12L, 13L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeArr));
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(6L), dashboardId, userId, 6L, assignmentStatusSelected);
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(7L), dashboardId, userId, 7L, incompleteAndPastDueSelected);
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(8L), dashboardId, userId, 8L, courseCompletionSatusSelected);
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(9L), dashboardId, userId, 9L, courseCompletionStatusWithGroupBySelected);
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(12L), dashboardId, userId, 12L, statusReportCompletionSatusSelected);
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(13L), dashboardId, userId, 13L, statusReportUserProgressSelected);

		//also save the drilldown dashboard configuration with default values, otherwise this will throw Null error later and can't load the reports
		Long[] configTypeIds = { 10L, 11L, 14L, 15L };
		dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIds));
		
		if(dcMap.get(10L) == null)
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(10L), dashboardId, userId, 10L, "0"); //configName = Drill_Down_Report
		if(dcMap.get(11L) == null)
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(11L), dashboardId, userId, 11L, "0"); //configName = Drill_Down_User_Summary_Detail_Report
		if(dcMap.get(14L) == null)
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(14L), dashboardId, userId, 14L, "0"); //configName = Status_Report_Completion_Status_Drill_Down
		if(dcMap.get(15L) == null)
			dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(15L), dashboardId, userId, 15L, "0"); //configName = Status_Report_User_Progress_Drill_Down
		
	}

	private List<ColumnDetailsDTO> getIncludedColumnsInChartSelection(Long siteId, Long dashboardId) {
		
		//get ALL the companyUsersColumn for the site
		Map<String, String> companyColumnAndDisplayNamemap = companyUsersColumnManager.getCompanyColumnNameAndDisplayNameMap(siteId);
		String[] inappropriateUserColumns = {"Email", "FirstName", "LastName", "MiddleName", "user_id", "Login_name"};
		
		//remove the inappropriateUserColumns from the entire Site CompanyUserColumns
		for(String col : inappropriateUserColumns) {
			if(companyColumnAndDisplayNamemap.containsKey(col))
				companyColumnAndDisplayNamemap.remove(col);
		}
		
		//add the CATALOG_ID & MODULE_TITLE to the Map
		companyColumnAndDisplayNamemap.put("BASE_CATALOG_ID", "Catalog ID");
		companyColumnAndDisplayNamemap.put("MODULE_TITLE", "Module Title");
		
		//get the included columns in the Chart Selection
		List<DashboardSelectionCriteria> includedColsInChartSelection = dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Group By", null, null);
		
		List<ColumnDetailsDTO> includedColList = new ArrayList<ColumnDetailsDTO> ();
		for(String colName : companyColumnAndDisplayNamemap.keySet()) {
	
			ColumnDetailsDTO includedCol = new ColumnDetailsDTO();
				includedCol.setColumnName(colName);
				includedCol.setColumnDisplayName(companyColumnAndDisplayNamemap.get(colName));
				includedCol.setIsIncluded("0"); //default to "0"
				
				//get the selectedSequenceNumber from the already existing includedColsInChartSelection
				//also set the included flag true or false 
				DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
					dsc.setColumnName(colName);
				if(includedColsInChartSelection.contains(dsc)) {
					dsc = includedColsInChartSelection.get(includedColsInChartSelection.indexOf(dsc));
					includedCol.setSequenceNumber(new Long(dsc.getSelectionSeq()));
					includedCol.setIsIncluded("1");
				}
			includedColList.add(includedCol);
		}
		
		return includedColList;
	}

	@Override
	public DrillDownDTO getDrillDownChartSelection(Long siteId, Long dashboardId) {
		
		Long[] configTypeIds = { 10L, 11L, 14L, 15L };
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIds));
		
		//get the drill down report columns and selected columns  -- Drill_Down_Report
		//get all the companyUsers column for 
		Map<String, String> companyUsersColumnNameDisplayNameMap = companyUsersColumnManager.getCompanyColumnNameAndDisplayNameMap(siteId);
		List<ColumnDetailsForDrillDownDTO> drillDownReportColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 10L, "User Columns");
		
		Map<String, String> drillDownColumnMap = getDrillDownColumnMap();
		List<ColumnDetailsForDrillDownDTO> drillDownModuleReportColumnList = getDrillDownReportColumnList(drillDownColumnMap, dashboardId, 10L, "Module Report Columns");
		
		//add the moduleReport COlumns to the companyUsers columns
		drillDownReportColumnList.addAll(drillDownModuleReportColumnList);
		
		//get the drill down report columns and selected columns  -- Drill_Down_User_Summary_Detail_Report
		List<ColumnDetailsForDrillDownDTO> drillDownUserSummaryDetailReportColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 11L, "User Columns");
		
		Map<String, String> drillDownUserSummaryDetailColumnMap = getDrillDownUserSummaryDetailColumnMap();
		List<ColumnDetailsForDrillDownDTO> drillDownModuleUserSummaryDetailReportColumnList = getDrillDownReportColumnList(drillDownUserSummaryDetailColumnMap, dashboardId, 11L, "Module Report Columns");
		
		//add the moduleReport COlumns to the companyUsers columns
		drillDownUserSummaryDetailReportColumnList.addAll(drillDownModuleUserSummaryDetailReportColumnList);
		
		
		
		//get the drill down report columns and selected columns  -- Status_Report_Completion_Status_Drill_Down
		//get all the companyUsers column for 
		List<ColumnDetailsForDrillDownDTO> statusReportCompletionStatusDrilldownUserColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 14L, "User Columns");
		
		Map<String, String> statusReportCompletionStatusDrilldownColumnMap = getDrillDownColumnMap();
		List<ColumnDetailsForDrillDownDTO> statusReportCompletionStatusDrilldownModuleColumnList = getDrillDownReportColumnList(statusReportCompletionStatusDrilldownColumnMap, dashboardId, 14L, "Module Report Columns");
		
		//add the moduleReport COlumns to the companyUsers columns
		statusReportCompletionStatusDrilldownUserColumnList.addAll(statusReportCompletionStatusDrilldownModuleColumnList);

		
		//get the drill down report columns and selected columns  -- Status_Report_User_Progress_Drill_Down
		//get all the companyUsers column for 
		List<ColumnDetailsForDrillDownDTO> statusReportUserProgressDrilldownUserColumnList = getDrillDownReportColumnList(companyUsersColumnNameDisplayNameMap, dashboardId, 15L, "User Columns");
		
		Map<String, String> statusReportUserProgressDrilldownColumnMap = getDrillDownColumnMap();
		List<ColumnDetailsForDrillDownDTO> statusReportUserProgressDrilldownModuleColumnList = getDrillDownReportColumnList(statusReportUserProgressDrilldownColumnMap, dashboardId, 15L, "Module Report Columns");
		
		//add the moduleReport COlumns to the companyUsers columns
		statusReportUserProgressDrilldownUserColumnList.addAll(statusReportUserProgressDrilldownModuleColumnList);

		DrillDownDTO drillDownDTO = new DrillDownDTO();	
			drillDownDTO.setIsDrillDownReportSelected(dcMap.get(10L) != null ? dcMap.get(10L).getConfigValue() : "0");
			drillDownDTO.setIsDrillDownUserSummaryDetailsSelected(dcMap.get(11L) != null ? dcMap.get(11L).getConfigValue() : "0");
			drillDownDTO.setIsStatusReportCompletionStatusDrilldownSelected(dcMap.get(14L) != null ? dcMap.get(14L).getConfigValue() : "0");
			drillDownDTO.setIsStatusReportUserProgressDrilldownSelected(dcMap.get(15L) != null ? dcMap.get(15L).getConfigValue() : "0");
			
			drillDownDTO.setColumnDetailsForDrillDownDTOForDetailsList(drillDownReportColumnList);
			drillDownDTO.setColumnDetailsForDrillDownDTOForSummaryList(drillDownUserSummaryDetailReportColumnList);
			drillDownDTO.setColumnDetailsForStatusReportCompletionStatusDrilldownList(statusReportCompletionStatusDrilldownUserColumnList);
			drillDownDTO.setColumnDetailsForStatusReportUserProgressDrilldownList(statusReportUserProgressDrilldownUserColumnList);
		return drillDownDTO;
	}

	private Map<String, String> getDrillDownUserSummaryDetailColumnMap() {
		Map<String, String> drillDownUserSummaryDetailColumnMap = new HashMap<String, String> ();
		for(DrillDownColumns.DrillDownSummaryColumnsEnum drillDownUserSummaryDetailCol: DrillDownColumns.DrillDownSummaryColumnsEnum.values()) {
			drillDownUserSummaryDetailColumnMap.put(drillDownUserSummaryDetailCol.getColumnName(), drillDownUserSummaryDetailCol.getColumnDisplayName());
		}
		return drillDownUserSummaryDetailColumnMap;
	}

	private Map<String, String> getDrillDownColumnMap() {
		Map<String, String> drillDownColumnMap = new HashMap<String, String> ();
		for(DrillDownColumns.DrillDownDetailsColumnsEnum drillDownCol: DrillDownColumns.DrillDownDetailsColumnsEnum.values()) {
			drillDownColumnMap.put(drillDownCol.getColumnName(), drillDownCol.getColumnDisplayName());
		}
		return drillDownColumnMap;
	}

	private List<ColumnDetailsForDrillDownDTO> getDrillDownReportColumnList(
			Map<String, String> companyColumnNameDisplayNameMap, Long dashboardId, long configTypeId, String columnType) {

		//get all the included columns from dashboard_selection_criteria
		List<DashboardSelectionCriteria> includedDscList = dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, null, null, configTypeId);
		
		List<ColumnDetailsForDrillDownDTO> drillDownReportColumnList = new ArrayList<ColumnDetailsForDrillDownDTO>();
		for(String colName : companyColumnNameDisplayNameMap.keySet()) {
			ColumnDetailsForDrillDownDTO columnDetailsForDrillDownDTO = new ColumnDetailsForDrillDownDTO();
			columnDetailsForDrillDownDTO.setColumnName(colName);
			columnDetailsForDrillDownDTO.setColumnDisplayName(companyColumnNameDisplayNameMap.get(colName));
			columnDetailsForDrillDownDTO.setSection(columnType);
			columnDetailsForDrillDownDTO.setIsIncluded("0"); //default to "0"
			
			//get the selectedSequenceNumber from the already existing includedColsInChartSelection
			//also set the included flag true or false 
			DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
				dsc.setColumnName(colName);
			if(includedDscList.contains(dsc)) {
				dsc = includedDscList.get(includedDscList.indexOf(dsc));
				columnDetailsForDrillDownDTO.setSequenceNumber(new Long(dsc.getSelectionSeq()));
				columnDetailsForDrillDownDTO.setIsIncluded("1");
			}
			drillDownReportColumnList.add(columnDetailsForDrillDownDTO);
		}
		
		return drillDownReportColumnList;
	}

	@Override
	public void saveDrillDownChartSelection(Long siteId, Long dashboardId, String userId,
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
			String includedColumnListForStatusReportUserProgressDrilldownUpdated) {

		//fix the @@@/$$$(/ and #) in the updated columns
		includedColumnDisplayNameListForDrillDown = StringUtil.decodeRequestParameter(includedColumnDisplayNameListForDrillDown);
		includedColumnDisplayNameListForSummaryDetailsDrillDown = StringUtil.decodeRequestParameter(includedColumnDisplayNameListForSummaryDetailsDrillDown);
		includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown = StringUtil.decodeRequestParameter(includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown);
		includedColumnDisplayNameListForStatusReportUserProgressDrilldown = StringUtil.decodeRequestParameter(includedColumnDisplayNameListForStatusReportUserProgressDrilldown);
		
		//first update the dashboardConfiguration with the chart selection checkbox values
		Long[] configTypeIds = { 10L, 11L, 14L, 15L };
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIds));
		
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(10L), dashboardId, userId, 10L, drillDownReportSelected); //configName = Drill_Down_Report
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(11L), dashboardId, userId, 11L, drillDownUserSummaryDetailsSelected); //configName = Drill_Down_User_Summary_Detail_Report
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(14L), dashboardId, userId, 14L, statusReportCompletionStatusDrilldownSelected); //configName = Status_Report_Completion_Status_Drill_Down
		dashboardConfigurationManager.saveDashboardConfiguration(dcMap.get(15L), dashboardId, userId, 15L, statusReportUserProgressDrilldownSelected); //configName = Status_Report_User_Progress_Drill_Down
		
		if(drillDownReportSelected.equals("1") && includedColumnListForDrillDownUpdated.equals("1")) { //configName = Drill_Down_Report
			
			//first delete all the existing dashboardSelectionCriteria for configTypeId = 10
			List<DashboardSelectionCriteria> dcsListToDelete = 
					dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Select", null, 10L);
			dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
			
			//then reinsert the newly selected columns
			insertDrillDownSelectedColumns(dashboardId, userId,
					includedColumnListForDrillDown,
					includedColumnDisplayNameListForDrillDown, 10L);
		}
		
		if(drillDownUserSummaryDetailsSelected.equals("1") && includedColumnListForSummaryDetailsDrillDownUpdated.equals("1")) { //configName = Drill_Down_User_Summary_Detail_Report
			
			//first delete all the existing dashboardSelectionCriteria for configTypeId = 10
			List<DashboardSelectionCriteria> dcsListToDelete = 
					dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Select", null, 11L);
			dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
			
			//then reinsert the newly selected columns
			insertDrillDownSelectedColumns(dashboardId, userId,
					includedColumnListForSummaryDetailsDrillDown,
					includedColumnDisplayNameListForSummaryDetailsDrillDown, 11L);
		}
		
		if(statusReportCompletionStatusDrilldownSelected.equals("1") && includedColumnListForStatusReportCompletionStatusDrilldownUpdated.equals("1")) { //configName = Status_Report_Completion_Status_Drill_Down
					
					//first delete all the existing dashboardSelectionCriteria for configTypeId = 14
					List<DashboardSelectionCriteria> dcsListToDelete = 
							dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Select", null, 14L);
					dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
					
					//then reinsert the newly selected columns
					insertDrillDownSelectedColumns(dashboardId, userId,
							includedColumnListForStatusReportCompletionStatusDrilldown,
							includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown, 14L);
				}
		
		if(statusReportUserProgressDrilldownSelected.equals("1") && includedColumnListForStatusReportUserProgressDrilldownUpdated.equals("1")) { //configName = Status_Report_User_Progress_Drill_Down
			
			//first delete all the existing dashboardSelectionCriteria for configTypeId = 15
			List<DashboardSelectionCriteria> dcsListToDelete = 
					dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaForFilters(dashboardId, "Select", null, 15L);
			dashboardSelectionCriteriaManager.deleteByDashboardSelectionCriteriaList(dcsListToDelete);
			
			//then reinsert the newly selected columns
			insertDrillDownSelectedColumns(dashboardId, userId,
					includedColumnListForStatusReportUserProgressDrilldown,
					includedColumnDisplayNameListForStatusReportUserProgressDrilldown, 15L);
		}
	}

	private void insertDrillDownSelectedColumns(Long dashboardId,
			String userId, String includedColumnListForDrillDown,
			String includedColumnDisplayNameListForDrillDown, Long configTypeId) {
		
		String[] includedColumnArr = includedColumnListForDrillDown.split(",");
		String[] includedColumnDisplayNameArr = includedColumnDisplayNameListForDrillDown.split(",");
		
		for(int i=0; i<includedColumnDisplayNameArr.length; i++) {
			dashboardSelectionCriteriaManager.saveDashboardDrillDownSelectionCriteria(dashboardId, userId,
					configTypeId, includedColumnArr[i], includedColumnDisplayNameArr[i], i+1);
		}
	}

	@Override
	public ShowExportReportDTO saveShowExportReport(ShowExportReportDTO requestDTO) {
		
		return dashboardConfigurationManager.saveShowExportReport(requestDTO);
	}

	@Override
	public ShowExportReportDTO getShowExportReport(ShowExportReportDTO requestDTO) {
		
		Long [] configTypeIdsArr = {16L};
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager
				.getDashboardConfigurationForConfigTypes(requestDTO.getDashboardId(), Arrays.asList(configTypeIdsArr));
		
		DashboardConfiguration dc = dcMap.get(16L);
		
		if(dc != null) {
			requestDTO.setShowExport(dc.getConfigValue().equals("1") ? 1L : 0L);
		}
		
		return requestDTO;
	}
}
