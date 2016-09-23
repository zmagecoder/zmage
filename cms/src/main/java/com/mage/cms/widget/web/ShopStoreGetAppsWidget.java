package com.mage.cms.widget.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.AppType;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.GetAppTypeResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;

/**
 * cms挂件
 * 
 * @author hu.yi 2014-07-15
 */
@UrlPathService(pluginType="",name="获取模板公共插件")
@Service
public class ShopStoreGetAppsWidget extends AbstractCmsWidget {
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


	public CmsLineResp get(String json) throws Exception {
		return null;
	}

	@UrlPathMethod(name = "获取类型，暂定为静态数据", path = "/SystemOperation/ZcloudTemplateBelongGet")
	public CmsLineResp getType(String json) throws Exception {
		AppType appType = new AppType();
		appType.setExtensionData(null);
		appType.setId("1");
		appType.setName("电商平台");
		appType.setRemark("");
		List<AppType> list = new ArrayList<AppType>();
		list.add(appType);
		GetAppTypeResp cmsLineResp = new GetAppTypeResp();
		cmsLineResp.setSuccess(true);
		cmsLineResp.setBelong(list);
		return cmsLineResp;
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
