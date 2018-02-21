package com.lrn.model;

import java.io.Serializable;

/**
 * The Class LRNError will represent any error codes and messages that could
 * be generated from different subsystems.
 */
public class LRNError implements Serializable {
    
    /** The code. */
    private Long code;
    
    /** The message. */
    private String message;
    
    /** Catalyst Error Code **/
    private String catalystErrCd;
    

    public LRNError(){}
    
    public LRNError(Long errorCode, String errorMessage){
    	this.code = errorCode;
    	this.message = errorMessage;
    }
    
    public LRNError(String catalystErrorCode, String errorMessage){
    	this.catalystErrCd = catalystErrorCode;
    	this.message = errorMessage;
    }
    
   
	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	
	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCatalystErrCd() {
		return catalystErrCd;
	}

	public void setCatalystErrCd(String catalystErrCd) {
		this.catalystErrCd = catalystErrCd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LRNError other = (LRNError) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LRNError ["+ code + ": " + catalystErrCd + ":" + message + "]";
	}
    
    
}
