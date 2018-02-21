package com.lrn.rest.controller;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrn.dto.request.DrilldownRequestDTO;
import com.lrn.dto.request.CompletionStatusReportRequestDTO;
import com.lrn.model.LRNResponse;
import com.lrn.services.ICompletionStatusReportService;

@Controller
@RequestMapping("/api/")
public class CompletionStatusReportsController {

	@Autowired
	private ICompletionStatusReportService completionStatusReportService;
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/completionStatusReport
	 * { "dashboardId" : "29", "siteId" : "5532", "managerId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0" }
	 */
	@RequestMapping(value = "completionStatusReport", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getCompletionStatusReport(
			@RequestBody CompletionStatusReportRequestDTO requestDTO) {
		
		return completionStatusReportService.getCompletionStatusReport(requestDTO);
	}
	
	/**
	 * http://10.103.30.72:8080/catalystdashboard/rest/api/completionStatusReportDrilldown
	 * { "dashboardId" : "29", "siteId" : "5532", "userId" : "21187845", "groupByColumnName": "", "hasDashboardConfig": "0", "completionStatus": "inProgress" }
	 */
	@RequestMapping(value = "completionStatusReportDrilldown", method = RequestMethod.POST)
	@Consumes("application/json")
	public @ResponseBody LRNResponse getCompletionStatusReportDrilldown(
			@RequestBody DrilldownRequestDTO requestDTO) {
		
		return completionStatusReportService.getCompletionStatusReportDrilldown(requestDTO);
	}
	
}
