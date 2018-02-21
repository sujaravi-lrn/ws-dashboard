package com.lrn.services.impl;
import org.apache.log4j.Logger;

import com.lrn.dao.IETLDao;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IETLService;
import com.lrn.util.LogUtils;

public class ETLService implements IETLService{
	private static final Logger logger = Logger.getLogger(ETLService.class);
	private IETLDao eTLSDao;
	@Override
	public LRNResponse getETLDetailsBySiteId(Long siteId) {
		long st = System.currentTimeMillis();
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(eTLSDao.getETLDeatils(siteId));
			//eTLSDao.getETLDeatils(siteId);
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		LogUtils.logTime(logger, "getETLDetailsBySiteId()", st, System.currentTimeMillis());
		return lrnResponse;
	}

	@Override
	public LRNResponse runETL(Long siteId) {
		long st = System.currentTimeMillis();
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			eTLSDao.runETL(siteId);
			lrnResponse.setSuccess(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			LRNError lrnError = new LRNError();
			lrnError.setMessage(exception.getMessage());
			lrnError.setCode(-1L);
			lrnResponse.setError(lrnError);
			lrnResponse.setSuccess(false);
		}
		LogUtils.logTime(logger, "runETL()", st, System.currentTimeMillis());
		return lrnResponse;
	}

	public void seteTLSDao(IETLDao eTLSDao) {
		this.eTLSDao = eTLSDao;
	}

	
}
