package com.lrn.dao;

import java.util.List;

import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.model.DashboardProxy;
import com.lrn.model.DashboardProxyPK;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public interface IProxyDao extends IGenericDao<DashboardProxy, DashboardProxyPK> {

	List<AssignedProxyUsersDTO> getProxyAssignmentList(Long siteId, String userId);

	String isValidLoginName(String proxyLoginName, Long siteId);

	String isUserActive(String proxyLoginName, Long siteId);

	String isProxyAlreadyAssigned(String assigneeUserId, String proxyLoginName, Long siteId);

	DashboardProxy getDashboardProxyByUserIdProxyUserId(String userId, String proxyUserId);

	DashboardProxy saveDashboardProxy(DashboardProxy proxy, String oldProxyUserId);

	boolean deleteProxyAssignment(String userId, String proxyUserId);

	List<AssignedProxyUsersDTO> getProxyAssignmentAndProxyPermissionUserList(Long siteId);

}
