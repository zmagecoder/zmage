package com.mage.cms.common.params.nav.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.nav.resp.GetStoreNavigationResp;

public class GetStoreNavigationReq extends CmsLineReq{
	private String nav_id;

	public String getNav_id() {
		return nav_id;
	}

	public void setNav_id(String nav_id) {
		this.nav_id = nav_id;
	}
	
	public GetStoreNavigationResp get() throws Exception{
		return (GetStoreNavigationResp) getRecentStoreLayout(this.nav_id,GetStoreNavigationResp.class);
	}
	
}
