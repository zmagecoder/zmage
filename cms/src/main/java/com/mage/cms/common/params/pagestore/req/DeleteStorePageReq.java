package com.mage.cms.common.params.pagestore.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.DeleteStorePageResp;
/**
 * 
 * @author pzh
 * 导航菜单删除
 * 
 */
public class DeleteStorePageReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String store_page_id;
	
	
	public String getStore_page_id() {
		return store_page_id;
	}

	public void setStore_page_id(String store_page_id) {
		this.store_page_id = store_page_id;
	}

	/**
	 * 删除页面
	 */
	public DeleteStorePageResp delete() throws Exception{
		
		return deleteStorePage(this);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
