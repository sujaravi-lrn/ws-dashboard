package com.lrn.rest.controller;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.ReportQueueRequestDTO;
import com.lrn.dto.request.ReporteeAndModuleCountsRequestDTO;
import com.lrn.model.LRNResponse;
import com.lrn.services.IUserReportsService;

@Controller
@RequestMapping("/api/")
public class UserReportsController {
	
	@Autowired
	private IUserReportsService userReportsService;
	

	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/directReporteeCounts
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "directReporteeCounts", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getDirectReporteeCounts(
			@RequestBody ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		return userReportsService.getDirectReporteeCounts(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/reporteeAndModuleCounts
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "reporteeAndModuleCounts", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getReporteeAndModuleCounts(
			@RequestBody ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		return userReportsService.getReporteeAndModuleCounts(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/reporteeAndModuleCountsDrilldown
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "reporteeAndModuleCountsDrilldown", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getReporteeAndModuleCountsDrilldown(
			@RequestBody ReporteeAndModuleCountsRequestDTO requestDTO) {
		
		return userReportsService.getReporteeAndModuleCountsDrilldown(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/directReporteeAndUserCountsTotalRecords
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "directReporteeAndUserCountsTotalRecords", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getDirectReporteeAndUserCountsTotalRecords(
			@RequestBody DrilldownRequestDTO requestDTO) {
		
		return userReportsService.getDirectReporteeAndUserCountsTotalRecords(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/directReporteeAndUserCounts
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "directReporteeAndUserCounts", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getDirectReporteeAndUserCounts(
			@RequestBody DrilldownRequestDTO requestDTO) {
		
		return userReportsService.getDirectReporteeAndUserCounts(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/directReporteeAndUserReportTotalRecords
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "directReporteeAndUserReportTotalRecords", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getDirectReporteeAndUserReportTotalRecords(
			@RequestBody DrilldownRequestDTO requestDTO) {
		
		return userReportsService.getDirectReporteeAndUserReportTotalRecords(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/directReporteeAndUserReport
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "directReporteeAndUserReport", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getDirectReporteeAndUserReport(
			@RequestBody DrilldownRequestDTO requestDTO) {
		
		return userReportsService.getDirectReporteeAndUserReport(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/reportQueueHistory
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845" }
	 */
	@RequestMapping(value = "reportQueueHistory", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getReportQueueHistory(
			@RequestBody ReportQueueRequestDTO requestDTO) {
		
		return userReportsService.getReportQueueHistory(requestDTO);
	}
}
