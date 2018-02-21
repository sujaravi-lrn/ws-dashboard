package com.lrn.customException;

public class SiteIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429940998784733128L;

	public SiteIdNotFoundException(String siteId) {
		super(siteId + "");

	}
}