package com.mage.cms.widget.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.AddStoreLayOutReq;
import com.mage.cms.common.params.base.req.CmsLineOperator;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.img.req.EditAdvertisingReq;
import com.mage.cms.common.params.img.req.GetAdvertisingReq;
import com.mage.cms.common.params.img.resp.GetAdvertisingResp;
import com.mage.cms.common.params.img.vo.Adv;
import com.mage.cms.common.params.img.vo.Advertising;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms挂件
 * 
 * @author hu.yi 2014-07-02
 */
@UrlPathService(pluginType="0",name="轮播广告插件")
@Service
public class ShopAdvertisingWidget extends AbstractCmsWidget {
	
	protected void display(Map<String, String> params) {
		//静态模式数据设置
		//this.putData("data", this.storeLay.getData());		//存储数据
		this.freeMarkerPaser.setPageName("ShopAdvertisingWidgetStatic");
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}
	protected void config(Map<String, String> params) {

	}
	
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout0")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return cmsReq.add(new CmsLayOutContentPraser() {
			//add by wui构造入参信息
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				Long pluginId = modual.getPluginId();
				String storeId = CmsLineOperator.getStoreId();
				GetAdvertisingResp resp = new GetAdvertisingResp();
				Advertising advertising = new Advertising();
				resp.setAdvertising(advertising);
				advertising.setExtensionData(null);
				List<Adv> advlist = new ArrayList<Adv>();
				advertising.setAdvertising(advlist);
				advlist.add(new Adv(null,null,0,"0",null, EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advlist.add(new Adv(null,null,0,"0",null, EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advlist.add(new Adv(null,null,0,"0",null, EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advertising.setDirection(0);
				advertising.setId(String.valueOf(pluginId));
				advertising.setInterval(0);
				advertising.setSetting(null);
				advertising.setShowTitle(new Integer("0"));
				advertising.setStoreId(storeId);
				advertising.setStyle(0);
				advertising.setTitle("请填写标题名称");
				advertising.setType(0);
				String strJson = BeanUtils.beanToJson(resp);
				return strJson;
			}
		});
	}

	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout0")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@UrlPathMethod(name = "编辑", path = "/Store/EditAdvertising")
	public CmsLineResp edit(String json) throws Exception {
		EditAdvertisingReq req = BeanUtils.jsonToBean(json, EditAdvertisingReq.class);
		return req.edit();
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}


	@UrlPathMethod(name = "获取", path = "/Store/GetAdvertising")
	public CmsLineResp get(String json) throws Exception {
		GetAdvertisingReq req = BeanUtils.jsonToBean(json, GetAdvertisingReq.class); 
		return req.get();
	}

	public CmsLineResp update(String json) throws Exception {
		return null;
	}

	@Override
	public CmsLineResp getRespByContent(String json) throws Exception {
		GetAdvertisingResp resp = BeanUtils.jsonToBean(json, GetAdvertisingResp.class);
		return resp.getAdvertising();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp)throws Exception {
		Advertising cmsResp = (Advertising) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(cmsResp.getShowTitle());
		vo.setStyle(new Integer(cmsResp.getStyle()));
		vo.setTitle(cmsResp.getTitle());
		vo.setType(cmsResp.getType());
		vo.setDirection(cmsResp.getDirection());
		vo.setInterval(cmsResp.getInterval());
		return vo;
	}
}
