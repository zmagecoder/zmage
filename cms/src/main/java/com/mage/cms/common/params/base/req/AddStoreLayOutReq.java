package com.mage.cms.common.params.base.req;


import com.mage.cms.common.params.base.resp.AddStoreLayOutResp;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
/**
 * 模板添加实体对象，同时赋予动作操作
 *
 */
public class AddStoreLayOutReq extends CmsLineReq{

	private String page_id;
	private StoreLayOutReq store_layout;
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	
	public StoreLayOutReq getStore_layout() {
		return store_layout;
	}
	public void setStore_layout(StoreLayOutReq store_layout) {
		this.store_layout = store_layout;
	}
	/**
	 * 拖拽添加模板
	 * @throws Exception 
	 */
	public AddStoreLayOutResp add(CmsLayOutContentPraser praser) throws Exception{
		return addStoreLayOut(praser,this);
	}
	
}
