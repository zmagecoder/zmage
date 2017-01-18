package com.mage.cms.common.params.base.resp;

import java.util.List;

/**
 * 获取当前页面插件内容出参
 */
public class GetStoreLayOutResp extends CmsLineResp{

	private boolean success;
	//StoreLayOutResp 中 data对应各个业务的get方法的出参，如StoreFreeResp 这些在各自业务中赋值
	private List<StoreLayOutResp> store_layout;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<StoreLayOutResp> getStore_layout() {
		return store_layout;
	}
	public void setStore_layout(List<StoreLayOutResp> store_layout) {
		this.store_layout = store_layout;
	}
}
