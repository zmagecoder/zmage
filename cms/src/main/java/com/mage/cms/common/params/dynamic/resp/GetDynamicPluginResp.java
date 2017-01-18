package com.mage.cms.common.params.dynamic.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.dynamic.vo.DynamicPlugin;

/**
 * 
 * @author pzh
 * 读动态插件出参
 */
public class GetDynamicPluginResp extends CmsLineResp implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DynamicPlugin message;
	
	public DynamicPlugin getMessage() {
		return message;
	}

	public void setMessage(DynamicPlugin message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
