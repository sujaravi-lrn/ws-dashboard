package com.lrn.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dto.UserLabelsDTO;
import com.lrn.dto.response.ProxyUserDTO;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public interface IUserManager {

	public String getUserColumnForDashboardConfig(String managerId, String configValue);

	public UserLabelsDTO getCompanyUsersColumn(String company);

	List<String> getDataPrivacyUserColumns(Long siteId, String managerId, String columnName);

	Map<String, ArrayList<String>> getDataPrivacyUserColumns(Long siteId, String managerId);

	boolean isDataPrivacySetupForSite(Long siteId);
	
	ProxyUserDTO findByUsername(String username, String company);
}
