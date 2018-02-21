package com.lrn.adapter.impl;

import java.util.Date;
import java.util.List;

import com.lrn.adapter.IProxyAdapter;
import com.lrn.constants.ValidationMessages.ValidationMessagesEnum;
import com.lrn.customException.DashBoardIdIsBlankException;
import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.ValidationMessageDTO;
import com.lrn.dto.response.ProxyUserDTO;
import com.lrn.manager.IProxyManager;
import com.lrn.manager.IUserManager;
import com.lrn.model.DashboardProxy;
import com.lrn.model.DashboardProxyPK;
import com.lrn.util.ApplicationConstant;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ProxyAdapter implements IProxyAdapter {

	private IProxyManager proxyManager;
	private IUserManager userManager;
	
	public void setProxyManager(IProxyManager proxyManager) {
		this.proxyManager = proxyManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentList(Long siteId, String userId) {
		if(userId==null)
			throw new DashBoardIdIsBlankException(ApplicationConstant.USER_ID_NULL_ON_REQUEST);
		
		return proxyManager.getProxyAssignmentList(siteId, userId);
	}

	@Override
	public ProxyUserDTO getProxyUser(String proxyLoginName, Long siteId, String company) {
		
		ValidationMessageDTO validationMessageDTO = new ValidationMessageDTO();
		
		String proxyUserId = proxyManager.isValidLoginName(proxyLoginName, siteId);
		
		if(proxyUserId == null) {
			validationMessageDTO.setCode(ValidationMessagesEnum.USER_IS_NOT_FOUND.getCode());
			validationMessageDTO.setMessage(ValidationMessagesEnum.USER_IS_NOT_FOUND.getMessage());
			
		} else {
		
			proxyUserId = proxyManager.isUserActive(proxyLoginName, siteId);
			if(proxyUserId == null) {
				validationMessageDTO.setCode(ValidationMessagesEnum.USER_IS_INACTIVE.getCode());
				validationMessageDTO.setMessage(ValidationMessagesEnum.USER_IS_INACTIVE.getMessage());
			}
		}
		
		ProxyUserDTO dto = userManager.findByUsername(proxyLoginName, company);
		dto.setValidationMessageDTO(validationMessageDTO);
		
		return dto;
	}
	
	@Override
	public ValidationMessageDTO saveProxyAssignment(String assigneeUserId,
			String proxyLoginName, Long siteId, String oldProxyUserId, String userId) {
		
		ValidationMessageDTO validationMessageDTO = new ValidationMessageDTO();
		
		String proxyUserId = proxyManager.isValidLoginName(proxyLoginName, siteId);
		
		if(proxyUserId == null) {
			validationMessageDTO.setCode(ValidationMessagesEnum.USER_IS_NOT_FOUND.getCode());
			validationMessageDTO.setMessage(ValidationMessagesEnum.USER_IS_NOT_FOUND.getMessage());
			return validationMessageDTO;
		}
		
		proxyUserId = proxyManager.isUserActive(proxyLoginName, siteId);
		if(proxyUserId == null) {
			validationMessageDTO.setCode(ValidationMessagesEnum.USER_IS_INACTIVE.getCode());
			validationMessageDTO.setMessage(ValidationMessagesEnum.USER_IS_INACTIVE.getMessage());
			return validationMessageDTO;
		}
		
		if(proxyManager.isProxyAlreadyAssigned(assigneeUserId, proxyLoginName, siteId) != null) {
			validationMessageDTO.setCode(ValidationMessagesEnum.PROXY_ALREADY_ASSIGNED.getCode());
			validationMessageDTO.setMessage(ValidationMessagesEnum.PROXY_ALREADY_ASSIGNED.getMessage());
			return validationMessageDTO;
		}
		
		DashboardProxy proxy = new DashboardProxy(); 

		DashboardProxyPK proxyPK = new DashboardProxyPK();
			proxyPK.setProxyUserId(proxyUserId);
			proxyPK.setUserId(assigneeUserId);
		proxy =	new DashboardProxy();
		
		proxy.setDashboardProxyPK(proxyPK);
		proxy.setCreateDate(new Date());
		proxy.setCreateUser(assigneeUserId);
		proxy.setUpdateDate(new Date());
		proxy.setUpdateUser(assigneeUserId);
		
		proxyManager.saveDashboardProxy(proxy, oldProxyUserId);
		
		validationMessageDTO.setCode(ValidationMessagesEnum.USER_SAVED.getCode());
		validationMessageDTO.setMessage(ValidationMessagesEnum.USER_SAVED.getMessage());
		
		return validationMessageDTO;

	}

	@Override
	public ValidationMessageDTO deleteProxyAssignment(String userId, String proxyUserId) {

		boolean deleted = proxyManager.deleteProxyAssignment(userId, proxyUserId);
		
		ValidationMessageDTO validationMessageDTO = new ValidationMessageDTO();
		validationMessageDTO.setCode(ValidationMessagesEnum.USER_DELETED.getCode());
		validationMessageDTO.setMessage(ValidationMessagesEnum.USER_DELETED.getMessage());
		return validationMessageDTO;
	}

	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentAndProxyPermissionUserList(Long siteId) {
		
		return proxyManager.getProxyAssignmentAndProxyPermissionUserList(siteId);
	}


}
