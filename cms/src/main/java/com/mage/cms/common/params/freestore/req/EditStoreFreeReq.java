package com.mage.cms.common.params.freestore.req;

import java.io.Serializable;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.freestore.resp.EditStoreFreeResp;
import com.mage.cms.common.params.freestore.resp.GetStoreFreeResp;
import com.mage.cms.common.params.freestore.resp.StoreFreeResp;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.utils.BeanUtils;


/**
 * 自由编辑实体对象，同时赋予动作操作
 * @author hu.yi
 * @date 2014.06.18
 */
public class EditStoreFreeReq extends CmsLineReq implements Serializable{

	private StoreFreeResp free_info;
	
	
	
	public StoreFreeResp getFree_info() {
		return free_info;
	}



	public void setFree_info(StoreFreeResp free_info) {
		this.free_info = free_info;
	}



	/**
	 * 拖拽添加模板
	 * @throws Exception 
	 */
	public EditStoreFreeResp edit() throws Exception{
		
		EditStoreFreeResp editStoreLayOutResp = new EditStoreFreeResp();
		
		Modual modual = getEditModualByPluginId(this.getFree_info().getFreeId());
		GetStoreFreeResp getStoreFreeResp = new GetStoreFreeResp();
		getStoreFreeResp.setFree(this.getFree_info());
		try {
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			String content = getStoreFreeResp.getFree().getContent();
			content = content.replaceAll("(\t)", "");
			getStoreFreeResp.getFree().setContent(content);
			editModual(this.getFree_info().getFreeId(), null, BeanUtils.beanToJson(getStoreFreeResp), modual);
			editStoreLayOutResp.setSuccess(true);
		} catch (Exception e) {
			editStoreLayOutResp.setSuccess(false);
		}
		
		return editStoreLayOutResp;
	}
}
