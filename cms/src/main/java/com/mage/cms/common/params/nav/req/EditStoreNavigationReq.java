package com.mage.cms.common.params.nav.req;

import java.io.Serializable;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.nav.resp.EditStoreNavigationResp;
import com.mage.cms.common.params.nav.resp.GetStoreNavigationResp;
import com.mage.cms.common.params.nav.vo.Navigation;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.utils.BeanUtils;

public class EditStoreNavigationReq extends CmsLineReq implements Serializable{
	
	private static final long serialVersionUID = -449182035691271345L;
	private Navigation nav_info;

	public Navigation getNav_info() {
		return nav_info;
	}

	public void setNav_info(
			Navigation nav_info) {
		this.nav_info = nav_info;
	}

	public CmsLineResp edit() throws Exception{
		EditStoreNavigationResp resp = new EditStoreNavigationResp();
		Modual modual = this.getEditModualByPluginId(this.nav_info.getNavId());
		try{
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			GetStoreNavigationResp getStoreNavigationResp = new GetStoreNavigationResp();
			getStoreNavigationResp.setNav(this.nav_info);
			editModual(this.nav_info.getNavId(),null,BeanUtils.beanToJson(getStoreNavigationResp),modual);
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
		}
		return resp;
	}
}
