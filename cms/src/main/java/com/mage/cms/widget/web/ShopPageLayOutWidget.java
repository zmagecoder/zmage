package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.params.base.req.UpdateStoreLayoutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.freestore.req.EditStoreFreeReq;
import com.mage.cms.common.params.freestore.req.GetStoreFreeReq;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms挂件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="",name="app发布")
@Service
public class ShopPageLayOutWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}

	protected void config(Map<String, String> params) {

	}
	
	public CmsLineResp add(String json) throws Exception {
		return null;
	}

	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	public CmsLineResp edit(String json) throws Exception {
		EditStoreFreeReq cmsReq = BeanUtils.jsonToBean(json, EditStoreFreeReq.class);
		return cmsReq.edit();
	}

	@Override
	//
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		//获取待发页面
		//循环页面
		//页面发布处理(pzh)
		  //页面模块发布处理(huyi)
		return null;
	}


	public CmsLineResp get(String json) throws Exception {
		GetStoreFreeReq cmsReq = BeanUtils.jsonToBean(json, GetStoreFreeReq.class);
		return cmsReq.get();
	}

	public CmsLineResp update(String json) throws Exception {
		UpdateStoreLayoutReq cmsReq = BeanUtils.jsonToBean(json, UpdateStoreLayoutReq.class);
		return cmsReq.update();
	}

	@Override
	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
		// TODO Auto-generated method stub
		return new StaticLayOutExtVo();
	}

}
