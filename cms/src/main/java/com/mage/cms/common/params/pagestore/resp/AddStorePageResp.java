package com.mage.cms.common.params.pagestore.resp;


import java.io.Serializable;

import com.mage.cms.common.model.AppPage;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.pagestore.req.StorePageReq;
/**
 * 页面添加出参对象
 * @author pzh
 * 
 */
public class AddStorePageResp  extends CmsLineResp implements Serializable {

	private StorePageReq store_page;
	private AppPage app_page;

	public StorePageReq getStore_page() {
		return store_page;
	}

	public void setStore_page(StorePageReq store_page) {
		this.store_page = store_page;
	}

	public AppPage getApp_page() {
		return app_page;
	}

	public void setApp_page(AppPage app_page) {
		this.app_page = app_page;
	}

}
