package com.mage.cms.common.params.base.req;

import java.util.List;

import com.mage.cms.common.params.base.resp.UpdateStoreLayoutResp;

/**
 * 布局更新入参对象
 * @author hu.yi
 * @date 2014.06.20
 */
public class UpdateStoreLayoutReq extends CmsLineReq{

	private String page_id;
	private List<StoreLayOutReq> store_layout;
	
	
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	
	
	public List<StoreLayOutReq> getStore_layout() {
		return store_layout;
	}
	public void setStore_layout(List<StoreLayOutReq> store_layout) {
		this.store_layout = store_layout;
	}
	public UpdateStoreLayoutResp update() throws Exception{
		
		return updateStoreLayout(this);
	}
}
