package com.lrn.dto;

import java.io.Serializable;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ValidationMessageDTO implements Serializable {
	
	private static final long serialVersionUID = 6888897673832606155L;
	
	private String message ;
	private String code ;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
