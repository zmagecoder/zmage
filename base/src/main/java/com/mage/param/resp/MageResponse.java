package com.mage.param.resp;


public class MageResponse extends RopResp{
	
	private static final long serialVersionUID = 5650182064130756011L;
	
	String userSessionId;

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}
}
