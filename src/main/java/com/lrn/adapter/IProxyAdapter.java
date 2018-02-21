package com.lrn.adapter;

import java.util.List;

import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.ValidationMessageDTO;
import com.lrn.dto.response.ProxyUserDTO;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public interface IProxyAdapter {

	List<AssignedProxyUsersDTO> getProxyAssignmentList(Long siteId, String userId);

	ValidationMessageDTO saveProxyAssignment(String assigneeUserId, String proxyLoginName,
			Long siteId, String oldProxyUserId, String userId);

	ValidationMessageDTO deleteProxyAssignment(String userId, String proxyUserId);

	List<AssignedProxyUsersDTO> getProxyAssignmentAndProxyPermissionUserList(Long siteId);

	ProxyUserDTO getProxyUser(String proxyLoginName, Long siteId, String company);

}
