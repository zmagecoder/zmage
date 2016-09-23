package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.template.req.EditStoreTemplateReq;
import com.mage.cms.common.params.template.req.GetStoreTemplateReq;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * 页面风格插件
 * 
 * @author pzh 2014-7-7
 */
@UrlPathService(pluginType="102",name="页面风格插件")
@Service
public class ShopStoreTemplateWidget extends AbstractCmsWidget {
	
	protected void display(Map<String, String> params) {
		
	}
	
	protected void config(Map<String, String> params) {

	}

	@Override
	public CmsLineResp add(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp delete(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@UrlPathMethod(name = "切换页面风格", path = "/Store/EditStoreTemplate")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		EditStoreTemplateReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, EditStoreTemplateReq.class);
		return cmsReq.edit();
	}

	@Override
	@UrlPathMethod(name = "切换页面风格", path = "/Store/GetStoreTemplate")
	public CmsLineResp get(String jsonReqStr) throws Exception {
		GetStoreTemplateReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, GetStoreTemplateReq.class);
		return cmsReq.get();
	}

	@Override
	public CmsLineResp update(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
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
		return null;
	}
	
	
}
