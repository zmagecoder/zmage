package com.mage.cms.common.params.base.resp;


import java.io.Serializable;

/**
 * 模板添加出参对象
 * 
 */
public class AddStoreLayOutResp  extends CmsLineResp implements Serializable {

	private StoreLayOutResp store_layout;
	
	
	public StoreLayOutResp getStore_layout() {
		return store_layout;
	}
	public void setStore_layout(StoreLayOutResp store_layout) {
		this.store_layout = store_layout;
	}
	
}
