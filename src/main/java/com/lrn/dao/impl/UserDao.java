package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IUserDao;
import com.lrn.dto.UserLabelDTO;
import com.lrn.dto.UserLabelsDTO;
import com.lrn.dto.response.ProxyUserDTO;
import com.lrn.model.User;

/**
 * @author Yuvraj
 */

public class UserDao extends GenericDao<User, String> implements IUserDao {

	public UserDao(Class<User> persistentClass) {
		super(persistentClass);
	}

	@Override
	public ProxyUserDTO findByUsername(String username, String company){

		StringBuilder queryBuilder = new StringBuilder();	
		queryBuilder.append("SELECT u.user_id as user_id, login_name, CONCAT(firstName,CONCAT(' ', lastname)) AS display_name ");
		queryBuilder.append(" FROM lcec.users u");
		queryBuilder.append(" WHERE login_name = ? and company = ? ");
		queryBuilder.append(" AND ACTIVE = 't'");
		
		ArrayList<Object> params = new ArrayList<Object> ();
		params.add(username.toUpperCase());
		params.add(company);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		ProxyUserDTO user = new ProxyUserDTO();
		
        while(itr.hasNext()) {
            Map<String, Object> resultMap =itr.next();
            user.setUserId((String) resultMap.get("user_id"));
            user.setLoginName((String) resultMap.get("login_name"));
            user.setUserDisplayName((String) resultMap.get("display_name"));
        }
        
        return user;
	}
	
	@Override
	public UserLabelsDTO getCompanyUsersColumn(String company) {
		
		StringBuilder queryBuilder = new StringBuilder();			
			queryBuilder.append("select * from lcec.company_users_column where company = ?");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(company);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<UserLabelDTO> userLabelsList = new ArrayList<UserLabelDTO> ();
		
        while(itr.hasNext()) {
            Map<String, Object> resultMap = itr.next();
            UserLabelDTO ul = getUserLabel(resultMap);
            userLabelsList.add(ul);
        }
        UserLabelsDTO dto = new UserLabelsDTO();
        dto.setUserLabelList(userLabelsList);

		return dto;
	} 

	private UserLabelDTO getUserLabel(Map<String, Object> rs) {
		UserLabelDTO dto = new UserLabelDTO();
		
		dto.setColumnName((String)rs.get("column_name"));
		dto.setCompany((String)rs.get("company"));
		dto.setDisplayName((String)rs.get("display_name"));
		
		BigDecimal reportPosition = (BigDecimal)rs.get("report_position");
		if(reportPosition != null)
			dto.setReportPosition(reportPosition.longValue());
		
		BigDecimal searchCriteria = (BigDecimal)rs.get("search_criteria");
		if(searchCriteria != null)
			dto.setSearchCriteria(searchCriteria.longValue());
		
		dto.setSourceOfData((String)rs.get("source_of_data"));
		
		BigDecimal editAllowed = (BigDecimal)rs.get("edit_allowed");
		if(editAllowed != null)
			dto.setEditAllowed(editAllowed.longValue());
		
		BigDecimal privilegeVisibilityLevel = (BigDecimal)rs.get("privilege_visibility_level");
		if(privilegeVisibilityLevel != null)
			dto.setPrivilegeVisibilityLevel(privilegeVisibilityLevel.longValue());
		
		BigDecimal requiredField = (BigDecimal)rs.get("required_field");
		if(requiredField != null)
			dto.setRequiredField(requiredField.longValue());
		
		BigDecimal fieldType = (BigDecimal)rs.get("field_type");
		if(fieldType != null)
			dto.setFieldType(fieldType.longValue());
		
		dto.setInstructionText((String)rs.get("instruction_text"));
		dto.setFieldFormat((String)rs.get("field_format"));
		
		BigDecimal protectedField= (BigDecimal)rs.get("protected_field");
		if(protectedField != null)
			dto.setProtectedField(protectedField.longValue());
		
		return dto;
	}

	@Override
	public String getUserColumnForDashboardConfig(String managerId, String configValue) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT ").append(configValue).append(" as COLUMN_NAME ");
			queryBuilder.append("FROM DIM_USERS ");
			queryBuilder.append("WHERE user_id = ? ");
			queryBuilder.append("and dw_is_active = 1");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(managerId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		String columnValue = null;
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			columnValue = (String) resultMap.get("COLUMN_NAME");
		}
		return columnValue;
	}
	
	@Override
	public Map<String, ArrayList<String>> getDataPrivacyUserColumns(Long siteId, String managerId) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT USER_COLUMN_NAME, VALUE as USER_COLUMN_VALUE ");
		queryBuilder.append("from DATA_FILTER_COLUMN dfc, DATA_FILTER_VALUE dfv ");
		queryBuilder.append("where dfc.ID = dfv.DATA_FILTER_COLUMN_ID ");
		queryBuilder.append("and dfc.SITE_ID = dfv.SITE_ID ");
		queryBuilder.append("and dfc.SITE_ID = ? ");
		queryBuilder.append("and CURRICULUM_ID is null ");
		queryBuilder.append("and dfc.USER_ID = ? ");
	
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			params.add(managerId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		Map<String, ArrayList<String>> dataPrivacyColumnMap = new HashMap<String, ArrayList<String>> ();
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			String columnName = (String) resultMap.get("USER_COLUMN_NAME");
			String columnValue = (String) resultMap.get("USER_COLUMN_VALUE");
			
			ArrayList<String> valueList = dataPrivacyColumnMap.get(columnName);
			if(valueList == null)
				valueList = new ArrayList<String>();
			
			valueList.add(columnValue);
			
			dataPrivacyColumnMap.put(columnName, valueList);
		}
		
		return dataPrivacyColumnMap;
	}
	
	@Override
	public List<String> getDataPrivacyUserColumns(Long siteId, String managerId, String columnName) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT USER_COLUMN_NAME, VALUE as USER_COLUMN_VALUE ");
		queryBuilder.append("from DATA_FILTER_COLUMN dfc, DATA_FILTER_VALUE dfv ");
		queryBuilder.append("where dfc.ID = dfv.DATA_FILTER_COLUMN_ID ");
		queryBuilder.append("and dfc.SITE_ID = dfv.SITE_ID ");
		queryBuilder.append("and dfc.SITE_ID = ? ");
		queryBuilder.append("and CURRICULUM_ID is null ");
		queryBuilder.append("and dfc.USER_ID = ? ");
		queryBuilder.append("and dfc.user_column_name = ?");
	
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			params.add(managerId);
			params.add(columnName);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		List<String> dataPrivacyColumnList = new ArrayList<String> ();
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			String columnValue = (String) resultMap.get("USER_COLUMN_VALUE");
			
			dataPrivacyColumnList.add(columnValue);
		}
		
		return dataPrivacyColumnList;
	}
	
	@Override
	public boolean isDataPrivacySetupForSite(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT CONFIG_VALUE FROM SITE_CONFIGS sc, SITE s ");
		queryBuilder.append(" WHERE sc.COMPANY = s.NAME AND s.ID = ? ");
		queryBuilder.append(" and CONFIG_NAME = 'EnableDataPrivacy'");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			String configValue = (String) resultMap.get("CONFIG_VALUE");
			
			if(configValue != null && configValue.equals("t"))
				return true;
		}
		
		return false;
	}

}
