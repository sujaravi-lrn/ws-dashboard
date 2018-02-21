package com.lrn.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.lrn.dao.IProxyDao;
import com.lrn.dto.AssignedProxyUsersDTO;
import com.lrn.model.DashboardProxy;
import com.lrn.model.DashboardProxyPK;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ProxyDao extends GenericDao<DashboardProxy, DashboardProxyPK> implements IProxyDao {

	public ProxyDao(Class<DashboardProxy> persistentClass) {
		super(persistentClass);
	}

	@Override
	public DashboardProxy getDashboardProxyByUserIdProxyUserId(String proxyUserId, String userId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT proxy_user_id, user_id, create_user, update_user, create_date, update_date");
			queryBuilder.append(" FROM dashboard_proxy");
			queryBuilder.append(" WHERE proxy_user_id = ?");
			queryBuilder.append(" AND user_id = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(proxyUserId);
			params.add(userId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			
			DashboardProxy proxy = new DashboardProxy();
			DashboardProxyPK pk = new DashboardProxyPK();
				pk.setProxyUserId((String) resultMap.get("proxy_user_id"));
				pk.setUserId((String) resultMap.get("user_id"));

				proxy.setDashboardProxyPK(pk);
				proxy.setCreateUser((String) resultMap.get("create_user"));
				proxy.setUpdateUser((String) resultMap.get("update_user"));
				proxy.setCreateDate((Date) resultMap.get("create_date"));
				proxy.setUpdateDate((Date) resultMap.get("update_date"));
				
			return proxy;
		}
		
		return null;
	}
	
	@Override
	public DashboardProxy saveDashboardProxy(DashboardProxy proxy, String oldProxyUserId) {
		
		StringBuffer queryBuilder = new StringBuffer();
		
		if(null == oldProxyUserId || "".equals(oldProxyUserId)) {
			queryBuilder.append("insert into DASHBOARD_PROXY(proxy_user_id, user_id, create_user, update_user, create_date, update_date) values");
			queryBuilder.append("(?, ?, ?, ?, ?, ?)");

			Query query = getSessionFactory().getCurrentSession().createSQLQuery(queryBuilder.toString());
				query.setParameter(0, proxy.getDashboardProxyPK().getProxyUserId());
				query.setParameter(1, proxy.getDashboardProxyPK().getUserId());
				query.setParameter(2, proxy.getCreateUser());
				query.setParameter(3, proxy.getUpdateUser());
				query.setParameter(4, proxy.getCreateDate());
				query.setParameter(5, proxy.getUpdateDate());
			
			query.executeUpdate();
			
		} else {
			
			queryBuilder.append("update DASHBOARD_PROXY SET PROXY_USER_ID = ?, update_user = ?, update_date = systimestamp ");
			queryBuilder.append(" WHERE proxy_user_id = ? AND user_id = ?");
			
			Query query = getSessionFactory().getCurrentSession().createSQLQuery(queryBuilder.toString());
			query.setParameter(0, proxy.getDashboardProxyPK().getProxyUserId());
			query.setParameter(1, proxy.getUpdateUser());
			query.setParameter(2, oldProxyUserId);
			query.setParameter(3, proxy.getDashboardProxyPK().getUserId());
		
			query.executeUpdate();
			
		}

		return proxy;
	}

	@Override
	public boolean deleteProxyAssignment(String userId, String proxyUserId) {
		
		StringBuffer queryBuilder = new StringBuffer();
			queryBuilder.append("delete from DASHBOARD_PROXY WHERE proxy_user_id = ? AND user_id = ?");
			
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(queryBuilder.toString());
			query.setParameter(0, proxyUserId);
			query.setParameter(1, userId);
		
		int deleted = query.executeUpdate();
		
		return deleted > 0 ? true : false;
	}
	
	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentList(Long siteId, String userId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT u.user_id as user_id, login_name, CONCAT(firstName,CONCAT(' ', lastname)) AS display_name ");
			queryBuilder.append(" FROM lcec.users u, DASHBOARD_PROXY p");
			queryBuilder.append(" WHERE u.user_id = p.PROXY_USER_ID");
			queryBuilder.append(" AND p.user_id = ?");
			queryBuilder.append(" AND ACTIVE = 't'");
			queryBuilder.append(" order by upper(display_name) asc");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(userId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		List<AssignedProxyUsersDTO> assignedProxyUsersDTOList = new ArrayList<AssignedProxyUsersDTO> ();
		long sequence = 1L;
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			
			AssignedProxyUsersDTO dto = new AssignedProxyUsersDTO();
				dto.setProxyUserId((String) resultMap.get("user_id"));
				dto.setProxyLoginName((String) resultMap.get("login_name"));
				dto.setProxyDisplayName((String) resultMap.get("display_name"));
				dto.setSequenceNumber(sequence++);
						
			assignedProxyUsersDTOList.add(dto);
		}
		
		return assignedProxyUsersDTOList;
	}

	@Override
	public String isValidLoginName(String proxyLoginName, Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT user_id FROM lcec.users u, site s");
			queryBuilder.append(" WHERE u.company = s.NAME");
			queryBuilder.append(" AND login_name =  UPPER(?)");
			queryBuilder.append(" AND s.ID = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(proxyLoginName);
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			return ((String) resultMap.get("user_id"));
		}
		
		return null;
	}

	@Override
	public String isUserActive(String proxyLoginName, Long siteId) {
	
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT user_id FROM lcec.users u, site s");
			queryBuilder.append(" WHERE u.company = s.NAME");
			queryBuilder.append(" AND login_name =  UPPER(?)");
			queryBuilder.append(" AND s.ID = ?");
			queryBuilder.append(" and u.ACTIVE = 't' ");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(proxyLoginName);
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			return ((String) resultMap.get("user_id"));
		}
		
		return null;
	}

	@Override
	public String isProxyAlreadyAssigned(String assigneeUserId, String proxyLoginName, Long siteId) {

		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT p.user_id as user_id");
			queryBuilder.append(" FROM lcec.users u, DASHBOARD_PROXY p");
			queryBuilder.append(" WHERE u.user_id = p.PROXY_USER_ID");
			queryBuilder.append(" AND login_name =  UPPER(?)");
			queryBuilder.append(" and p.user_id = ?");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(proxyLoginName);
			params.add(assigneeUserId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			return ((String) resultMap.get("user_id"));
		}
		
		return null;
	}

	@Override
	public List<AssignedProxyUsersDTO> getProxyAssignmentAndProxyPermissionUserList(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("select perm.object,u.user_Id as user_Id_dashboard,u.login_name as user_login_name, u.firstname||' '||u.lastname user_display_name, ");
		queryBuilder.append(" proxy_user.user_id as proxy_user_id, proxy_user.login_name as proxy_login_name, proxy_user.firstname||' '||proxy_user.lastname proxy_display_name ");
		queryBuilder.append(" from permissions@DL_LCECMV_LCEC perm, site s , lcec.users u, dashboard_proxy dp, lcec.users proxy_user ");
		queryBuilder.append(" where s.id = ? ");
		queryBuilder.append(" and perm.object_type = 'User' ");
		queryBuilder.append(" and perm.group_id = s.name||'CatalystDashboard1' ");
		queryBuilder.append(" and perm.object = u.user_id ");
		queryBuilder.append(" and perm.object = dp.user_id (+) ");
		queryBuilder.append(" and u.active = 't' ");
		queryBuilder.append(" and nvl(proxy_user.active,'t') = 't'  ");
		queryBuilder.append(" and s.name = u.company");
		queryBuilder.append(" and dp.proxy_user_id = proxy_user.user_id (+) ");
		queryBuilder.append(" order by upper(user_display_name) asc ");
		
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);
		
		List<Map<String, Object>> resultSetMapList = queryForList(
				queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
	 
		List<AssignedProxyUsersDTO> assignedProxyUsersDTOList = new ArrayList<AssignedProxyUsersDTO>();
		
		long sequence = 1l;
		
		while (itr.hasNext()) {	
			
			Map<String, Object> resultMap = itr.next();
			AssignedProxyUsersDTO assignedProxyUsersDTO = new AssignedProxyUsersDTO();
			
			assignedProxyUsersDTO.setUserId((String) resultMap.get("user_Id_dashboard"));
			assignedProxyUsersDTO.setUserLoginName((String) resultMap.get("user_login_name"));		
			assignedProxyUsersDTO.setUserDisplayName((String) resultMap.get("user_display_name"));
			
			assignedProxyUsersDTO.setProxyUserId((String) resultMap.get("proxy_user_id"));
			assignedProxyUsersDTO.setProxyLoginName((String) resultMap.get("proxy_login_name"));
			assignedProxyUsersDTO.setProxyDisplayName((String) resultMap.get("proxy_display_name"));
			
			assignedProxyUsersDTO.setSequenceNumber(sequence++);
			assignedProxyUsersDTOList.add(assignedProxyUsersDTO);
		}
		
		return assignedProxyUsersDTOList;
	}


}
