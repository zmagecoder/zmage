package com.mage.cms.common.params.mobile.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;

public class GetMobilePreviewResp extends CmsLineResp implements Serializable{
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
