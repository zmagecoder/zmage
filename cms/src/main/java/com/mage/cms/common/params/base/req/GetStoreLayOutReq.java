package com.mage.cms.common.params.base.req;

import com.mage.cms.common.params.base.resp.GetStoreLayOutResp;

/**
 * 获取当前页面插件内容入参
 * @author hu.yi
 * @date 2014.06.24
 */
public class GetStoreLayOutReq extends CmsLineReq{

	private String page_id;

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	
	
	public GetStoreLayOutResp get() throws Exception{
		return getPageStoreLayout(this);
	}
}
