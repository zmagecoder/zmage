package com.mage.cms.common.params.base.resp;

import java.util.List;

/**
 * 获取app类型
 * @author hu.yi
 * @date 2014.07.16
 */
public class GetAppTypeResp extends CmsLineResp{

	private boolean success;
	private List<AppType> belong;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<AppType> getBelong() {
		return belong;
	}
	public void setBelong(List<AppType> belong) {
		this.belong = belong;
	}
}
