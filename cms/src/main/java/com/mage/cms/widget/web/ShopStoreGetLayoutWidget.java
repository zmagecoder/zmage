package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.req.GetStoreLayOutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms挂件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="",name="获取页面数据公共插件")
@Service
public class ShopStoreGetLayoutWidget extends AbstractCmsWidget {
	
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

	@Override
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}


	@UrlPathMethod(name = "获取", path = "/Store/GetStoreLayout")
	public CmsLineResp get(String json) throws Exception {
		GetStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, GetStoreLayOutReq.class);
		return cmsReq.get();
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
