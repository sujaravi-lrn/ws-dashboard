package com.lrn.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IUserDao;
import com.lrn.dto.UserLabelsDTO;
import com.lrn.dto.response.ProxyUserDTO;
import com.lrn.manager.IUserManager;

/**
 * @author Suja.Ravi
 * May 25, 2016
 */
public class UserManager implements IUserManager {

	private IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String getUserColumnForDashboardConfig(String managerId, String configValue) {
		return userDao.getUserColumnForDashboardConfig(managerId, configValue);
	}

	@Override
	public UserLabelsDTO getCompanyUsersColumn(String company) {
		return userDao.getCompanyUsersColumn(company);
	}

	@Override
	public List<String> getDataPrivacyUserColumns(Long siteId, String managerId, String columnName) {
		return userDao.getDataPrivacyUserColumns(siteId, managerId, columnName);
	}
	
	@Override
	public Map<String, ArrayList<String>> getDataPrivacyUserColumns(Long siteId, String managerId) {
		return userDao.getDataPrivacyUserColumns(siteId, managerId);
	}
	
	@Override
	public boolean isDataPrivacySetupForSite(Long siteId) {
		return userDao.isDataPrivacySetupForSite(siteId);
	}
	
	@Override
	public ProxyUserDTO findByUsername(String username, String company) {
		return userDao.findByUsername(username, company);
	}
	
}
