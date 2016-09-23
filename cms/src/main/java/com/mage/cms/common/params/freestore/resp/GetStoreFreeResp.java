package com.mage.cms.common.params.freestore.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;

public class GetStoreFreeResp extends CmsLineResp implements Serializable{

	private StoreFreeResp free;
	
	
	public StoreFreeResp getFree() {
		return free;
	}
	public void setFree(StoreFreeResp free) {
		this.free = free;
	}
}
