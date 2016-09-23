package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.req.PublishStoreLayOutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;

/**
 * cms公共发布插件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="",name="公共发布插件")
@Service
public class ShopLayOutPublishWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}

	protected void config(Map<String, String> params) {

	}
	
	public CmsLineResp add(String json) throws Exception {
		return null;
	}

	public CmsLineResp delete(String json) throws Exception {
		return null;
	}

	public CmsLineResp edit(String json) throws Exception {
		return null;
	}

	@UrlPathMethod(name = "发布", path = "/Store/BuildStatic")
	public CmsLineResp publish(String json) throws Exception {
		PublishStoreLayOutReq cmsReq = new PublishStoreLayOutReq();
		return cmsReq.publish();
	}

	@UrlPathMethod(name = "手机发布", path = "/Store/MobileBuildStatic")
	public CmsLineResp mobilepublish(String json) throws Exception {
		PublishStoreLayOutReq cmsReq = new PublishStoreLayOutReq();
		return cmsReq.publish();
	}
	

	public CmsLineResp get(String json) throws Exception {
		return null;
	}

	public CmsLineResp update(String json) throws Exception {
		return null;
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
