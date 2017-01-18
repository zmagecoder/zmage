package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.pagestore.req.SaveStorePageReq;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms公共发布插件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="",name="公共发布插件")
@Service
public class ShopLayOutSaveWidget extends AbstractCmsWidget {
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

	public CmsLineResp publish(String json) throws Exception {
		return null;
	}


	public CmsLineResp get(String json) throws Exception {
		return null;
	}

	public CmsLineResp update(String json) throws Exception {
		return null;
	}
	
	@UrlPathMethod(name = "保存页面", path = "/Store/SaveStorePage")
	public CmsLineResp save(String jsonReqStr) throws Exception {
		SaveStorePageReq cmsReq = BeanUtils.jsonToBean("{}", SaveStorePageReq.class);
		return cmsReq.save();
	}
	
	@UrlPathMethod(name = "保存手机版页面", path = "/Store/MobileSaveStorePage")
	public CmsLineResp saveMobile(String jsonReqStr) throws Exception {
		SaveStorePageReq cmsReq = BeanUtils.jsonToBean("{}", SaveStorePageReq.class);
		return cmsReq.save();
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
