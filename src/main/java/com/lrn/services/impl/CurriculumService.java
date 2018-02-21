package com.lrn.services.impl;

import com.lrn.adapter.ICurriculumAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.ICurriculumService;

public class CurriculumService implements ICurriculumService{
	
	private ICurriculumAdapter curriculumAdapter;
	
	public void setCurriculumAdapter(ICurriculumAdapter curriculumAdapter) {
		this.curriculumAdapter = curriculumAdapter;
	}

	@Override
	public LRNResponse  getDashboardCampaignSelection(Long siteId, Long dashboardId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(curriculumAdapter.getDashboardCampaignSelection(siteId, dashboardId));
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

	@Override
	public LRNResponse saveDashboardCampaignSelection(Long siteId,
			Long dashboardId, String curriculumIds, String userId) {

		LRNResponse lrnResponse = new LRNResponse();
		try {			
			curriculumAdapter.saveDashboardCampaignSelection(siteId,
					dashboardId, curriculumIds, userId);
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		return lrnResponse;
	}

}
