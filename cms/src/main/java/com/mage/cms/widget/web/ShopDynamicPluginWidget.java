package com.mage.cms.widget.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.model.CmsWidget;
import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.AddStoreLayOutReq;
import com.mage.cms.common.params.base.req.CmsLineOperator;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.dynamic.req.EditDynamicPluginReq;
import com.mage.cms.common.params.dynamic.req.GetDynamicPluginReq;
import com.mage.cms.common.params.dynamic.resp.GetDynamicPluginResp;
import com.mage.cms.common.params.dynamic.vo.DynamicPlugin;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.consts.CmsConst;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.widget.processor.IWidget;
import com.mage.platform.framework.widget.processor.IWidgetParamParser;
import com.mage.platform.sdk.freemarker.FreeMarkerPaser;

/**
 * 动态插件
 * 
 */
@UrlPathService(pluginType="11",name="动态插件")
@Service
public class ShopDynamicPluginWidget extends AbstractCmsWidget {
	
	@Resource
	private IWidgetParamParser widgetParamParser;
	
	@Override
	protected void display(Map<String, String> params) {
		
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout11")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq req = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return req.add(new CmsLayOutContentPraser() {
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				Long pluginId = modual.getPluginId();
				String storeId = CmsLineOperator.getStoreId();
				GetDynamicPluginResp resp = new GetDynamicPluginResp();
				DynamicPlugin message = new DynamicPlugin();
				message.setId(pluginId);
				message.setStoreId(Long.valueOf(storeId));
				message.setCheckType(0);
				message.setInputType(0);
				message.setShowTitle(3);
				message.setStyle(0);
				message.setTitle("动态插件");
				message.setWidget_image("/default/cmsline/Content/Admin/images/edit_default.png");
				resp.setMessage(message);
				String content = BeanUtils.beanToJson(resp);
				return content;
			}
		});
	}

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout11")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	@UrlPathMethod(name = "编辑", path = "/Store/EditPluginStoreMessage")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		EditDynamicPluginReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, EditDynamicPluginReq.class);
		return cmsReq.edit();
	}

	@Override
	@UrlPathMethod(name = "获取", path = "/Store/GetPluginStoreMessage")
	public CmsLineResp get(String json) throws Exception {
		GetDynamicPluginReq req = BeanUtils.jsonToBean(json, GetDynamicPluginReq.class); 
		return req.get();
	}


	@Override
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		GetDynamicPluginResp cmsResp = BeanUtils.jsonToBean(jsonReqStr, GetDynamicPluginResp.class);
		return cmsResp.getMessage();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp)throws Exception {
		DynamicPlugin plugin = (DynamicPlugin) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(plugin.getShowTitle());
		vo.setStyle(new Integer(plugin.getStyle()));
		vo.setTitle(plugin.getTitle());
		return vo;
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}

	@Override
	public CmsLineResp update(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
