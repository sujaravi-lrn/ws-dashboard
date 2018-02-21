package com.lrn.manager;

import java.util.Map;

import com.lrn.model.CompanyUsersColumn;
import com.lrn.model.CompanyUsersColumnPK;

/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public interface ICompanyUsersColumnManager extends 
			IGenericManager<CompanyUsersColumn, CompanyUsersColumnPK> {

	Map<String, String> getCompanyColumnNameAndDisplayNameMap(Long siteId);

	Map<String, String> getCompanyDisplayNameAndColumnNameMap(Long siteId);

}
