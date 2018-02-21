package com.lrn.customException;

public class DashBoardIdIsBlankException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7371655535105538052L;
	
	public DashBoardIdIsBlankException()
	{
		
	}
	public DashBoardIdIsBlankException(String dashBoardId)
	{
		super(dashBoardId+"");
	}

}
