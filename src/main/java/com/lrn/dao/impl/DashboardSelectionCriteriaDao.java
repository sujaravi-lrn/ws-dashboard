/**
 * 
 */
package com.lrn.dao.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.lrn.dao.ICompanyUsersColumnDao;
import com.lrn.dao.IDashboardConfigurationDao;
import com.lrn.dao.IDashboardSelectionCriteriaDao;
import com.lrn.dao.IPartnerSiteDao;
import com.lrn.model.DashboardSelectionCriteria;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

@Transactional(readOnly = false)
public class DashboardSelectionCriteriaDao extends GenericDao<DashboardSelectionCriteria, Long> implements IDashboardSelectionCriteriaDao {

	IPartnerSiteDao partnerSiteDao;
	ICompanyUsersColumnDao companyUsersColumnDao;
	IDashboardConfigurationDao dashboardConfigurationDao;
	
	public void setPartnerSiteDao(IPartnerSiteDao partnerSiteDao) {
		this.partnerSiteDao = partnerSiteDao;
	}

	public void setCompanyUsersColumnDao(
			ICompanyUsersColumnDao companyUsersColumnDao) {
		this.companyUsersColumnDao = companyUsersColumnDao;
	}
	
	public void setDashboardConfigurationDao(
			IDashboardConfigurationDao dashboardConfigurationDao) {
		this.dashboardConfigurationDao = dashboardConfigurationDao;
	}

	public DashboardSelectionCriteriaDao(Class<DashboardSelectionCriteria> persistentClass) {
		super(persistentClass);
	}

	@Override
	public void deleteByDashboardSelectionCriteriaList(List<DashboardSelectionCriteria> dcsListToDelete) {
		
		this.getHibernateTemplate().deleteAll(dcsListToDelete);
	}

	@Override
	public List<DashboardSelectionCriteria> getDashboardSelectionCriteriaForFilters(Long dashboardId, String clauseName, 
			String columnName, Long configTypeId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dash_selection_criteria_id, dashboard_id, config_type_id, clause_name, selection_seq,");
			queryBuilder.append(" column_name, filter_operator, condition_operator, selection_value, type_of_value,");
			queryBuilder.append(" active, created_by, created_on, updated_by, updated_on");
			queryBuilder.append(" FROM DASHBOARD_SELECTION_CRITERIA");
			queryBuilder.append(" WHERE DASHBOARD_ID = ?");
			if(clauseName != null && !clauseName.isEmpty())
				queryBuilder.append(" AND clause_name = ?");
			if(columnName != null && !columnName.isEmpty())
				queryBuilder.append(" AND column_name = ?");
			if(configTypeId != null)
				queryBuilder.append(" AND config_type_id = ?");
			
		List<DashboardSelectionCriteria> list = new ArrayList<DashboardSelectionCriteria> ();
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			if(clauseName != null && !clauseName.isEmpty())
				params.add(clauseName);
			if(columnName != null && !columnName.isEmpty())
				params.add(columnName);
			if(configTypeId != null)
				params.add(configTypeId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
			dsc.setDashSelectionCriteriaId(DBUtils.getLongFromResultMapObject(resultMap.get("dash_selection_criteria_id")));
			dsc.setDashboardId(DBUtils.getLongFromResultMapObject(resultMap.get("dashboard_id")));
			if(resultMap.get("config_type_id") != null)
				dsc.setConfigTypeId(DBUtils.getLongFromResultMapObject(resultMap.get("config_type_id")));
			dsc.setClauseName((String) resultMap.get("clause_name"));
			if(resultMap.get("selection_seq") != null)
				dsc.setSelectionSeq(((BigDecimal) resultMap.get("selection_seq")).toString());
			dsc.setColumnName((String) resultMap.get("column_name"));
			dsc.setFilterOperator((String) resultMap.get("filter_operator"));
			dsc.setConditionOperator((String) resultMap.get("condition_operator"));
			dsc.setSelectionValue((String) resultMap.get("selection_value"));
			dsc.setTypeOfValue((String) resultMap.get("type_of_value"));
			if(resultMap.get("active") != null)
				dsc.setActive(DBUtils.getLongFromResultMapObject(resultMap.get("active")));
			dsc.setCreatedBy((String) resultMap.get("created_by"));
			dsc.setCreatedOn((Date) resultMap.get("created_on"));
			dsc.setUpdatedBy((String) resultMap.get("updated_by"));
			dsc.setUpdatedOn((Date) resultMap.get("updated_on"));
			list.add(dsc);
		}
		
		return list;
	}

	@Override
	public Map<String, Map<String, Long>> getIncludedColumnsNameAndColumnDisplayNameAndSequenceByConfigTypeId(
			Long dashBoardId, Long configTypeId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT selection_seq, column_name, selection_value from DASHBOARD_SELECTION_CRITERIA ");
			queryBuilder.append(" WHERE dashboard_id = ?");
			queryBuilder.append(" AND config_type_id = ?");
		
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashBoardId);
			params.add(configTypeId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		Map<String, Map<String, Long>> columnNameANDColumnDisplayNameAndSequenceMap = new HashMap<String, Map<String, Long>>();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Map<String, Long> columnDisplayNameANDSequenceMap = new HashMap<String, Long>();
			String value = (String) resultMap.get("selection_value");
			columnDisplayNameANDSequenceMap.put(fixColumn(value), DBUtils.getLongFromResultMapObject(resultMap.get("selection_seq")));
			columnNameANDColumnDisplayNameAndSequenceMap.put((String) resultMap.get("column_name"), columnDisplayNameANDSequenceMap);
		}

		return columnNameANDColumnDisplayNameAndSequenceMap;
	}
	
	@Override 
	public List<DashboardSelectionCriteria> getDashboardSelectionCriteriaByClauseName(Long dashboardId, String clauseName) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dash_selection_criteria_id, dashboard_id, config_type_id, clause_name, selection_seq,");
			queryBuilder.append(" column_name, filter_operator, condition_operator, selection_value, type_of_value,");
			queryBuilder.append(" active, created_by, created_on, updated_by, updated_on");
			queryBuilder.append(" FROM DASHBOARD_SELECTION_CRITERIA");
			queryBuilder.append(" WHERE dashboard_id = ?");
			queryBuilder.append(" AND clause_name = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashboardId);
			params.add(clauseName);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		List<DashboardSelectionCriteria> dscList = new ArrayList<DashboardSelectionCriteria> ();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			DashboardSelectionCriteria dsc = new DashboardSelectionCriteria();
				dsc.setDashSelectionCriteriaId(DBUtils.getLongFromResultMapObject(resultMap.get("dash_selection_criteria_id")));
				dsc.setDashboardId(DBUtils.getLongFromResultMapObject(resultMap.get("dashboard_id")));
				if(resultMap.get("config_type_id") != null)
					dsc.setConfigTypeId(DBUtils.getLongFromResultMapObject(resultMap.get("config_type_id")));
				dsc.setClauseName((String) resultMap.get("clause_name"));
				if(resultMap.get("selection_seq") != null)
					dsc.setSelectionSeq(resultMap.get("selection_seq").toString());
				dsc.setColumnName((String) resultMap.get("column_name"));
				dsc.setFilterOperator((String) resultMap.get("filter_operator"));
				dsc.setConditionOperator((String) resultMap.get("condition_operator"));
				dsc.setSelectionValue((String) resultMap.get("selection_value"));
				dsc.setTypeOfValue((String) resultMap.get("type_of_value"));
				if(resultMap.get("active") != null)
					dsc.setActive(DBUtils.getLongFromResultMapObject(resultMap.get("active")));
				dsc.setCreatedBy((String) resultMap.get("created_by"));
				dsc.setCreatedOn((Date) resultMap.get("created_on"));
				dsc.setUpdatedBy((String) resultMap.get("updated_by"));
				dsc.setUpdatedOn((Date) resultMap.get("updated_on"));
				
			dscList.add(dsc);
		}
		
		return dscList;
	}
	
	private String fixColumn(String column) {
		try {
			column = StringUtil.replaceString(column, "@@@", "/");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return column;
	}
}
