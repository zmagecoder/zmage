package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.params.window.req.EditShowcaseReq;
import com.mage.cms.common.params.window.req.GetShowcaseReq;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms橱窗插件
 * @author pzh 2014-6-27
 */
@UrlPathService(pluginType="",name="橱窗插件")
@Service
public class ShopShowcaseWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}
	
	protected void config(Map<String, String> params) {

	}
	
	@UrlPathMethod(name = "获取", path = "/Store/GetShowcase")
	public CmsLineResp get(String json) throws Exception {
		GetShowcaseReq cmsReq = BeanUtils.jsonToBean(json, GetShowcaseReq.class);
		return cmsReq.get();
	}
	

	@Override
	public CmsLineResp getRespByContent(String json) throws Exception {
		return null;
	}

	@Override
	@UrlPathMethod(name = "编辑橱窗", path = "/Store/EditShowcase")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		EditShowcaseReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, EditShowcaseReq.class);
		return cmsReq.edit();
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
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
		// TODO Auto-generated method stub
		return new StaticLayOutExtVo();
	}

}
