package com.mage.cms.common.params.base.req;

import com.mage.cms.common.params.base.resp.DeleteStoreLayoutResp;

/**
 * 删除模块信息入参
 * @author hu.yi
 * @date 2014.06.20
 */
public class DeleteStoreLayOutReq extends CmsLineReq{

	private String page_id;
	private String store_layout_id;
	
	
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getStore_layout_id() {
		return store_layout_id;
	}
	public void setStore_layout_id(String store_layout_id) {
		this.store_layout_id = store_layout_id;
	}
	
	public DeleteStoreLayoutResp delete() throws Exception{
		
		return deleteStoreLayOut(this);
	}
}