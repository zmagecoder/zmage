package com.mage.cms.common.params.dynamic.req;

import java.io.Serializable;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.dynamic.resp.EditDynamicPluginResp;
import com.mage.cms.common.params.dynamic.resp.GetDynamicPluginResp;
import com.mage.cms.common.params.dynamic.vo.DynamicPlugin;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * 
 * @author pzh
 * 读取动态插件入参
 * 
 */
public class EditDynamicPluginReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DynamicPlugin message;
	
	public EditDynamicPluginResp edit(){
		EditDynamicPluginResp resp = new EditDynamicPluginResp();
		Modual modual = this.getEditModualByPluginId(this.message.getId() + "");
		
		GetDynamicPluginResp getStoreFreeResp = new GetDynamicPluginResp();
		getStoreFreeResp.setMessage(this.message);
		try {
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			editModual(this.getMessage().getId() + "", null, BeanUtils.beanToJson(getStoreFreeResp), modual);
			resp.setSuccess(true);
		} catch (Exception e) {
			resp.setSuccess(false);
		}
		
		return resp;
	}
	
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
