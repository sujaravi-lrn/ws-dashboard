package com.lrn.services;

import com.lrn.model.LRNResponse;

public interface IETLService {
	
LRNResponse getETLDetailsBySiteId(Long siteId);	

LRNResponse runETL(Long siteId);	
	

}
