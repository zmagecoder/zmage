package com.mage.cms.common.params.pagestore.resp;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.pagestore.req.StorePageReq;
/**
 * 页面添加出参对象
 * @author pzh
 * 
 */
public class GetStorePageResp  extends CmsLineResp implements Serializable {

	private List<StorePageReq> store_page = new ArrayList<StorePageReq>();

	public List<StorePageReq> getStore_page() {
		return store_page;
	}

	public void setStore_page(List<StorePageReq> store_page) {
		this.store_page = store_page;
	}
	
}
