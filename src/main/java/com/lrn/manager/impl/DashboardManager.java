package com.lrn.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IDashboardDao;
import com.lrn.dao.IGenericDao;
import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.PartnerSiteDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.manager.IDashboardManager;
import com.lrn.model.Dashboard;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public class DashboardManager extends GenericManager<Dashboard, Long> implements IDashboardManager {

	private IDashboardDao dashboardDao;
	
	private Map<Long, Dashboard> dashboardSettingsForAllSitesMap = null;
	
	public DashboardManager(IGenericDao<Dashboard, Long> iGenericDao) {
		super(iGenericDao);
	}

	public void setDashboardDao(IDashboardDao dashboardDao) {
		this.dashboardDao = dashboardDao;
	}

	@Override
	public Dashboard getDashboardForSite(Long siteId) {
		
		return dashboardDao.getDashboardForSite(siteId);
		
/*		Dashboard dashboard = dashboardSettingsForAllSitesMap.get(siteId);
		if(dashboard == null) {
			loadDashboardSettingsForAllSites();
			return dashboardDao.getDashboardForSite(siteId);
		}
		return dashboard;*/
	}

	@Override
	public Long saveDashboard(Long siteId, String userId, Long active,
			Dashboard dashboard, PartnerSiteDTO partnerSiteDTO) {
		
		if(dashboard == null) {
			dashboard = new Dashboard();
			dashboard.setDashboardId(-1L);
			dashboard.setDashboardName((partnerSiteDTO.getPartnerName() + "::" + partnerSiteDTO.getSiteName()));
			dashboard.setDescription("Saving Dashboard");
			dashboard.setPartnerID(partnerSiteDTO.getPartnerId());
			dashboard.setSiteId(siteId);
		}
			
		dashboard.setActive(active);
		dashboard.setCreatedBy(userId);
		dashboard.setCreatedOn(new Date());
		dashboard.setUpdatedBy(userId);
		dashboard.setUpdatedOn(new Date());
		
		dashboard = this.save(dashboard);
		
		//after saving, refresh the list from DB
//		loadDashboardSettingsForAllSites();

		return dashboard.getDashboardId();
	}

	@Override
	public List<AssignedProxyUsersDTO> getAssignedUserIdsforProxyUsers(Long dashboardId, String proxyUserId) {
		return dashboardDao.getAssignedUserIdsforProxyUsers(dashboardId, proxyUserId);
	}

	@Override
	public DashboardUpdateDateDTO getLastUpdatedDate(Long siteId) {
		return dashboardDao.getLastUpdatedDate(siteId);
	}

	@Override 
	public Map<Long, Dashboard> getDashboardSettingsForAllSites() {
		loadDashboardSettingsForAllSites();
			
		return dashboardSettingsForAllSitesMap;
	}

	@Override
	public void loadDashboardSettingsForAllSites() {
			
		List<Dashboard> dashboardList = dashboardDao.getDashboardSettingsForAllSites();
		
		dashboardSettingsForAllSitesMap = new HashMap<Long, Dashboard> ();
		for(Dashboard d : dashboardList)
			dashboardSettingsForAllSitesMap.put(d.getSiteId(), d);
		
	}
	
	public void init() {
//		loadDashboardSettingsForAllSites();
	}
}
