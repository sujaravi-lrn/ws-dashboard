package com.lrn.dao;

import java.util.List;

import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.model.Dashboard;

public interface IDashboardDao extends IGenericDao<Dashboard, String> {

	public Dashboard getDashboardForSite(Long siteId);

	List<AssignedProxyUsersDTO> getAssignedUserIdsforProxyUsers(Long dashboardId, String proxyUserId);

	public DashboardUpdateDateDTO getLastUpdatedDate(Long siteId);

	List<Dashboard> getDashboardSettingsForAllSites();

}
