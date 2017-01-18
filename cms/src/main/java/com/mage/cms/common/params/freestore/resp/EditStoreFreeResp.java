package com.mage.cms.common.params.freestore.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;


/**
 * 模板编辑出参对象
 * @author hu.yi
 * @date 2014.06.18
 */
public class EditStoreFreeResp extends CmsLineResp{

	private boolean success;

	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
