package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.req.UpdateStoreLayoutReq;
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
@UrlPathService(pluginType="",name="布局更新")
@Service
public class ShopStoreUpdateLayOutWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}

	protected void config(Map<String, String> params) {

	}
	
	public CmsLineResp add(String json) throws Exception {
		return null;
	}

	@Override
	public CmsLineResp delete(String jsonReqStr) {
		// TODO Auto-generated method stub
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


	@Override
	public CmsLineResp get(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@UrlPathMethod(name = "更新", path = "/Store/UpdateStoreLayout")
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
