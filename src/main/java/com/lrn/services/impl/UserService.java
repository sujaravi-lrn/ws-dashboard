package com.lrn.services.impl;

import com.lrn.adapter.IUserAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IUserService;

public class UserService implements IUserService {
	
	private IUserAdapter userAdapter;

	public void setUserAdapter(IUserAdapter userAdapter) {
		this.userAdapter = userAdapter;
	}

	@Override
	public LRNResponse getCompanyUsersColumn(String company) {

		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(userAdapter.getCompanyUsersColumn(company));
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse; 
	}
}
