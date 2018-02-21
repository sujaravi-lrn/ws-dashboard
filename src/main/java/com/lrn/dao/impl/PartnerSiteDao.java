package com.lrn.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lrn.dao.IPartnerSiteDao;
import com.lrn.dto.PartnerSiteDTO;
import com.lrn.model.PartnerSite;

/**
 * @author Suja.Ravi
 * Jun 22, 2016
 */
public class PartnerSiteDao extends  GenericDao<PartnerSite, String> implements IPartnerSiteDao {

	public PartnerSiteDao(Class<PartnerSite> persistentClass) {
		super(persistentClass);
	}

	@Override
	public PartnerSiteDTO getPartnerSiteInfo(Long siteId) {

		StringBuilder queryBuilder = new StringBuilder();			
			queryBuilder.append("select p.id as PARTNER_ID, p.name as PARTNER_NAME, s.name as SITE_NAME");
			queryBuilder.append(" from lcec.partner p, lcec.site s");
			queryBuilder.append(" where s.PARTNER_ID = p.id");
			queryBuilder.append(" and s.id = ?");
			
		ArrayList<Object> params = new ArrayList<Object> ();
			params.add(siteId);
			
		List<Map<String, Object>> resultSetMapList = queryForList(queryBuilder.toString(), params);
		Iterator<Map<String, Object>> itr = resultSetMapList.iterator();
		
		while(itr.hasNext()) {
		    Map<String, Object> resultMap = itr.next();
		    return getPatnerSiteInfo(resultMap);
		}
		
		return null;	
	}

	private PartnerSiteDTO getPatnerSiteInfo(Map<String, Object> rs) {
		
		PartnerSiteDTO  partnerSiteDTO = new PartnerSiteDTO();
		    partnerSiteDTO.setPartnerId(((BigDecimal)rs.get("PARTNER_ID")).longValue());
			partnerSiteDTO.setPartnerName(((String)rs.get("PARTNER_NAME")));
			partnerSiteDTO.setSiteName(((String)rs.get("SITE_NAME")));
		return partnerSiteDTO;
	}
}
