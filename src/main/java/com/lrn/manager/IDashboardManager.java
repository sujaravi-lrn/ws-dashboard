package com.lrn.manager;

import java.util.List;
import java.util.Map;

import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.PartnerSiteDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.model.Dashboard;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public interface IDashboardManager extends IGenericManager<Dashboard, Long> {

	Dashboard getDashboardForSite(Long siteId);

	Long saveDashboard(Long siteId, String userId, Long active, Dashboard dashboard, PartnerSiteDTO partnerSiteDTO);

	List<AssignedProxyUsersDTO> getAssignedUserIdsforProxyUsers(Long dashboardId, String proxyUserId);

	DashboardUpdateDateDTO getLastUpdatedDate(Long siteId);

	Map<Long, Dashboard> getDashboardSettingsForAllSites();

	void loadDashboardSettingsForAllSites();

}
