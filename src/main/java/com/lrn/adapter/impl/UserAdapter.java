package com.lrn.adapter.impl;

import com.lrn.adapter.IUserAdapter;
import com.lrn.dto.UserLabelsDTO;
import com.lrn.manager.IUserManager;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public class UserAdapter implements IUserAdapter {

	private IUserManager userManager;
	
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public UserLabelsDTO getCompanyUsersColumn(String company) {
		return userManager.getCompanyUsersColumn(company);
	}
}
