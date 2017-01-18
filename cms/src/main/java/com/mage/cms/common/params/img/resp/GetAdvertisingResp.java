package com.mage.cms.common.params.img.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.img.vo.Advertising;

public class GetAdvertisingResp extends CmsLineResp{
	private Advertising advertising;

	public Advertising getAdvertising() {
		return advertising;
	}

	public void setAdvertising(Advertising advertising) {
		this.advertising = advertising;
	}
	
}
