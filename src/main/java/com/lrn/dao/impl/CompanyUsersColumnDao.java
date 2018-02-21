package com.lrn.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.ICompanyUsersColumnDao;
import com.lrn.dao.IPartnerSiteDao;
import com.lrn.model.CompanyUsersColumn;
import com.lrn.model.CompanyUsersColumnPK;

public class CompanyUsersColumnDao extends
		GenericDao<CompanyUsersColumn, CompanyUsersColumnPK> implements ICompanyUsersColumnDao {
	
	IPartnerSiteDao partnerSiteDao;
	
	public CompanyUsersColumnDao(Class<CompanyUsersColumn> persistentClass) {
		super(persistentClass);
	}

	public void setPartnerSiteDao(IPartnerSiteDao partnerSiteDao) {
		this.partnerSiteDao = partnerSiteDao;
	}
	
	@Override
	public Map<String, String> getCompanyColumnNameAndDisplayNameMap(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select column_name, display_name from lcec.company_users_column where company = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(partnerSiteDao.getPartnerSiteInfo(siteId).getSiteName());
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		Map<String, String> companyColumnNameDisplayNameMap = new HashMap<String, String>();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			companyColumnNameDisplayNameMap.put((String) resultMap.get("column_name"), (String) resultMap.get("display_name"));
		}

		return companyColumnNameDisplayNameMap;
	}

	@Override
	public Map<String, String> getCompanyDisplayNameAndColumnNameMap(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select column_name, display_name from lcec.company_users_column where company = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(partnerSiteDao.getPartnerSiteInfo(siteId).getSiteName());
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		Map<String, String> companyDisplayNameColumnNameMap = new HashMap<String, String>();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			companyDisplayNameColumnNameMap.put((String) resultMap.get("display_name"), (String) resultMap.get("column_name"));
		}

		return companyDisplayNameColumnNameMap;
	}
}