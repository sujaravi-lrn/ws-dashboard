package com.lrn.model;

import java.io.Serializable;

public class LRNResponse implements Serializable {

	private static final long serialVersionUID = -5352866937864513137L;
	
	private boolean isSuccess = false;
	private LRNError error = null;
	private Object dataObject;
	
	public LRNResponse() {
		// default constructor because we don't ALWAYS have an error to report.
	}
	
	public LRNResponse(Long errorCode, String errorMessage){
		this.isSuccess = ((errorMessage == null) ? true : false);
		this.error = new LRNError(errorCode, errorMessage);
	}

	public LRNResponse(Long errorCode, String catalystErrorCode, String errorMessage){
		this.isSuccess = ((errorMessage == null) ? true : false);
		this.error = new LRNError(catalystErrorCode, errorMessage);
	}

	
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the error
	 */
	public LRNError getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(LRNError error) {
		this.isSuccess = ((error.getMessage() == null) ? true : false);
		this.error = error;
	}

	/**
	 * @return the dataObject
	 */
	public Object getDataObject() {
		return dataObject;
	}

	/**
	 * @param dataObject the dataObject to set
	 */
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
}