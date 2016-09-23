package com.mage.cms.common.params.pagestore.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.UpdateStorePageResp;
/**
 * 
 * @author pzh
 * 导航菜单更新
 * 
 */
public class UpdateStorePageReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private StorePageReq store_page;

	public StorePageReq getStore_page() {
		return store_page;
	}



	public void setStore_page(StorePageReq store_page) {
		this.store_page = store_page;
	}

	/**
	 * 获取页面列表
	 */
	public UpdateStorePageResp update() throws Exception{
		
		return updateStorePage(this);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
