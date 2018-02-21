package com.lrn.constants;

/**
 * @author Suja.Ravi
 * Jul 26, 2016
 */
public class ValidationMessages {
	
	public enum ValidationMessagesEnum {

		USER_IS_INACTIVE("ER0007", "user is inactive"),
		PROXY_ALREADY_ASSIGNED("ER0008", "proxy already assigned"),
		USER_IS_NOT_FOUND("ER0009", "user not found"),
		USER_SAVED("S001", "user saved"),
		USER_DELETED("S002", "user deleted");
		
		
	    private final String code;
	    private final String message;
	
	    private ValidationMessagesEnum(String code, String message) {
	    	this.code = code;
	    	this.message = message;
	    }
	    
	    public String getCode() { return code; }
	    public String getMessage() { return message; }
	}	
	
}
