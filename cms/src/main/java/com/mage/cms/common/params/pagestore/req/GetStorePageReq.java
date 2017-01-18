package com.mage.cms.common.params.pagestore.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
/**
 * 新增页面实体对象
 * @author pzh
 */
public class GetStorePageReq extends CmsLineReq{
	
	private String save_level; 
	private String device; 
	private String type;
	

	public String getSave_level() {
		return save_level;
	}

	public void setSave_level(String save_level) {
		this.save_level = save_level;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取页面列表
	 */
	public GetStorePageResp get() throws Exception{
		
		return getStorePage(this);
	}
}
