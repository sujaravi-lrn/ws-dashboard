package com.lrn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dto.UserLabelsDTO;
import com.lrn.dto.response.ProxyUserDTO;
import com.lrn.model.User;

public interface IUserDao extends IGenericDao<User, String> {
	
	public UserLabelsDTO getCompanyUsersColumn(String company);

	public String getUserColumnForDashboardConfig(String managerId, String configValue);

	List<String> getDataPrivacyUserColumns(Long siteId, String managerId, String columnName);

	Map<String, ArrayList<String>> getDataPrivacyUserColumns(Long siteId, String managerId);

	public boolean isDataPrivacySetupForSite(Long siteId);

	ProxyUserDTO findByUsername(String username, String company);
}
