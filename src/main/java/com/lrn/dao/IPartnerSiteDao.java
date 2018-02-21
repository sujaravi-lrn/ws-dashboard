package com.lrn.dao;

import com.lrn.dto.PartnerSiteDTO;
import com.lrn.model.PartnerSite;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public interface IPartnerSiteDao  extends IGenericDao<PartnerSite, String>{

	public PartnerSiteDTO getPartnerSiteInfo(Long siteId);

}
