package com.lrn.manager.impl;

import com.lrn.dao.IPartnerSiteDao;
import com.lrn.dto.PartnerSiteDTO;
import com.lrn.manager.IPartnerSiteManager;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public class PartnerSiteManager implements IPartnerSiteManager {
	
	private IPartnerSiteDao partnerSiteDao;
	
	public void setPartnerSiteDao(IPartnerSiteDao partnerSiteDao) {
		this.partnerSiteDao = partnerSiteDao;
	}

	@Override
	public PartnerSiteDTO getPartnerSiteInfo(Long siteId) {
		return partnerSiteDao.getPartnerSiteInfo(siteId);
	}
}
