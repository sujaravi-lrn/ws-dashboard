package com.lrn.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.transaction.annotation.Transactional;

import com.lrn.dao.IDashboardDao;
import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.dto.response.DashboardUpdateDateDTO;
import com.lrn.model.Dashboard;
import com.lrn.util.DBUtils;

@Transactional(readOnly = false)
public class DashboardDao extends GenericDao<Dashboard, String> implements IDashboardDao {
	
	public DashboardDao(Class<Dashboard> persistentClass) {
		super(persistentClass);

	}

	@Override
	public DashboardUpdateDateDTO getLastUpdatedDate(Long siteId) {

		DashboardUpdateDateDTO dto = new DashboardUpdateDateDTO();
			dto.setDailyETLUpdateDate(getDailyETLUpdateDate());
			dto.setInitialETLDate(getInitialETLDate(siteId));
			dto.setStatusETLUpdateDate(getStatusETLUpdateDate());
			dto.setUserHierarchyUpdateDate(getUserHierarchyUpdateDate());
			
		return dto;
	}
	
	private String getDailyETLUpdateDate() {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select max(batch_end_time) as LAST_UPDATE_DATE from batch");
		queryBuilder.append(" where isdailyetl_load = 1");
		queryBuilder.append(" and current_status = 'SUCCESS'");

		ArrayList<Object> params = new ArrayList<Object>();
	
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
	
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Date lastUpdateDate = (Date) resultMap.get("LAST_UPDATE_DATE");
			
			SimpleDateFormat dateFormatPT = new SimpleDateFormat("dd MMM yyyy");
			dateFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			SimpleDateFormat timeFormatPT = new SimpleDateFormat("HH:mm:ss");
			timeFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			if(lastUpdateDate != null)
				return (dateFormatPT.format(lastUpdateDate) + " at " + timeFormatPT.format(lastUpdateDate) + " PT");
		}
		
		return null;
	}
	
	private String getInitialETLDate(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select max(batch_end_time) as LAST_UPDATE_DATE from batch");
		queryBuilder.append(" where site_id = ?");
		queryBuilder.append(" and current_status = 'SUCCESS'");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);
	
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
	
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Date lastUpdateDate = (Date) resultMap.get("LAST_UPDATE_DATE");
			
			SimpleDateFormat dateFormatPT = new SimpleDateFormat("dd MMM yyyy");
			dateFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			SimpleDateFormat timeFormatPT = new SimpleDateFormat("HH:mm:ss");
			timeFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			if(lastUpdateDate != null)
				return (dateFormatPT.format(lastUpdateDate) + " at " + timeFormatPT.format(lastUpdateDate) + " PT");
		}
		
		return null;
	}
	
	private String getStatusETLUpdateDate() {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select max(batch_end_time) as LAST_UPDATE_DATE from batch");
		queryBuilder.append(" where isdailyetl_load = 0");
		queryBuilder.append(" and current_status = 'SUCCESS'");

		ArrayList<Object> params = new ArrayList<Object>();
	
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
	
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Date lastUpdateDate = (Date) resultMap.get("LAST_UPDATE_DATE");
			
			SimpleDateFormat dateFormatPT = new SimpleDateFormat("dd MMM yyyy");
			dateFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			SimpleDateFormat timeFormatPT = new SimpleDateFormat("HH:mm:ss");
			timeFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			if(lastUpdateDate != null)
				return (dateFormatPT.format(lastUpdateDate) + " at " + timeFormatPT.format(lastUpdateDate) + " PT");
		}
		
		return null;
	}
	
	private String getUserHierarchyUpdateDate() {
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select max(batch_end_time) as LAST_UPDATE_DATE from batch");
		queryBuilder.append(" where isdailyetl_load = 0");
		queryBuilder.append(" and site_id = 0");
		queryBuilder.append(" and CURRENT_STAGE = 'SUPERVISOR_HIERARCHY'");
		queryBuilder.append(" and current_status = 'SUCCESS'");

		ArrayList<Object> params = new ArrayList<Object>();
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
	
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Date lastUpdateDate = (Date) resultMap.get("LAST_UPDATE_DATE");
			
			SimpleDateFormat dateFormatPT = new SimpleDateFormat("dd MMM yyyy");
			dateFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			SimpleDateFormat timeFormatPT = new SimpleDateFormat("HH:mm:ss");
			timeFormatPT.setTimeZone(TimeZone.getTimeZone("PT"));
			
			if(lastUpdateDate != null)
				return (dateFormatPT.format(lastUpdateDate) + " at " + timeFormatPT.format(lastUpdateDate) + " PT");
		}
		
		return null;
	}
	

	@Override
	public List<Dashboard> getDashboardSettingsForAllSites() {
		
		List<Dashboard> dashboardList = new ArrayList<Dashboard> ();
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT d.dashboard_id as dashboard_id, dashboard_name, description, partner_id, site_id, active,");
			queryBuilder.append(" d.created_by as created_by, d.created_on as created_on,");
			queryBuilder.append(" d.updated_by as updated_by, d.updated_on as updated_on, config_value"); 
			queryBuilder.append(" FROM dashboard d, DASHBOARD_CONFIGURATION dc");
			queryBuilder.append(" WHERE dc.dashboard_id = d.dashboard_id");
			queryBuilder.append(" AND config_type_id = 1");
			
		ArrayList<Object> params = new ArrayList<Object>();

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);

		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			Dashboard dashboard = getDashboardFromResultMap(resultMap);
			//config_value is what is referred to in the front end for show dashboard ON/OFF
			dashboard.setActive(((String) resultMap.get("config_value")).equals("1") ? 1L : 0L);
			dashboardList.add(dashboard);
		}

		logger.debug("Loading DashboardSettingsForAllSites...");
		
		return dashboardList;
	}
	
	@Override
	public Dashboard getDashboardForSite(Long siteId) {
		Dashboard dashboard = null;
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dashboard_id, dashboard_name, description, partner_id, site_id, active,");
			queryBuilder.append(" created_by, created_on, updated_by, updated_on");
			queryBuilder.append(" FROM dashboard d");
			queryBuilder.append(" where d.SITE_ID = ?");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);

		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			dashboard = getDashboardFromResultMap(resultMap);
		}

		logger.debug("Loading DashboardSettingsForSite..." + siteId);
		
		return dashboard;
	}

	private Dashboard getDashboardFromResultMap(Map<String, Object> resultMap) {
		Dashboard dashboard = new Dashboard();

		dashboard.setDashboardId(DBUtils.getLongFromResultMapObject(resultMap.get("DASHBOARD_ID")));
		dashboard.setDashboardName((String) resultMap.get("dashboard_name"));
		dashboard.setDescription((String) resultMap.get("description"));
		dashboard.setPartnerID(DBUtils.getLongFromResultMapObject(resultMap.get("partner_id")));
		dashboard.setSiteId(DBUtils.getLongFromResultMapObject(resultMap.get("site_id")));
		dashboard.setActive(DBUtils.getLongFromResultMapObject(resultMap.get("active")));
		dashboard.setCreatedBy((String) resultMap.get("CREATED_BY"));
		dashboard.setCreatedOn((Date) resultMap.get("created_on"));
		dashboard.setUpdatedBy((String) resultMap.get("updated_by"));
		dashboard.setUpdatedOn((Date) resultMap.get("updated_on"));
		
		return dashboard;
	}


	@Override
	public List<AssignedProxyUsersDTO> getAssignedUserIdsforProxyUsers(Long dashboardId, String proxyUserId) {
		
		StringBuilder queryBuilder = new StringBuilder();
		
			queryBuilder.append("select * from  ");
			queryBuilder.append(" ( ");
			queryBuilder.append("select perm.object,u.user_Id as user_Id_dashboard,u.login_name as user_login_name, u.firstname||' '|| u.lastname ||' (' || u.login_name ||')' user_display_name, ");
			queryBuilder.append(" proxy_user.user_id as proxy_user_id, proxy_user.login_name as proxy_login_name, proxy_user.firstname||' '||proxy_user.lastname proxy_display_name ");
			queryBuilder.append(" from permissions@DL_LCECMV_LCEC perm, site s , lcec.users u, dashboard_proxy dp, lcec.users proxy_user, dashboard db ");
			queryBuilder.append(" where db.dashboard_id = ? ");
			queryBuilder.append(" and perm.object_type = 'User' ");
			queryBuilder.append(" and perm.group_id = s.name||'CatalystDashboard1' ");
			queryBuilder.append(" and perm.object = u.user_id ");
			queryBuilder.append(" and perm.object = dp.user_id (+) ");
			queryBuilder.append(" and u.active = 't' ");
			queryBuilder.append(" and db.site_id = s.id ");
			queryBuilder.append(" and dp.proxy_user_id = ? ");
			queryBuilder.append(" and u.user_id != proxy_user.user_id ");
			queryBuilder.append(" and nvl(proxy_user.active,'t') = 't'  ");
			queryBuilder.append(" and dp.proxy_user_id = proxy_user.user_id (+) ");	
			
		queryBuilder.append(" union ");
		
			queryBuilder.append(" select ");
			queryBuilder.append(" NULL,u.user_Id as user_Id_dashboard,u.login_name as user_login_name, u.firstname||' '|| u.lastname ||' (' || u.login_name ||')' user_display_name, null ,null,null ");
			queryBuilder.append(" from  lcec.users u, site s, dashboard db ");
			queryBuilder.append(" where  db.dashboard_id = ? ");
			queryBuilder.append(" and db.site_id = s.id ");
			queryBuilder.append("  and company=s.name ");
			queryBuilder.append("  and user_id= ? ");		
			queryBuilder.append("  ) ");	
			queryBuilder.append(" order by upper(user_display_name) asc ");
			
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(dashboardId);
		params.add(proxyUserId);
		params.add(dashboardId);
		params.add(proxyUserId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		List<AssignedProxyUsersDTO> assignedProxyUsersDTOList = new ArrayList<AssignedProxyUsersDTO> ();
		
		long sequence = 1L;
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			AssignedProxyUsersDTO dto = new AssignedProxyUsersDTO();
				dto.setSequenceNumber(sequence++);
				dto.setUserId((String) resultMap.get("user_Id_dashboard"));
				dto.setUserLoginName((String) resultMap.get("user_login_name"));
				dto.setUserDisplayName((String) resultMap.get("user_display_name"));
				dto.setProxyUserId((String) resultMap.get("proxy_user_id"));
				dto.setProxyLoginName((String) resultMap.get("proxy_login_name"));
				dto.setProxyDisplayName((String) resultMap.get("proxy_display_name"));
				
			assignedProxyUsersDTOList.add(dto);
		}

		return assignedProxyUsersDTOList;
	}

}
