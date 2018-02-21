package com.lrn.manager.impl;

import java.util.List;

import com.lrn.dao.IGenericDao;
import com.lrn.dao.IProxyDao;
import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.manager.IProxyManager;
import com.lrn.model.DashboardProxy;
import com.lrn.model.DashboardProxyPK;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ProxyManager extends GenericManager<DashboardProxy, DashboardProxyPK> implements IProxyManager {

	public ProxyManager(IGenericDao<DashboardProxy, DashboardProxyPK> iGenericDao) {
		super(iGenericDao);
	}

	private IProxyDao proxyDao;
	

	public void setProxyDao(IProxyDao proxyDao) {
		this.proxyDao = proxyDao;
	}

	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentList(Long siteId, String userId) {
		return proxyDao.getProxyAssignmentList(siteId, userId);
	}

	@Override
	public String isValidLoginName(String proxyLoginName, Long siteId) {
		return proxyDao.isValidLoginName(proxyLoginName, siteId);
	}

	@Override
	public String isUserActive(String proxyLoginName, Long siteId) {
		return proxyDao.isUserActive(proxyLoginName, siteId);
	}

	@Override
	public String isProxyAlreadyAssigned(String assigneeUserId, String proxyLoginName, Long siteId) {
		return proxyDao.isProxyAlreadyAssigned(assigneeUserId, proxyLoginName, siteId);
	}

	@Override
	public DashboardProxy getDashboardProxyByUserIdProxyUserId(String userId, String proxyUserId) {
		return proxyDao.getDashboardProxyByUserIdProxyUserId(userId, proxyUserId);
	}

	@Override
	public DashboardProxy saveDashboardProxy(DashboardProxy proxy, String oldProxyUserId) {
		return proxyDao.saveDashboardProxy(proxy, oldProxyUserId);
	}

	@Override
	public boolean deleteProxyAssignment(String userId, String proxyUserId) {
		return proxyDao.deleteProxyAssignment(userId, proxyUserId);
	}

	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentAndProxyPermissionUserList(Long siteId) {
		return proxyDao.getProxyAssignmentAndProxyPermissionUserList(siteId);
	}
}
