package com.lrn.dto.response;

import java.io.Serializable;

import com.lrn.dto.ValidationMessageDTO;

public class ProxyUserDTO implements Serializable {

	private static final long serialVersionUID = -2007475485622183802L;

	private String userId;
	private String loginName;
	private String userDisplayName;
	
	private ValidationMessageDTO validationMessageDTO;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public ValidationMessageDTO getValidationMessageDTO() {
		return validationMessageDTO;
	}

	public void setValidationMessageDTO(ValidationMessageDTO validationMessageDTO) {
		this.validationMessageDTO = validationMessageDTO;
	}
}
