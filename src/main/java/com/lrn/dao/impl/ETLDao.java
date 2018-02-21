package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IETLDao;
import com.lrn.dto.FirstTimeETLDTO;
import com.lrn.model.Dashboard;
import com.lrn.model.ETL;

public class ETLDao extends GenericDao<ETL, Long> implements IETLDao {

	public ETLDao(Class<ETL> persistentClass) {
		super(persistentClass);
	}

	@Override
	public List<FirstTimeETLDTO> getETLDeatils(Long siteId) {
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT BATCH_STATUS ");
			queryBuilder.append("from ");
			queryBuilder.append("FIRST_TIME_ETL_LOAD ");
			queryBuilder.append("WHERE ");
			queryBuilder.append("SITE_ID=? ");
			
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		List<FirstTimeETLDTO> firstTimeETLDTOList = new ArrayList<FirstTimeETLDTO>();
		FirstTimeETLDTO dto = new FirstTimeETLDTO();
		
		if (resultSetMapList.size() > 0) {
			Iterator<Map<String, Object>> itr = resultSetMapList.iterator();

			while (itr.hasNext()) {
				Map<String, Object> resultMap = itr.next();
				dto = fetchingFirstTimeETValues(resultMap);
				if (dto != null) {
					if (dto.getBatchStatus().equalsIgnoreCase("inprocess"))
						;
					{
						dto.setBatchStatus("INPROCESS");
					}
					if (dto.getBatchStatus().equalsIgnoreCase("Complete"))
						;
					{
						dto.setBatchStatus("COMPLETE");
					}
					firstTimeETLDTOList.add(dto);

				}
			}
		} else {
			/*Dashboard getDashBoardId = dashboardDao
					.getDashboardForSite(siteId);*/
			Dashboard getDashBoardId =getDashboardForSite(siteId);
			
			String configTypeIdConfiguration= getConfiguredInfoForETL(getDashBoardId
					.getDashboardId());
			if (configTypeIdConfiguration != null) {
				dto.setDashBoardConfigured("Y");
				//dto.setFirstTimeETLDTO(configTypeIdConfiguration);

			} else {
				dto.setDashBoardConfigured("N");
				//dto.setFirstTimeETLDTO(configTypeIdConfiguration);
			}
			String campaignConfigurationInfoForETL = getCampaignConfigurationInfoForETL(getDashBoardId
					.getDashboardId());
			if (campaignConfigurationInfoForETL != null) {
				dto.setCampaignConfigured("Y");
				
			} else {
				dto.setCampaignConfigured("N");
				
			}
			String GroupByConfigured = getGroupByConfigurationInfoForETL(getDashBoardId
					.getDashboardId());
			if (GroupByConfigured != null) {
				dto.setGroupByConfigured("Y");
				
			} else {
				dto.setGroupByConfigured("N");
				
			}
			
			firstTimeETLDTOList.add(dto);
		}

		return firstTimeETLDTOList;

	}

	private FirstTimeETLDTO fetchingFirstTimeETValues(Map<String, Object> resultMap) {
		FirstTimeETLDTO dto = new FirstTimeETLDTO();
		dto.setBatchStatus((String) resultMap.get("BATCH_STATUS"));
		/*
		 * dto.setSiteId(((BigDecimal) resultMap.get("SITE_ID")).longValue());
		 * dto.setBatchId(((BigDecimal) resultMap.get("BATCH_ID")).longValue());
		 */
		return dto;
	}

	
	@Override 
	public Dashboard getDashboardForSite(Long siteId) {
		
		Dashboard dashboard = null; 
		
		StringBuilder queryBuilder = new StringBuilder();
	    	queryBuilder.append("select d.DASHBOARD_ID from dashboard d where d.SITE_ID=?");
		  
		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);
		  
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		  
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator(); 
		while(itr.hasNext()) { 
			Map<String, Object> resultMap = itr.next();
			dashboard = getDashboardFromResultMap(resultMap); 
		}
		return dashboard; 
	}
	  
	private Dashboard getDashboardFromResultMap(Map<String, Object> resultMap) {
		
		Dashboard dashboard = new Dashboard();
		dashboard.setDashboardId(((BigDecimal) resultMap.get("DASHBOARD_ID")) .longValue());
		return dashboard; 
	}
	 

	private String getConfiguredInfoForETL(Long dashBoardId) {

		FirstTimeETLDTO FirstTimeETLDTO = new FirstTimeETLDTO();
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select * from dashboard_configuration where CONFIG_TYPE_ID=2 and DASHBOARD_ID=?");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashBoardId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		
        if(resultSetMapList!=null) {
       
			Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			while (itr.hasNext()) {
				Map<String, Object> resultMap = itr.next();
				FirstTimeETLDTO = getDashboardConfiguration(resultMap);
			}

			return "StringValue";
        } else {
        	return null;
        }
	}

	private FirstTimeETLDTO getDashboardConfiguration(Map<String, Object> resultMap) {
		
		FirstTimeETLDTO FirstTimeETLDTO = new FirstTimeETLDTO();
		Long DashboardConfigurationID = (((BigDecimal) resultMap.get("CONFIG_TYPE_ID")).longValue());
		if (DashboardConfigurationID != null) 
			FirstTimeETLDTO.setDashBoardConfigured("Y");
		return FirstTimeETLDTO;
	}

	private String getCampaignConfigurationInfoForETL(Long dashBoardId) {
		
		FirstTimeETLDTO campaignConfigurationDTO = new FirstTimeETLDTO();
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select COLUMN_NAME from dashboard_selection_criteria where clause_name='where' and column_name='CURRICULUM_ID' and dashboard_id=?");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashBoardId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		
		if(resultSetMapList!=null) {
			Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			while (itr.hasNext()) {
				Map<String, Object> resultMap = itr.next();
				campaignConfigurationDTO = getCampaignConfigurationDTO(resultMap);
			}

			return "StringValue";
		} else {
			return null;
		}
	}
	
	private FirstTimeETLDTO getCampaignConfigurationDTO(Map<String, Object> resultMap) {
		FirstTimeETLDTO FirstTimeETLDTO = new FirstTimeETLDTO();

		FirstTimeETLDTO.setCampaignConfigured((String) resultMap.get("COLUMN_NAME"));
		return FirstTimeETLDTO;
	}

	private String getGroupByConfigurationInfoForETL(Long dashBoardId) {
		
		FirstTimeETLDTO campaignGroupByConfigurationDTO = new FirstTimeETLDTO();
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select COLUMN_NAME from dashboard_selection_criteria where clause_name='Group By'  and dashboard_id=?");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(dashBoardId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		if(resultSetMapList!=null) {
			Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
			while (itr.hasNext()) {
				Map<String, Object> resultMap = itr.next();
				campaignGroupByConfigurationDTO = getGroupByConfigurationDTO(resultMap);
			}
			return "StringValue";
		} else {
			return null;
		}
	}
	
	private FirstTimeETLDTO getGroupByConfigurationDTO(Map<String, Object> resultMap) {

		FirstTimeETLDTO groupByConfigurationDTO = new FirstTimeETLDTO();
		groupByConfigurationDTO.setGroupByConfigured((String) resultMap.get("COLUMN_NAME"));
		return groupByConfigurationDTO;
	}

	/**
	 * Below Method for runing the ETL
	 * */
	@Override
	public void runETL(Long siteId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select from FIRST_TIME_ETL_LOAD where SITE_ID=?");

		ArrayList<Object> params = new ArrayList<Object>();
			params.add(siteId);

		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
	}
}
