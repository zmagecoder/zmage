package com.mage.cms.common.params.nav.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.nav.vo.Navigation;

public class GetStoreNavigationResp extends CmsLineResp{
	
	private static final long serialVersionUID = 1L;
	
	private Navigation nav;

	public Navigation getNav() {
		return nav;
	}

	public void setNav(Navigation nav) {
		this.nav = nav;
	}
}
