package com.lrn.services.impl;

import com.lrn.adapter.IProxyAdapter;
import com.lrn.model.LRNError;
import com.lrn.model.LRNResponse;
import com.lrn.services.IProxyService;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ProxyService implements IProxyService {

	private IProxyAdapter proxyAdapter;
	
	public void setProxyAdapter(IProxyAdapter proxyAdapter) {
		this.proxyAdapter = proxyAdapter;
	}


	@Override
	public LRNResponse getProxyAssignmentList(Long siteId, String userId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(proxyAdapter.getProxyAssignmentList(siteId, userId));
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
	public LRNResponse saveProxyAssignment(String assigneeUserId,
			String proxyLoginName, Long siteId, String oldProxyUserId, String userId) {
		
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(proxyAdapter.saveProxyAssignment(assigneeUserId, proxyLoginName, siteId, oldProxyUserId, userId));
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
	public LRNResponse deleteProxyAssignment(String userId, String proxyUserId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(proxyAdapter.deleteProxyAssignment(userId, proxyUserId));
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
	public LRNResponse getProxyAssignmentAndProxyPermissionUserList(Long siteId) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(proxyAdapter.getProxyAssignmentAndProxyPermissionUserList(siteId));
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
	public LRNResponse getProxyUser(String proxyLoginName, Long siteId, String company) {
		LRNResponse lrnResponse = new LRNResponse();
		try {			
			lrnResponse.setDataObject(proxyAdapter.getProxyUser(proxyLoginName, siteId, company));
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
