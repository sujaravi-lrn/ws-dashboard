package com.lrn.manager.impl;

import java.util.Map;

import com.lrn.dao.ICompanyUsersColumnDao;
import com.lrn.dao.IGenericDao;
import com.lrn.manager.ICompanyUsersColumnManager;
import com.lrn.model.CompanyUsersColumn;
import com.lrn.model.CompanyUsersColumnPK;


/**
 * @author Suja.Ravi
 * Jun 23, 2016
 */
public class CompanyUsersColumnManager extends
	GenericManager<CompanyUsersColumn, CompanyUsersColumnPK> implements ICompanyUsersColumnManager {

	private ICompanyUsersColumnDao companyUsersColumnDao;
	
	public void setCompanyUsersColumnDao(
			ICompanyUsersColumnDao companyUsersColumnDao) {
		this.companyUsersColumnDao = companyUsersColumnDao;
	}

	public CompanyUsersColumnManager(IGenericDao<CompanyUsersColumn, CompanyUsersColumnPK> iGenericDao) {
		super(iGenericDao);
	}

	@Override
	public Map<String, String> getCompanyColumnNameAndDisplayNameMap(Long siteId) {
		return companyUsersColumnDao.getCompanyColumnNameAndDisplayNameMap(siteId);
	}
	
	@Override
	public Map<String, String> getCompanyDisplayNameAndColumnNameMap(Long siteId) {
		return companyUsersColumnDao.getCompanyDisplayNameAndColumnNameMap(siteId);
	}
}
