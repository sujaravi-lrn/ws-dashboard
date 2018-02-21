package com.lrn.rest.controller;

import javax.ws.rs.Consumes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.dto.request.ShowExportReportDTO;
import com.lrn.model.Dashboard;
import com.lrn.model.LRNResponse;
import com.lrn.services.ICourseService;
import com.lrn.services.ICurriculumService;
import com.lrn.services.IDashboardConfigurationService;
import com.lrn.services.IDashboardService;
import com.lrn.services.IETLService;
import com.lrn.services.IProxyService;
import com.lrn.services.IUserAssignmentService;
import com.lrn.services.IUserService;
import com.lrn.util.StringUtil;

@Controller
@RequestMapping("/api")
public class DashboardRESTfulController {
	
	private static final Logger logger = Logger.getLogger(DashboardRESTfulController.class);
	
	private LRNResponse lrnResponseForPerformance=null;

	@Autowired
	private IUserService userService;
	@Autowired
	private IDashboardService dashboardService;
	@Autowired
	private IProxyService proxyService;
	@Autowired
	private IDashboardConfigurationService dashboardConfigurationService;
	@Autowired
	private ICurriculumService curriculumService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IUserAssignmentService userAssignmentService;
	@Autowired
	private IETLService eTLService;

	public void setCourseAssignmentService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public void setUserAssignmentService(IUserAssignmentService userAssignmentService) {
		this.userAssignmentService = userAssignmentService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setDashboardService(IDashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	public void setDashboardConfigurationService(IDashboardConfigurationService dashboardConfigurationService) {
		this.dashboardConfigurationService = dashboardConfigurationService;
	}

	public void setCurriculumService(ICurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}

	/*Added method for ETL*/

	@RequestMapping(value = "/getETLDetails/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getETLDetails(
			@PathVariable(value = "siteId") Long siteId) {

		return eTLService.getETLDetailsBySiteId(siteId);
	}

	@RequestMapping(value = "/runETL/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse runETL(
			@PathVariable(value = "siteId") Long siteId) {
		return eTLService.runETL(siteId);

	}

	/******************** RE-WRITTEN services **********************/
	
	@RequestMapping(value = "/getCourseAssignmentStatusGroupByForManager/dashBoardId={dashBoardId}/managerId={managerId}/groupByColumnName={groupByColumnName}/hasDashBoardConfig={hasDashBoardConfig}/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCourseAssignmentStatusGroupByForManager(

			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "managerId") String managerId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig,
			@PathVariable(value = "siteId") Long siteId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCourseAssignmentStatusGroupByForManager");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/managerId=").append(managerId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append("/siteId=").append(siteId);
			buf.append(".........");
		logger.debug(buf);
		
		lrnResponseForPerformance = courseService.get1stChartCourseCompletionStatus(dashBoardId, managerId, groupByColumnName, hasDashBoardConfig, siteId);
		
		return lrnResponseForPerformance;
	}
	

	@RequestMapping(value = "/getCourseAssignmentDetailsForManager/dashBoardId={dashBoardId}/siteId={siteId}/managerId={managerId}/groupByColumnName={groupByColumnName}/groupByColumnValue={groupByColumnValue}/completionStatus={completionStatus}/hasDashBoardConfig={hasDashBoardConfig}/filteredColumn={filteredColumn}/filteredColumnValue={filteredColumnValue}/isExport={isExport}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCourseAssignmentDetailsForManager(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "managerId") String managerId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "groupByColumnValue") String groupByColumnValue,
			@PathVariable(value = "completionStatus") String completionStatus,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig,
			@PathVariable(value = "filteredColumn") String filteredColumn,
			@PathVariable(value = "filteredColumnValue") String filteredColumnValue,
			@PathVariable(value = "isExport") Long isExport) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCourseAssignmentDetailsForManager");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/siteId=").append(siteId);
			buf.append("/managerId=").append(managerId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/groupByColumnValue=").append(groupByColumnValue);
			buf.append("/completionStatus=").append(completionStatus);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append("/filteredColumn=").append(filteredColumn);
			buf.append("/filteredColumnValue=").append(filteredColumnValue);
			buf.append("/isExport=").append(isExport);
			buf.append(".........");
		logger.debug(buf);

//		String completionStatusAvailable = ModuleCourseCompletionStatusMap.getValue(completionStatus);
		
		//front end calls are designed in a way that 1st chart and 4th chart are called EXACTLY the same way, by previous developers.. 
		//so, I have to work around this BAD design for now
		//For 1st chart, the completionStatus values are complete_ON_TIME, complete_PAST_DUE, incomplete_NOT_DUE_YET, incomplete_PAST_DUE
		//changing the above logic for differentiation - call the 4thChart if groupByColumnName=isNullValue & groupByColumnValue=isNullValue
		if(StringUtil.isDashboardGroupByColumnValueNull(groupByColumnName)) {
			//for 4th chart, the courseStatus values are complete_ON_TIME, complete_PAST_DUE, complete_NO_DUE_DATE, complete_CREDITED_ON_TIME, complete_CREDITED_PAST_DUE,
			//complete_CREDITED_NO_DUE_DATE, in_PROCESS_PAST_DUE, in_PROCESS_NO_DUE_DATE, in_PROCESS_NOT_DUE_YET, not_STARTED_PAST_DUE, not_STARTED_NO_DUE_DATE, not_STARTED_NOT_DUE_YET
			lrnResponseForPerformance = courseService
					.get4thChartCourseStatusDetailsDrilldown(dashBoardId, siteId, managerId, groupByColumnName,
					groupByColumnValue, completionStatus, hasDashBoardConfig,filteredColumn,filteredColumnValue, isExport);
		} else {
		
			lrnResponseForPerformance = courseService
					.get1stChartCourseCompletionStatusDrilldown(dashBoardId, siteId, managerId, groupByColumnName,
					groupByColumnValue, completionStatus, hasDashBoardConfig,filteredColumn,filteredColumnValue, isExport);
		}
		
		return lrnResponseForPerformance;
	}

	
	@RequestMapping(value = "/getCourseAssignmentDetailsForPastDue/dashBoardId={dashBoardId}/userId={userId}/groupByColumnName={groupByColumnName}/hasDashBoardConfig={hasDashBoardConfig}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCourseAssignmentDetailsForPastDue(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCourseAssignmentDetailsForPastDue");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/userId=").append(userId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append(".........");
		logger.debug(buf);
	
		Dashboard dashboard = (Dashboard) dashboardService.getDashboardById(dashBoardId).getDataObject();
		lrnResponseForPerformance = courseService.get2ndChartIncompleteAndPastDueCourses(dashBoardId, 
				dashboard.getSiteId(), userId, groupByColumnName, hasDashBoardConfig);
		
		return lrnResponseForPerformance;
	}
	

	@RequestMapping(value = "/getCourseAssignmentDetailsForManagerWithPastDue/dashBoardId={dashBoardId}/siteId={siteId}/managerId={managerId}/groupByColumnName={groupByColumnName}/groupByColumnValue={groupByColumnValue}/completionStatus={completionStatus}/hasDashBoardConfig={hasDashBoardConfig}/filteredColumn={filteredColumn}/filteredColumnValue={filteredColumnValue}/isExport={isExport}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCourseAssignmentDetailsForManagerWithPastDue(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "managerId") String managerId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "groupByColumnValue") String groupByColumnValue,
			@PathVariable(value = "completionStatus") String completionStatus,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig,
			@PathVariable(value = "filteredColumn") String filteredColumn,
			@PathVariable(value = "filteredColumnValue") String filteredColumnValue,
			@PathVariable(value = "isExport") Long isExport) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCourseAssignmentDetailsForManagerWithPastDue");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/siteId=").append(siteId);
			buf.append("/managerId=").append(managerId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/groupByColumnValue=").append(groupByColumnValue);
			buf.append("/completionStatus=").append(completionStatus);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append("/filteredColumn=").append(filteredColumn);
			buf.append("/filteredColumnValue=").append(filteredColumnValue);
			buf.append("/isExport=").append(isExport);
			buf.append(".........");
		logger.debug(buf);
	
		lrnResponseForPerformance = courseService
				.get2ndChartIncompleteAndPastDueCoursesDrilldown(dashBoardId,
						siteId, managerId, groupByColumnName,
						groupByColumnValue, completionStatus,
						hasDashBoardConfig,filteredColumn,filteredColumnValue, isExport);
		
		return lrnResponseForPerformance;
	}
	

	@RequestMapping(value = "/getUserAssignmentDetailsForManagerByAssignment/dashBoardId={dashBoardId}/userId={userId}/groupByColumnName={groupByColumnName}/hasDashBoardConfig={hasDashBoardConfig}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getUserAssignmentDetailsForManagerByAssignment(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashboardConfig) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getUserAssignmentDetailsForManagerByAssignment");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/userId=").append(userId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/hasDashBoardConfig=").append(hasDashboardConfig);
			buf.append(".........");
		logger.debug(buf);
	
		Dashboard dashboard = (Dashboard) dashboardService.getDashboardById(dashBoardId).getDataObject();
		lrnResponseForPerformance = userAssignmentService.get3rdChartUserCompletionStatus(
				dashBoardId, dashboard.getSiteId(), userId, groupByColumnName, hasDashboardConfig);
		
		return lrnResponseForPerformance;
	}
	
	@RequestMapping(value = "/getUserAssignmentDetailsForManager/dashBoardId={dashBoardId}/managerId={managerId}/groupByColumnName={groupByColumnName}/groupByColumnValue={groupByColumnValue}/completionStatus={completionStatus}/hasDashBoardConfig={hasDashBoardConfig}/siteId={siteId}/filteredColumn={filteredColumn}/filteredColumnValue={filteredColumnValue}/isExport={isExport}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getUserAssignmentDetailsForManager(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "managerId") String managerId,
			@PathVariable(value = "groupByColumnName") String groupByColumnName,
			@PathVariable(value = "groupByColumnValue") String groupByColumnValue,
			@PathVariable(value = "completionStatus") String completionStatus,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig,
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "filteredColumn") String filteredColumn,
			@PathVariable(value = "filteredColumnValue") String filteredColumnValue,
			@PathVariable(value = "isExport") Long isExport) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getUserAssignmentDetailsForManager");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/managerId=").append(managerId);
			buf.append("/groupByColumnName=").append(groupByColumnName);
			buf.append("/groupByColumnValue=").append(groupByColumnValue);
			buf.append("/completionStatus=").append(completionStatus);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append("/siteId=").append(siteId);
			buf.append("/filteredColumn=").append(filteredColumn);
			buf.append("/filteredColumnValue=").append(filteredColumnValue);
			buf.append("/isExport=").append(isExport);
			buf.append(".........");
		logger.debug(buf);
	
		lrnResponseForPerformance = userAssignmentService.get3rdChartUserCompletionStatusDrilldown(
				dashBoardId, managerId, groupByColumnName, groupByColumnValue,
				completionStatus, hasDashBoardConfig, siteId, filteredColumn, filteredColumnValue, isExport);
		
		return lrnResponseForPerformance;
	}
	
	@RequestMapping(value = "/getCourseAssignmentStatusManager/dashBoardId={dashBoardId}/managerId={managerId}/hasDashBoardConfig={hasDashBoardConfig}/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCourseAssignmentStatusManager(

			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "managerId") String managerId,
			@PathVariable(value = "hasDashBoardConfig") Long hasDashBoardConfig,
			@PathVariable(value = "siteId") Long siteId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCourseAssignmentStatusManager");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/managerId=").append(managerId);
			buf.append("/hasDashBoardConfig=").append(hasDashBoardConfig);
			buf.append("/siteId=").append(siteId);
			buf.append(".........");
		logger.debug(buf);
	
		lrnResponseForPerformance = courseService.get4thChartCourseStatusDetails(dashBoardId, managerId, hasDashBoardConfig, siteId); 
		
		return lrnResponseForPerformance;
	}
	
	/**********  Dashboard Configuration Services **********/
	@RequestMapping(value = "/getDashboardDetails/siteId={siteId}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardConfigurationForSite(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "userId") String userId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getDashboardDetails");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
		logger.debug(buf);
	
		return dashboardService.getDashboardConfigurationForSite(siteId);
	}
	
	@RequestMapping(value = "/saveDashboard/siteId={siteId}/userId={userId}/active={active}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardDetails(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "active") Long active) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveDashboard");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
			buf.append("/active=").append(active);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardService.saveDashboard(siteId, userId, active);
	}

	@RequestMapping(value = "/getDashboardForConfiguration/siteId={siteId}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardForSite(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "userId") String userId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getDashboardForConfiguration");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
		
		return dashboardService.getDashboardForSite(siteId);
	}
	
	@RequestMapping(value = "/showDashboard/siteId={siteId}/active={active}/userId={userId}/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveDashboardConfiguration(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "active") Long active,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "dashBoardId") Long dashBoardId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/showDashboard");
			buf.append("/siteId=").append(siteId);
			buf.append("/active=").append(active);
			buf.append("/userId=").append(userId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardService.saveDashboardConfiguration(siteId, active, userId, dashBoardId);
	}

	@RequestMapping(value = "/getDashBoardViewConfig/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardConfigsForCharts(
			@PathVariable(value = "siteId") Long siteId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getDashBoardViewConfig");
			buf.append("/siteId=").append(siteId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardService.getDashboardConfigsForCharts(siteId);
	}

	@RequestMapping(value = "/getGroupByColumnNameAndDisplayName/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getGroupByDashboardSelectionCriteria(
			@PathVariable(value = "dashBoardId") Long dashBoardId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getGroupByColumnNameAndDisplayName");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardService.getGroupByDashboardSelectionCriteria(dashBoardId);
	}

	@RequestMapping(value = "/getUserColumnDetails/companyColumn={company}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getCompanyUsersColumn(
			@PathVariable(value = "company") String company) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getUserColumnDetails");
			buf.append("/company=").append(company);
			buf.append(".........");
		logger.debug(buf);
		
		return userService.getCompanyUsersColumn(company);
	}

	@RequestMapping(value = "/getHierarchyConfiguration/siteId={siteId}/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getHierarchySelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getHierarchyConfiguration");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.getHierarchySelection(siteId, dashBoardId);
	}
	
	
	@RequestMapping(value = "/saveHierarchy/siteId={siteId}/dashBoardId={dashBoardId}/hierarchy1Value={hierarchy1Value}/hierarchy1Updated={hierarchy1Updated}/hierarchy2Value={hierarchy2Value}/hierarchy2Updated={hierarchy2Updated}/hierarchy3Value={hierarchy3Value}/hierarchy3Updated={hierarchy3Updated}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveHierarchySelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "hierarchy1Value") String hierarchy1Value,
			@PathVariable(value = "hierarchy1Updated") String hierarchy1Updated,
			@PathVariable(value = "hierarchy2Value") String hierarchy2Value,
			@PathVariable(value = "hierarchy2Updated") String hierarchy2Updated,
			@PathVariable(value = "hierarchy3Value") String hierarchy3Value,
			@PathVariable(value = "hierarchy3Updated") String hierarchy3Updated,
			@PathVariable(value = "userId") String userId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveHierarchy");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/hierarchy1Value=").append(hierarchy1Value);
			buf.append("/hierarchy1Updated=").append(hierarchy1Updated);
			buf.append("/hierarchy2Value=").append(hierarchy2Value);
			buf.append("/hierarchy2Updated=").append(hierarchy2Updated);
			buf.append("/hierarchy3Value=").append(hierarchy3Value);
			buf.append("/hierarchy3Updated=").append(hierarchy3Updated);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.saveHierarchySelection(siteId, dashBoardId,
				hierarchy1Value, hierarchy1Updated, hierarchy2Value,
				hierarchy2Updated, hierarchy3Value, hierarchy3Updated, userId);
	}

	@RequestMapping(value = "/getCurriculum/siteId={siteId}/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardCampaignSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getCurriculum");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
		
		return curriculumService.getDashboardCampaignSelection(siteId, dashBoardId);
	}
	

	@RequestMapping(value = "/saveCurriculumBySiteIdAndDashBoardId/siteId={siteId}/dashBoardId={dashBoardId}/curriculumIds={curriculumIds}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveDashboardCampaignSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "curriculumIds") String curriculumIds,
			@PathVariable(value = "userId") String userId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveCurriculumBySiteIdAndDashBoardId");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/curriculumIds=").append(curriculumIds);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
	
		return curriculumService.saveDashboardCampaignSelection(siteId,
				dashBoardId, curriculumIds, userId);
	}

	@RequestMapping(value = "/getChartConfiguration/siteId={siteId}/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getChartConfiguration");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.getChartSelection(siteId, dashBoardId);
	}


	@RequestMapping(value = "/saveChartSelection/siteId={siteId}/"
			+ "dashBoardId={dashBoardId}/"
			+ "userId={userId}/"
			+ "includedColumns={includedColumns}/"
			+ "columnsUpdated={columnsUpdated}/"
			+ "assignmentStatusSelected={assignmentStatusSelected}/"
			+ "incompleteAndPastDueSelected={incompleteAndPastDueSelected}/"
			+ "courseCompletionSatusSelected={courseCompletionSatusSelected}/"
			+ "courseCompletionStatusWithGroupBySelected={courseCompletionStatusWithGroupBySelected}/"
			+ "statusReportCompletionSatusSelected={statusReportCompletionSatusSelected}/"
			+ "statusReportUserProgressSelected={statusReportUserProgressSelected}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "includedColumns") String includedColumns,
			@PathVariable(value = "columnsUpdated") String columnsUpdated,
			@PathVariable(value = "assignmentStatusSelected") String assignmentStatusSelected,
			@PathVariable(value = "incompleteAndPastDueSelected") String incompleteAndPastDueSelected,
			@PathVariable(value = "courseCompletionSatusSelected") String courseCompletionSatusSelected,
			@PathVariable(value = "courseCompletionStatusWithGroupBySelected") String courseCompletionStatusWithGroupBySelected,
			@PathVariable(value = "statusReportCompletionSatusSelected") String statusReportCompletionSatusSelected,
			@PathVariable(value = "statusReportUserProgressSelected") String statusReportUserProgressSelected ) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveChartSelection");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/includedColumns=").append(includedColumns);
			buf.append("/columnsUpdated=").append(columnsUpdated);
			buf.append("/assignmentStatusSelected=").append(assignmentStatusSelected);
			buf.append("/incompleteAndPastDueSelected=").append(incompleteAndPastDueSelected);
			buf.append("/courseCompletionSatusSelected=").append(courseCompletionSatusSelected);
			buf.append("/courseCompletionStatusWithGroupBySelected=").append(courseCompletionStatusWithGroupBySelected);
			buf.append("/statusReportCompletionSatusSelected=").append(statusReportCompletionSatusSelected);
			buf.append("/statusReportUserProgressSelected=").append(statusReportUserProgressSelected);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.saveChartSelection(siteId, dashBoardId,
				userId, includedColumns, columnsUpdated,
				assignmentStatusSelected, incompleteAndPastDueSelected,
				courseCompletionSatusSelected,
				courseCompletionStatusWithGroupBySelected,
				statusReportCompletionSatusSelected,
				statusReportUserProgressSelected);
	}
	
	@RequestMapping(value = "/saveChartSelection/siteId={siteId}/"
			+ "dashBoardId={dashBoardId}/"
			+ "userId={userId}/"
			+ "includedColumns={includedColumns}/"
			+ "columnsUpdated={columnsUpdated}/"
			+ "assignmentStatusSelected={assignmentStatusSelected}/"
			+ "incompleteAndPastDueSelected={incompleteAndPastDueSelected}/"
			+ "courseCompletionSatusSelected={courseCompletionSatusSelected}/"
			+ "courseCompletionStatusWithGroupBySelected={courseCompletionStatusWithGroupBySelected}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "includedColumns") String includedColumns,
			@PathVariable(value = "columnsUpdated") String columnsUpdated,
			@PathVariable(value = "assignmentStatusSelected") String assignmentStatusSelected,
			@PathVariable(value = "incompleteAndPastDueSelected") String incompleteAndPastDueSelected,
			@PathVariable(value = "courseCompletionSatusSelected") String courseCompletionSatusSelected,
			@PathVariable(value = "courseCompletionStatusWithGroupBySelected") String courseCompletionStatusWithGroupBySelected) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveChartSelection");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/includedColumns=").append(includedColumns);
			buf.append("/columnsUpdated=").append(columnsUpdated);
			buf.append("/assignmentStatusSelected=").append(assignmentStatusSelected);
			buf.append("/incompleteAndPastDueSelected=").append(incompleteAndPastDueSelected);
			buf.append("/courseCompletionSatusSelected=").append(courseCompletionSatusSelected);
			buf.append("/courseCompletionStatusWithGroupBySelected=").append(courseCompletionStatusWithGroupBySelected);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.saveChartSelection(siteId, dashBoardId,
				userId, includedColumns, columnsUpdated,
				assignmentStatusSelected, incompleteAndPastDueSelected,
				courseCompletionSatusSelected,
				courseCompletionStatusWithGroupBySelected,
				"0",
				"0");
	}
	
	@RequestMapping(value = "/getDrillDownConfiguration/siteId={siteId}/dashBoardId={dashBoardId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDrillDownChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getDrillDownConfiguration");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.getDrillDownChartSelection(siteId, dashBoardId);
	}
	
	@RequestMapping(value = "/saveDrillDown/siteId={siteId}/"
			+ "dashBoardId={dashBoardId}/"
			+ "userId={userId}/"
			+ "drillDownReportSelected={drillDownReportSelected}/"
			+ "includedColumnListForDrillDown={includedColumnListForDrillDown}/"
			+ "includedColumnDisplayNameListForDrillDown={includedColumnDisplayNameListForDrillDown}/"
			+ "includedColumnListForDrillDownUpdated={includedColumnListForDrillDownUpdated}/"
			
			+ "drillDownUserSummaryDetailsSelected={drillDownUserSummaryDetailsSelected}/"
			+ "includedColumnListForSummaryDetailsDrillDown={includedColumnListForSummaryDetailsDrillDown}/"
			+ "includedColumnDisplayNameListForSummaryDetailsDrillDown={includedColumnDisplayNameListForSummaryDetailsDrillDown}/"
			+ "includedColumnListForSummaryDetailsDrillDownUpdated={includedColumnListForSummaryDetailsDrillDownUpdated}/"
			
			+ "statusReportCompletionStatusDrilldownSelected={statusReportCompletionStatusDrilldownSelected}/"
			+ "includedColumnListForStatusReportCompletionStatusDrilldown={includedColumnListForStatusReportCompletionStatusDrilldown}/"
			+ "includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown={includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown}/"
			+ "includedColumnListForStatusReportCompletionStatusDrilldownUpdated={includedColumnListForStatusReportCompletionStatusDrilldownUpdated}/"
			
			+ "statusReportUserProgressDrilldownSelected={statusReportUserProgressDrilldownSelected}/"
			+ "includedColumnListForStatusReportUserProgressDrilldown={includedColumnListForStatusReportUserProgressDrilldown}/"
			+ "includedColumnDisplayNameListForStatusReportUserProgressDrilldown={includedColumnDisplayNameListForStatusReportUserProgressDrilldown}/"
			+ "includedColumnListForStatusReportUserProgressDrilldownUpdated={includedColumnListForStatusReportUserProgressDrilldownUpdated}"
			, method = RequestMethod.GET)
	
	public @ResponseBody LRNResponse saveDrillDownChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			
			@PathVariable(value = "drillDownReportSelected") String drillDownReportSelected,
			@PathVariable(value = "includedColumnListForDrillDown") String includedColumnListForDrillDown,
			@PathVariable(value = "includedColumnDisplayNameListForDrillDown") String includedColumnDisplayNameListForDrillDown,
			@PathVariable(value = "includedColumnListForDrillDownUpdated") String includedColumnListForDrillDownUpdated,
			
			@PathVariable(value = "drillDownUserSummaryDetailsSelected") String drillDownUserSummaryDetailsSelected,
			@PathVariable(value = "includedColumnListForSummaryDetailsDrillDown") String includedColumnListForSummaryDetailsDrillDown,
			@PathVariable(value = "includedColumnDisplayNameListForSummaryDetailsDrillDown") String includedColumnDisplayNameListForSummaryDetailsDrillDown,
			@PathVariable(value = "includedColumnListForSummaryDetailsDrillDownUpdated") String includedColumnListForSummaryDetailsDrillDownUpdated,

			@PathVariable(value = "statusReportCompletionStatusDrilldownSelected") String statusReportCompletionStatusDrilldownSelected,
			@PathVariable(value = "includedColumnListForStatusReportCompletionStatusDrilldown") String includedColumnListForStatusReportCompletionStatusDrilldown,
			@PathVariable(value = "includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown") String includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown,
			@PathVariable(value = "includedColumnListForStatusReportCompletionStatusDrilldownUpdated") String includedColumnListForStatusReportCompletionStatusDrilldownUpdated,
			
			@PathVariable(value = "statusReportUserProgressDrilldownSelected") String statusReportUserProgressDrilldownSelected,
			@PathVariable(value = "includedColumnListForStatusReportUserProgressDrilldown") String includedColumnListForStatusReportUserProgressDrilldown,
			@PathVariable(value = "includedColumnDisplayNameListForStatusReportUserProgressDrilldown") String includedColumnDisplayNameListForStatusReportUserProgressDrilldown,
			@PathVariable(value = "includedColumnListForStatusReportUserProgressDrilldownUpdated") String includedColumnListForStatusReportUserProgressDrilldownUpdated ) {
			

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveDrillDown");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/userId=").append(userId);
			
			buf.append("/drillDownReportSelected=").append(drillDownReportSelected);
			buf.append("/includedColumnListForDrillDown=").append(includedColumnListForDrillDown);
			buf.append("/includedColumnDisplayNameListForDrillDown=").append(includedColumnDisplayNameListForDrillDown);
			buf.append("/includedColumnListForDrillDownUpdated=").append(includedColumnListForDrillDownUpdated);
			
			buf.append("/drillDownUserSummaryDetailsSelected=").append(drillDownUserSummaryDetailsSelected);
			buf.append("/includedColumnListForSummaryDetailsDrillDown=").append(includedColumnListForSummaryDetailsDrillDown);
			buf.append("/includedColumnDisplayNameListForSummaryDetailsDrillDown=").append(includedColumnDisplayNameListForSummaryDetailsDrillDown);
			buf.append("/includedColumnListForSummaryDetailsDrillDownUpdated=").append(includedColumnListForSummaryDetailsDrillDownUpdated);
			
			buf.append("/statusReportCompletionStatusDrilldownSelected=").append(statusReportCompletionStatusDrilldownSelected);
			buf.append("/includedColumnListForStatusReportCompletionStatusDrilldown=").append(includedColumnListForStatusReportCompletionStatusDrilldown);
			buf.append("/includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown=").append(includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown);
			buf.append("/includedColumnListForStatusReportCompletionStatusDrilldownUpdated=").append(includedColumnListForStatusReportCompletionStatusDrilldownUpdated);
			
			buf.append("/statusReportUserProgressDrilldownSelected=").append(statusReportUserProgressDrilldownSelected);
			buf.append("/includedColumnListForStatusReportUserProgressDrilldown=").append(includedColumnListForStatusReportUserProgressDrilldown);
			buf.append("/includedColumnDisplayNameListForStatusReportUserProgressDrilldown=").append(includedColumnDisplayNameListForStatusReportUserProgressDrilldown);
			buf.append("/includedColumnListForStatusReportUserProgressDrilldownUpdated=").append(includedColumnListForStatusReportUserProgressDrilldownUpdated);
			
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.saveDrillDownChartSelection(siteId, dashBoardId, userId,
				drillDownReportSelected, includedColumnListForDrillDown,
				includedColumnDisplayNameListForDrillDown,
				includedColumnListForDrillDownUpdated,
				
				drillDownUserSummaryDetailsSelected,
				includedColumnListForSummaryDetailsDrillDown,
				includedColumnDisplayNameListForSummaryDetailsDrillDown,
				includedColumnListForSummaryDetailsDrillDownUpdated,
				
				statusReportCompletionStatusDrilldownSelected, 
				includedColumnListForStatusReportCompletionStatusDrilldown,
				includedColumnDisplayNameListForStatusReportCompletionStatusDrilldown,
				includedColumnListForStatusReportCompletionStatusDrilldownUpdated,
				
				statusReportUserProgressDrilldownSelected, 
				includedColumnListForStatusReportUserProgressDrilldown,
				includedColumnDisplayNameListForStatusReportUserProgressDrilldown,
				includedColumnListForStatusReportUserProgressDrilldownUpdated
				);
	}
	
	@RequestMapping(value = "/saveDrillDown/siteId={siteId}/"
			+ "dashBoardId={dashBoardId}/"
			+ "userId={userId}/"
			+ "drillDownReportSelected={drillDownReportSelected}/"
			+ "includedColumnListForDrillDown={includedColumnListForDrillDown}/"
			+ "includedColumnDisplayNameListForDrillDown={includedColumnDisplayNameListForDrillDown}/"
			+ "includedColumnListForDrillDownUpdated={includedColumnListForDrillDownUpdated}/"
			
			+ "drillDownUserSummaryDetailsSelected={drillDownUserSummaryDetailsSelected}/"
			+ "includedColumnListForSummaryDetailsDrillDown={includedColumnListForSummaryDetailsDrillDown}/"
			+ "includedColumnDisplayNameListForSummaryDetailsDrillDown={includedColumnDisplayNameListForSummaryDetailsDrillDown}/"
			+ "includedColumnListForSummaryDetailsDrillDownUpdated={includedColumnListForSummaryDetailsDrillDownUpdated}"
			, method = RequestMethod.GET)
	
	public @ResponseBody LRNResponse saveDrillDownChartSelection(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId,
			
			@PathVariable(value = "drillDownReportSelected") String drillDownReportSelected,
			@PathVariable(value = "includedColumnListForDrillDown") String includedColumnListForDrillDown,
			@PathVariable(value = "includedColumnDisplayNameListForDrillDown") String includedColumnDisplayNameListForDrillDown,
			@PathVariable(value = "includedColumnListForDrillDownUpdated") String includedColumnListForDrillDownUpdated,
			
			@PathVariable(value = "drillDownUserSummaryDetailsSelected") String drillDownUserSummaryDetailsSelected,
			@PathVariable(value = "includedColumnListForSummaryDetailsDrillDown") String includedColumnListForSummaryDetailsDrillDown,
			@PathVariable(value = "includedColumnDisplayNameListForSummaryDetailsDrillDown") String includedColumnDisplayNameListForSummaryDetailsDrillDown,
			@PathVariable(value = "includedColumnListForSummaryDetailsDrillDownUpdated") String includedColumnListForSummaryDetailsDrillDownUpdated) {
			

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveDrillDown");
			buf.append("/siteId=").append(siteId);
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/userId=").append(userId);
			
			buf.append("/drillDownReportSelected=").append(drillDownReportSelected);
			buf.append("/includedColumnListForDrillDown=").append(includedColumnListForDrillDown);
			buf.append("/includedColumnDisplayNameListForDrillDown=").append(includedColumnDisplayNameListForDrillDown);
			buf.append("/includedColumnListForDrillDownUpdated=").append(includedColumnListForDrillDownUpdated);
			
			buf.append("/drillDownUserSummaryDetailsSelected=").append(drillDownUserSummaryDetailsSelected);
			buf.append("/includedColumnListForSummaryDetailsDrillDown=").append(includedColumnListForSummaryDetailsDrillDown);
			buf.append("/includedColumnDisplayNameListForSummaryDetailsDrillDown=").append(includedColumnDisplayNameListForSummaryDetailsDrillDown);
			buf.append("/includedColumnListForSummaryDetailsDrillDownUpdated=").append(includedColumnListForSummaryDetailsDrillDownUpdated);
			
			buf.append(".........");
		logger.debug(buf);
	
		return dashboardConfigurationService.saveDrillDownChartSelection(siteId, dashBoardId, userId,
				drillDownReportSelected, includedColumnListForDrillDown,
				includedColumnDisplayNameListForDrillDown,
				includedColumnListForDrillDownUpdated,
				
				drillDownUserSummaryDetailsSelected,
				includedColumnListForSummaryDetailsDrillDown,
				includedColumnDisplayNameListForSummaryDetailsDrillDown,
				includedColumnListForSummaryDetailsDrillDownUpdated,
				
				"0", "0", "0", "0",
				
				"0", "0", "0", "0"
				);
	}
	
	/*********************** Assume Identity Services **********************/
	@RequestMapping(value = "/getDashboardViewSettings/dashBoardId={dashBoardId}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getDashboardViewSettings(
			@PathVariable(value = "dashBoardId") Long dashBoardId,
			@PathVariable(value = "userId") String userId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getDashboardViewSettings");
			buf.append("/dashBoardId=").append(dashBoardId);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
		
		return dashboardService.getDashboardViewSettings(dashBoardId, userId);
	}
	
	@RequestMapping(value = "/getProxyAssignmentList/siteId={siteId}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getProxyAssignmentList(
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "userId") String userId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getProxyAssignmentList");
			buf.append("/siteId=").append(siteId);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
	
		return proxyService.getProxyAssignmentList(siteId, userId);
	}
	
	@RequestMapping(value = "/saveProxyAssignment/userId={userId}/proxyLoginName={proxyLoginName}/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveProxyAssignment(
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "proxyLoginName") String proxyLoginName,
			@PathVariable(value = "siteId") Long siteId) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveProxyAssignment");
			buf.append("/userId=").append(userId);
			buf.append("/proxyLoginName=").append(proxyLoginName);
			buf.append("/siteId=").append(siteId);
			buf.append(".........");
		logger.debug(buf);
		
		return proxyService.saveProxyAssignment(userId, proxyLoginName, siteId, null, null);
	}
	
	@RequestMapping(value = "/deleteProxyAssignment/userId={userId}/proxyUserId={proxyUserId}/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse deleteProxyAssignment(
			@PathVariable(value = "userId") String userId,
			@PathVariable(value = "proxyUserId") String proxyUserId,
			@PathVariable(value = "siteId") Long siteId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/deleteProxyAssignment");
			buf.append("/userId=").append(userId);
			buf.append("/proxyUserId=").append(proxyUserId);
			buf.append("/siteId=").append(siteId);
			buf.append(".........");
		logger.debug(buf);
		
		return proxyService.deleteProxyAssignment(userId, proxyUserId);
	}
	
	@RequestMapping(value = "/getProxyUser/proxyLoginName={proxyLoginName}/siteId={siteId}/company={company}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getProxyUser(
			@PathVariable(value = "proxyLoginName") String proxyLoginName,
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "company") String company) {
		
		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/getProxyUser");
			buf.append("/proxyLoginName=").append(proxyLoginName);
			buf.append("/siteId=").append(siteId);
			buf.append("/company=").append(company);
			buf.append(".........");
		logger.debug(buf);
		
		return proxyService.getProxyUser(proxyLoginName, siteId, company);
	}
	
	@RequestMapping(value = "/saveProxyAssignmentAdminSection/assigneeUserId={assigneeUserId}/proxyLoginName={proxyLoginName}/siteId={siteId}/oldProxyUserId={oldProxyUserId}/userId={userId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse saveProxyAssignmentAdminSection(
			@PathVariable(value = "assigneeUserId") String assigneeUserId,
			@PathVariable(value = "proxyLoginName") String proxyLoginName,
			@PathVariable(value = "siteId") Long siteId,
			@PathVariable(value = "oldProxyUserId") String oldProxyUserId,
			@PathVariable(value = "userId") String userId) {

		StringBuffer buf = new StringBuffer();
			buf.append("Calling.........");
			buf.append("/saveProxyAssignmentAdminSection");
			buf.append("/assigneeUserId=").append(assigneeUserId);
			buf.append("/proxyLoginName=").append(proxyLoginName);
			buf.append("/siteId=").append(siteId);
			buf.append("/oldProxyUserId=").append(oldProxyUserId);
			buf.append("/userId=").append(userId);
			buf.append(".........");
		logger.debug(buf);
		
		return proxyService.saveProxyAssignment(assigneeUserId, proxyLoginName, siteId, oldProxyUserId, userId);
	}
	
	@RequestMapping(value = "/getProxyAssignmentAndProxyPermissionUserList/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getProxyAssignmentAndProxyPermissionUserList(
			@PathVariable(value = "siteId") Long siteId) {
		
		StringBuffer buf = new StringBuffer();
		buf.append("Calling.........");
			buf.append("/getProxyAssignmentAndProxyPermissionUserList");
			buf.append("/siteId=").append(siteId);
		logger.debug(buf);
		
		return proxyService.getProxyAssignmentAndProxyPermissionUserList(siteId);
	}

	@RequestMapping(value = "/getLastUpdatedDate/siteId={siteId}", method = RequestMethod.GET)
	public @ResponseBody LRNResponse getLastUpdatedDate(
			@PathVariable(value = "siteId") Long siteId) {
		
		StringBuffer buf = new StringBuffer();
		buf.append("Calling.........");
			buf.append("/getLastUpdatedDate");
			buf.append("/siteId=").append(siteId);
		logger.debug(buf);
		
		return dashboardService.getLastUpdatedDate(siteId);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/saveShowExportReport
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "showExport": "0" }
	 */
	@RequestMapping(value = "saveShowExportReport", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse saveShowExportReport(
			@RequestBody ShowExportReportDTO requestDTO) {
		
		return dashboardConfigurationService.saveShowExportReport(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/getShowExportReport
	 * { "dashboardId" : "29", "siteId" : "5532" }
	 */
	@RequestMapping(value = "getShowExportReport", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getShowExportReport(
			@RequestBody ShowExportReportDTO requestDTO) {
		
		return dashboardConfigurationService.getShowExportReport(requestDTO);
	}
}
