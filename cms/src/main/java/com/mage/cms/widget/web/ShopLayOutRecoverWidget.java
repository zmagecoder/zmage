package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.pagestore.req.RecoverStorePageReq;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms公共恢复插件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="",name="公共恢复插件")
@Service
public class ShopLayOutRecoverWidget extends AbstractCmsWidget {
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

	public CmsLineResp publish(String jsonReqStr) throws Exception {
		return null;
	}

	public CmsLineResp get(String json) throws Exception {
		return null;
	}

	public CmsLineResp update(String json) throws Exception {
		return null;
	}

	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		return null;
	}
	
	@UrlPathMethod(name = "恢复页面", path = "/Store/RecoverStorePage")
	public CmsLineResp recover (String jsonReqStr) throws Exception{
		RecoverStorePageReq cmsReq = BeanUtils.jsonToBean("{}", RecoverStorePageReq.class);
		return cmsReq.recover();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
		// TODO Auto-generated method stub
		return new StaticLayOutExtVo();
	}

}
