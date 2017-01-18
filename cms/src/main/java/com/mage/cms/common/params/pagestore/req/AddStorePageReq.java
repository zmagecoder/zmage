package com.mage.cms.common.params.pagestore.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
/**
 * 新增页面实体对象
 * @author pzh
 */
public class AddStorePageReq extends CmsLineReq{
	
	private StorePageReq store_page;
	private boolean publish_flag ;

	public StorePageReq getStore_page() {
		return store_page;
	}

	public void setStore_page(StorePageReq store_page) {
		this.store_page = store_page;
	}
	
	

	public boolean isPublish_flag() {
		return publish_flag;
	}

	public void setPublish_flag(boolean publish_flag) {
		this.publish_flag = publish_flag;
	}

	/**
	 * 新增页面
	 */
	public AddStorePageResp add() throws Exception{
		AddStorePageResp resp = addStorePage(this);
		StorePageReq store_page = resp.getStore_page();
		store_page.setPage_url(resp.getApp_page().getPage_url());
		store_page.setIndexId(resp.getApp_page().getIndexId() + "");
		store_page.setPageId(resp.getApp_page().getPage_id());
		resp.setStore_page(store_page);
		return resp;
	}
}
