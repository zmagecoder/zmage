package com.mage.cms.common.params.img.req;

import java.io.Serializable;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.img.resp.GetAdvertisingResp;
import com.mage.cms.common.params.img.vo.Advertising;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.utils.BeanUtils;

public class EditAdvertisingReq extends CmsLineReq implements Serializable{
	private Advertising advertising;

	public Advertising getAdvertising() {
		return advertising;
	}

	public void setAdvertising(Advertising advertising) {
		this.advertising = advertising;
	}

	public CmsLineResp edit() throws Exception{
		GetAdvertisingResp resp = new GetAdvertisingResp();
		Modual modual = this.getEditModualByPluginId(this.advertising.getId());
		try{
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			GetAdvertisingResp getAdvertisingResp = new GetAdvertisingResp();
			getAdvertisingResp.setAdvertising(advertising);
			editModual(this.advertising.getId(),null,BeanUtils.beanToJson(getAdvertisingResp),modual);
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
		}
		return resp;
	}
}
