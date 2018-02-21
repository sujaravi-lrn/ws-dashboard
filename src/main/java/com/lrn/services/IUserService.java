package com.lrn.services;

import com.lrn.model.LRNResponse;

public interface IUserService {
	
	LRNResponse getCompanyUsersColumn(String Company);
}
