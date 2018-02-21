package com.lrn.util;

import org.apache.log4j.Logger;
/*@author Yuvraj
 * 
 */
public class ApplicationConstant {

	private static final Logger logger = Logger.getLogger(ApplicationConstant.class);
	
	public static String FOR_SITE_ID_DASBOARD_ID_IS_NOT_PRESENT_IN_DATABASE="ER0001";
	
	public static String DASBOARD_DETAILS_NOT_ADDED_ON_DATABASE_FOR_THIS_REQUEST_BECAUSE_SITE_ID_IS_GETTING_NULL_ON_REQUEST="ER0002";
	
	public static String DASHBOARD_ID_SHOULD_NOT_BE_BLANK_OR_NULL="ER0003";
	
	public static String SITE_ID_SHOULD_NOT_BE_NULL_FOR_RETRIVING_THE_GETDASHBOARDVIEWCONFIG="ER0004";
	
	public static String DASHBOARD_ID_NULL_ON_REQUEST_SHOULD_NOT_SEND_DASHBOARD_ID_NULL="ER0005";
	
	public static String USER_ID_NULL_ON_REQUEST="ER0006";

}
