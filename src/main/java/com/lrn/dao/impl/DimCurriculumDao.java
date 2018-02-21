/**
 * 
 */
package com.lrn.dao.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IDimCurriculumDao;
import com.lrn.dto.CampaignSelectionDTO;
import com.lrn.dto.DisplayDimCurriculumDTO;
import com.lrn.enumType.CampaignStatus.CampaignStatusEnum;
import com.lrn.enumType.CampaignStatus.Status;
import com.lrn.model.DimCurriculum;
import com.lrn.util.DBUtils;


public class DimCurriculumDao extends GenericDao<DimCurriculum, Long> implements IDimCurriculumDao {

	public DimCurriculumDao(Class<DimCurriculum> persistentClass) {
		super(persistentClass);
	}
		
	@Override
	public List<DisplayDimCurriculumDTO> getDashboardCampaignSelection(Long siteId, Long dashboardId) {
		
		StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT dc.ID, dashboard_id, NAME, dc.open_date, dc.drive_state, dc.close_date, dc.due_date");
			queryBuilder.append(" FROM dim_curriculum dc");
			queryBuilder.append(" LEFT OUTER JOIN DASHBOARD_SELECTION_CRITERIA dsc");
			queryBuilder.append(" ON TO_CHAR(dc.ID)= dsc.SELECTION_VALUE");
			queryBuilder.append(" AND dsc.dashboard_id = ?");
			queryBuilder.append(" WHERE site_id = ?");
			queryBuilder.append(" AND (close_date IS NULL  OR  TO_CHAR(close_date,'yyyy') >= '2015') AND  drive_state = 2");
			queryBuilder.append(" AND dc.ID IN (SELECT ID FROM drive@DL_LCECDW_APP_DARLA dr WHERE  dc.ID = dr.ID AND is_archived != 1 )");
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(dashboardId);
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		List<DisplayDimCurriculumDTO> displayDimCurriculumDTOList = new ArrayList<DisplayDimCurriculumDTO>();
		
		while (itr.hasNext()) {
			Map<String, Object> resultMap = itr.next();
			DisplayDimCurriculumDTO displayDimCurriculumDTO = new DisplayDimCurriculumDTO();
				displayDimCurriculumDTO = displayDimCurriculumDTOFromMap(resultMap);
				
			CampaignSelectionDTO campaignSelectionDTO = new CampaignSelectionDTO();
				campaignSelectionDTO.setIsSelected(resultMap.get("dashboard_id") == null ? "0" : "1");
				displayDimCurriculumDTO.setCampaignSelectionDTO(campaignSelectionDTO);
				
			displayDimCurriculumDTOList.add(displayDimCurriculumDTO);
		}
		
		return displayDimCurriculumDTOList;
	}

	private DisplayDimCurriculumDTO displayDimCurriculumDTOFromMap(Map<String, Object> resultMap) {
		
		DisplayDimCurriculumDTO displayDimCurriculumDTO = new DisplayDimCurriculumDTO();
			displayDimCurriculumDTO.setId(DBUtils.getLongFromResultMapObject(resultMap.get("id")));
			displayDimCurriculumDTO.setName((String) resultMap.get("name"));
			
		Date openDate = (Date) resultMap.get("open_date");
			displayDimCurriculumDTO.setOpenDate(openDate);
			
			displayDimCurriculumDTO.setStatus(getCampaignStatus((BigDecimal) resultMap.get("drive_state"), openDate, (Date) resultMap.get("close_date") , (Date) resultMap.get("due_date")));
		
		return displayDimCurriculumDTO;
	}
	
	private String getCampaignStatus(BigDecimal driveState, Date openDate, Date closeDate, Date dueDate){
		
		 // this combines status with the current time to produce a view status
        Status status =  Status.forVal(driveState.intValue());
        Date currentDate = new Date();
        switch (status) {
	        case PLANNING: return CampaignStatusEnum.PLANNING.getLabel();
	        case ABORTED: return CampaignStatusEnum.ABORTED.getLabel();
	        case SCHEDULED: 
	            //ideally if opendate is null the drivestate should be planning and not Scheduled but due to data issue it 
	            // is coming in this loop and  to maintain the sync with Campaign Manager 
	            //We have send it to next loop ie to display started label or past due label
		        if (openDate != null && currentDate.compareTo(openDate) < 0) {
		        	return CampaignStatusEnum.SCHEDULED.getLabel();
		        } else if (closeDate == null || currentDate.compareTo(closeDate) < 0) {
		        	return (dueDate == null || currentDate.compareTo(dueDate) < 0) ? CampaignStatusEnum.STARTED.getLabel() : CampaignStatusEnum.PAST_DUE.getLabel();
		        } else {
		        	return CampaignStatusEnum.CLOSED.getLabel();
		        }
        }
		
		return "";
	}
	
}
