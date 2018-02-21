package com.lrn.customException;

public class DashBoardIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DashBoardIdNotFoundException(String dashBoardId){
		super(dashBoardId+"");
	}

}
