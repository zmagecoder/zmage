package com.mage.cms.common.params.img.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.img.resp.GetAdvertisingResp;

public class GetAdvertisingReq extends CmsLineReq{
	private String advertising_id;

	public String getAdvertising_id() {
		return advertising_id;
	}

	public void setAdvertising_id(String advertising_id) {
		this.advertising_id = advertising_id;
	}
	
	public GetAdvertisingResp get() throws Exception{
		return (GetAdvertisingResp)getRecentStoreLayout(this.advertising_id,GetAdvertisingResp.class);
	}
}
