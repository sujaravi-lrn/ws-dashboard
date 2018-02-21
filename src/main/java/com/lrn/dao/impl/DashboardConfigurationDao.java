package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.lrn.dao.IDashboardConfigurationDao;
import com.lrn.dto.DashboardAndConfigurationDTO;
import com.lrn.model.DashboardConfiguration;
import com.lrn.util.DBUtils;
import com.lrn.util.StringUtil;

@Transactional(readOnly = false)
public class DashboardConfigurationDao extends GenericDao<DashboardConfiguration, Long> implements IDashboardConfigurationDao
{

	public DashboardConfigurationDao(Class<DashboardConfiguration> persistentClass) {
		super(persistentClass);
	}


	@Override
	public DashboardAndConfigurationDTO getDashboardConfigurationForSite(Long siteId) {
		DashboardAndConfigurationDTO dashboardConfigDTO = null;
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT d.dashboard_id as dashboard_id, dashboard_name, description, partner_id, site_id, active,");
			queryBuilder.append(" d.created_by as created_by, d.created_on as created_on,");
			queryBuilder.append(" d.updated_by as updated_by, d.updated_on as updated_on,"); 
			queryBuilder.append(" dashboard_configuration_id, config_type_id, config_value");
			queryBuilder.append(" FROM dashboard d, DASHBOARD_CONFIGURATION dc");
			queryBuilder.append(" WHERE dc.dashboard_id = d.dashboard_id");
			queryBuilder.append(" and d.SITE_ID = ?");
			queryBuilder.append(" AND config_type_id = 1");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);

		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			dashboardConfigDTO = new DashboardAndConfigurationDTO();
			dashboardConfigDTO.setDashboardID(DBUtils.getLongFromResultMapObject(resultMap.get("dashboard_id")));
			dashboardConfigDTO.setDashboardName((String) resultMap.get("dashboard_name"));
			dashboardConfigDTO.setDescription((String) resultMap.get("description"));
			dashboardConfigDTO.setPartnerId(DBUtils.getLongFromResultMapObject(resultMap.get("partner_id")));
			dashboardConfigDTO.setSiteId(DBUtils.getLongFromResultMapObject(resultMap.get("site_id")));
			dashboardConfigDTO.setActive(DBUtils.getLongFromResultMapObject(resultMap.get("active")));
			dashboardConfigDTO.setCreatedBy((String) resultMap.get("CREATED_BY"));
			dashboardConfigDTO.setCreatedOn((Date) resultMap.get("created_on"));
			dashboardConfigDTO.setUpdatedBy((String) resultMap.get("updated_by"));
			dashboardConfigDTO.setUpdatedOn((Date) resultMap.get("updated_on"));
			dashboardConfigDTO.setDashboardConfigurationId(DBUtils.getLongFromResultMapObject(resultMap.get("dashboard_configuration_id")));
			dashboardConfigDTO.setConfigTypeId(DBUtils.getLongFromResultMapObject(resultMap.get("config_type_id")));
			dashboardConfigDTO.setConfigValue((String) resultMap.get("config_value"));
		}

		return dashboardConfigDTO;
	}
	
	@Override 
	public Map<String, String> getDashboardHierarchyConfigs(Long dashboardId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select config_type, config_name, config_value");
			queryBuilder.append(" from dashboard d, dashboard_configuration dc, config_type ct");
			queryBuilder.append(" where d.dashboard_id = dc.dashboard_id");
			queryBuilder.append(" and dc.config_type_id = ct.config_type_id");
			queryBuilder.append(" and dc.dashboard_id = ?");
			queryBuilder.append(" and active = 1");
			queryBuilder.append(" and config_type = 'Hierarchy'");
			queryBuilder.append(" AND DC.CONFIG_VALUE IS NOT NULL ");
	
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashboardId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Map<String, String> hierMap = new HashMap<String, String> ();
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			String configName = (String) resultMap.get("config_name");
			String configValue = (String) resultMap.get("config_value");
			hierMap.put(configName, configValue);
		}
		
		return hierMap;
	}
	
	@Override
	public String getSameHierachyColumn(Long dashboardId) {

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select config_value from dashboard_configuration ");
			queryBuilder.append("where dashboard_id = ? and config_type_id = '4'");
		
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashboardId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		
		if (resultSetMapList.size() > 0) {
			Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			List<String> columnDisplayNameList = new ArrayList<String>();
			while (itr.hasNext()) {
				Map<String, Object> resultMap = itr.next();
				columnDisplayNameList.add((String) resultMap.get("CONFIG_VALUE"));
			}

			return columnDisplayNameList.get(0);
		}
		
		return null;
	}
	
	@Override
	public String getSameDataHierarchyConfigForDashboard(Long dashboardId) {
		String configValue = null;
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT DISTINCT DC.CONFIG_VALUE as CONFIG_VALUE ");
			queryBuilder.append("FROM DASHBOARD D, ");
			queryBuilder.append("CONFIG_TYPE CT, ");
			queryBuilder.append("DASHBOARD_CONFIGURATION DC ");
			queryBuilder.append("WHERE  D.DASHBOARD_ID = DC.DASHBOARD_ID ");
			queryBuilder.append("AND CT.CONFIG_TYPE_ID = DC.CONFIG_TYPE_ID ");
			queryBuilder.append("AND CT.CONFIG_TYPE = 'Hierarchy' ");
			queryBuilder.append("AND CT.CONFIG_NAME ='Same_Data_Hierarchy' ");
			queryBuilder.append("AND DC.CONFIG_VALUE IS NOT NULL ");
			queryBuilder.append("AND D.DASHBOARD_ID = ? ");
			queryBuilder.append("AND D.ACTIVE = 1");
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			configValue = (String) resultMap.get("CONFIG_VALUE");
		}
		return configValue;
	}
	
	@Override
	public Map<Long, DashboardConfiguration> getDashboardConfigurationForConfigTypes(Long dashboardId, List<Long> configTypes) {
		
		String configTypesString = StringUtil.getLongListAsString(configTypes);
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dashboard_configuration_id, config_type_id, dashboard_id, config_value,");
			queryBuilder.append(" created_by, created_on, updated_by, updated_on");
			queryBuilder.append(" FROM Dashboard_Configuration");
			queryBuilder.append(" WHERE dashboard_id = ?");
			queryBuilder.append(" AND config_type_id IN (").append(configTypesString).append(")");
		
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		Map<Long, DashboardConfiguration> dcMap = new HashMap<Long, DashboardConfiguration> ();
		while(itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			DashboardConfiguration dc = new DashboardConfiguration();
				dc.setDashboardConfigurationId(((BigDecimal)resultMap.get("dashboard_configuration_id")).longValue());
				dc.setDashboardId(((BigDecimal)resultMap.get("dashboard_id")).longValue());
				Long configTypeId = ((BigDecimal)resultMap.get("config_type_id")).longValue();
				dc.setConfigTypeId(configTypeId);
				dc.setConfigValue((String) resultMap.get("config_value"));
				dc.setCreatedBy((String) resultMap.get("created_by"));
				dc.setCreatedOn((Date) resultMap.get("created_on"));
				dc.setUpdatedBy((String) resultMap.get("updated_by"));
				dc.setUpdatedOn((Date) resultMap.get("updated_on"));
			dcMap.put(configTypeId, dc);
		}
		
		return dcMap;
	}
	
}
