package com.mage.cms.common.params.base.resp;

/**
 * 删除模块信息出参
 * @author hu.yi
 * @date 2014.06.20
 */
public class DeleteStoreLayoutResp extends CmsLineResp{

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
