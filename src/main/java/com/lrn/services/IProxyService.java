package com.lrn.services;

import com.lrn.model.LRNResponse;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public interface IProxyService {

	LRNResponse getProxyAssignmentList(Long siteId, String userId);

	LRNResponse saveProxyAssignment(String assigneeUserId, String proxyLoginName,
			Long siteId, String oldProxyUserId, String userId);

	LRNResponse deleteProxyAssignment(String userId, String proxyUserId);

	LRNResponse getProxyAssignmentAndProxyPermissionUserList(Long siteId);
	
	LRNResponse getProxyUser(String proxyLoginName, Long siteId, String company);
}
