package com.lrn.dao;

import java.util.Map;

import com.lrn.manager.IGenericManager;
import com.lrn.model.CompanyUsersColumn;
import com.lrn.model.CompanyUsersColumnPK;

public interface ICompanyUsersColumnDao extends
		IGenericManager<CompanyUsersColumn, CompanyUsersColumnPK> {
	
	public Map<String, String> getCompanyColumnNameAndDisplayNameMap(Long siteId);

	public Map<String, String> getCompanyDisplayNameAndColumnNameMap(Long siteId);
}
