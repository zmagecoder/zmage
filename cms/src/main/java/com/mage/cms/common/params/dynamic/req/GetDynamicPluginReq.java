package com.mage.cms.common.params.dynamic.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.dynamic.resp.GetDynamicPluginResp;
/**
 * 
 * @author pzh
 * 读取动态插件入参
 * 
 */
public class GetDynamicPluginReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public CmsLineResp get()throws Exception{
		return getRecentStoreLayout(this.id + "",GetDynamicPluginResp.class);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
