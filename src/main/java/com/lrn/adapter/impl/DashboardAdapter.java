package com.lrn.adapter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lrn.adapter.IDashboardAdapter;
import com.lrn.customException.DashBoardIdIsBlankException;
import com.lrn.customException.DashBoardIdNotFoundException;
import com.lrn.customException.SiteIdNotFoundException;
import com.lrn.dto.ColumnDetailsDTO;
import com.lrn.dto.DashBoardViewConfigDTO;
import com.lrn.dto.DashboardAndConfigurationDTO;
import com.lrn.dto.DashboardViewSettingsDTO;
import com.lrn.dto.GetDashboardDTO;
import com.lrn.dto.PartnerSiteDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.manager.IDashboardConfigurationManager;
import com.lrn.manager.IDashboardManager;
import com.lrn.manager.IDashboardSelectionCriteriaManager;
import com.lrn.manager.IPartnerSiteManager;
import com.lrn.model.Dashboard;
import com.lrn.model.DashboardConfiguration;
import com.lrn.model.DashboardSelectionCriteria;
import com.lrn.util.ApplicationConstant;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public class DashboardAdapter implements IDashboardAdapter {

	private IDashboardManager dashboardManager;
	private IDashboardConfigurationManager dashboardConfigurationManager;
	private IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager;
	private IPartnerSiteManager partnerSiteManager;
	
	public void setDashboardManager(IDashboardManager dashboardManager) {
		this.dashboardManager = dashboardManager;
	}
	
	public void setDashboardConfigurationManager(
			IDashboardConfigurationManager dashboardConfigurationManager) {
		this.dashboardConfigurationManager = dashboardConfigurationManager;
	}

	public void setPartnerSiteManager(IPartnerSiteManager partnerSiteManager) {
		this.partnerSiteManager = partnerSiteManager;
	}

	public void setDashboardSelectionCriteriaManager(
			IDashboardSelectionCriteriaManager dashboardSelectionCriteriaManager) {
		this.dashboardSelectionCriteriaManager = dashboardSelectionCriteriaManager;
	}

	@Override
	public Long saveDashboard(Long siteId, String userId, Long active) {
		
		if (siteId == null) 
			throw new SiteIdNotFoundException(ApplicationConstant.DASBOARD_DETAILS_NOT_ADDED_ON_DATABASE_FOR_THIS_REQUEST_BECAUSE_SITE_ID_IS_GETTING_NULL_ON_REQUEST);
		
		Dashboard dashboard = dashboardManager.getDashboardForSite(siteId);
		PartnerSiteDTO partnerSiteDTO = partnerSiteManager.getPartnerSiteInfo(siteId);
		
		return dashboardManager.saveDashboard(siteId, userId, active, dashboard, partnerSiteDTO);
	}

	@Override
	public GetDashboardDTO getDashboardConfigurationForSite(Long siteId) {
		DashboardAndConfigurationDTO dashboardConfigDTO = dashboardConfigurationManager.getDashboardConfigurationForSite(siteId);
		
		GetDashboardDTO dto = new GetDashboardDTO();
		if(dashboardConfigDTO != null) {
			dto.setActive(dashboardConfigDTO.getConfigValue());
			dto.setDashboardID(dashboardConfigDTO.getDashboardID());
		} 
		return dto;
		
	}

	@Override
	public GetDashboardDTO getDashboardForSite(Long siteId) {
		Dashboard dashboard = dashboardManager.getDashboardForSite(siteId);
		GetDashboardDTO dto = new GetDashboardDTO();
		if(dashboard != null) {
			dto.setDashboardID(dashboard.getDashboardId());
			dto.setActive(dashboard.getActive().toString());
		}
		return dto;
	}

	@Override
	public void saveDashboardConfiguration(Long siteId, Long active, String userID, Long dashboardId) {
		
		if(dashboardId == null)
	    	   throw new DashBoardIdNotFoundException(ApplicationConstant.DASHBOARD_ID_NULL_ON_REQUEST_SHOULD_NOT_SEND_DASHBOARD_ID_NULL); 
		
		DashboardAndConfigurationDTO dashboardConfigDTO = dashboardConfigurationManager.getDashboardConfigurationForSite(siteId);
		
		dashboardConfigurationManager.saveDashboardConfigurationFromDto(active, userID, dashboardId, dashboardConfigDTO);
		
		//after saving refresh the dashboard settings from DB
		dashboardManager.loadDashboardSettingsForAllSites();
	}


	@Override
	public DashBoardViewConfigDTO getDashboardConfigsForCharts(Long siteId) {
		
		if(siteId == null)
			throw new SiteIdNotFoundException(ApplicationConstant.SITE_ID_SHOULD_NOT_BE_NULL_FOR_RETRIVING_THE_GETDASHBOARDVIEWCONFIG);
		
		DashBoardViewConfigDTO dto = new DashBoardViewConfigDTO();
		Dashboard dashboard = dashboardManager.getDashboardForSite(siteId);
		if(dashboard == null)
			return dto;
		
		Long [] configTypeIdsArr = {6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L};
		Long dashboardId = dashboard.getDashboardId();
		Map<Long, DashboardConfiguration> dcMap = dashboardConfigurationManager.getDashboardConfigurationForConfigTypes(dashboardId, Arrays.asList(configTypeIdsArr));
		
			dto.setDashBoardId(dashboardId);
			dto.setShowUserAssignmentStatusChart(dcMap.get(6L) != null ? dcMap.get(6L).getConfigValue() : "0");
			dto.setShowIncompletePastDue(dcMap.get(7L) != null ? dcMap.get(7L).getConfigValue() : "0");
			dto.setShowCourseCompletionStatusChart(dcMap.get(8L) != null ? dcMap.get(8L).getConfigValue() : "0");
			dto.setShowCourseCompletionStatusWithGroupByChart(dcMap.get(9L) != null ? dcMap.get(9L).getConfigValue() : "0");
			dto.setShowDrillDownReport(dcMap.get(10L) != null ? dcMap.get(10L).getConfigValue() : "0");
			dto.setShowDrillDownUserSummaryDetailReport(dcMap.get(11L) != null ? dcMap.get(11L).getConfigValue() : "0");
			
			dto.setShowStatusReportCompletionStatusReport(dcMap.get(12L) != null ? dcMap.get(12L).getConfigValue() : "0");
			dto.setShowStatusReportUserProgressReport(dcMap.get(13L) != null ? dcMap.get(13L).getConfigValue() : "0");
			dto.setShowStatusReportCompletionStatusDrilldownReport(dcMap.get(14L) != null ? dcMap.get(14L).getConfigValue() : "0");
			dto.setShowStatusReportUserProgressDrilldownReport(dcMap.get(15L) != null ? dcMap.get(15L).getConfigValue() : "0");
		return dto;
	}

	@Override
	public List<ColumnDetailsDTO> getGroupByDashboardSelectionCriteria(Long dashboardId) {
		if(dashboardId == null)
			throw new DashBoardIdIsBlankException(ApplicationConstant.DASHBOARD_ID_SHOULD_NOT_BE_BLANK_OR_NULL);
		
		List<DashboardSelectionCriteria> dscList = dashboardSelectionCriteriaManager.getDashboardSelectionCriteriaByClauseName(dashboardId, "Group By");
		List<ColumnDetailsDTO> dtoList = new ArrayList<ColumnDetailsDTO> ();
		
		for(DashboardSelectionCriteria dsc : dscList) {
			ColumnDetailsDTO dto = new ColumnDetailsDTO();
				dto.setColumnDisplayName(dsc.getSelectionValue());
				dto.setColumnName(dsc.getColumnName());
				dto.setSequenceNumber(new Long(dsc.getSelectionSeq()));
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@Override
	public Dashboard getDashboardById(Long dashboardId) {
		return dashboardManager.get(dashboardId);
	}

	@Override
	public DashboardViewSettingsDTO getDashboardViewSettings(Long dashboardId, String userId) {
		
		if(dashboardId==null)
			throw new DashBoardIdIsBlankException(ApplicationConstant.DASHBOARD_ID_SHOULD_NOT_BE_BLANK_OR_NULL);
		if(userId==null)
			throw new DashBoardIdIsBlankException(ApplicationConstant.USER_ID_NULL_ON_REQUEST);
			
		DashboardViewSettingsDTO dashboardViewSettingsDTO = new DashboardViewSettingsDTO();
			dashboardViewSettingsDTO.setAssignedProxyUsersDTO(dashboardManager.getAssignedUserIdsforProxyUsers(dashboardId, userId));
			dashboardViewSettingsDTO.setColumnDetailsDTO(getGroupByDashboardSelectionCriteria(dashboardId));
		
		return dashboardViewSettingsDTO;
	}

	@Override
	public DashboardUpdateDateDTO getLastUpdatedDate(Long siteId) {
		return dashboardManager.getLastUpdatedDate(siteId);
	}

}
