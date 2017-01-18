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
import com.mage.cms.common.params.img.vo.Setting;
import com.mage.cms.common.params.nav.resp.GetTagClassResp;
import com.mage.cms.common.params.news.vo.TagClass;
import com.mage.cms.common.params.news.vo.TagClassInfo;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * cms挂件
 * 
 * @author sguo 2014-6-26
 */
@UrlPathService(pluginType="12",name="图片滚播")
@Service
public class ShopCarouselWidget extends AbstractCmsWidget {

	protected void display(Map<String, String> params) {
		//静态模式数据设置
		//this.putData("data", this.storeLay.getData());//存储数据
		this.freeMarkerPaser.setPageName("ShopCarouselWidgetStatic");
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout12")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq req = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return req.add(new CmsLayOutContentPraser() {
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
				advlist.add(new Adv(null,null,0,"0",null,EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advlist.add(new Adv(null,null,0,"0",null,EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advlist.add(new Adv(null,null,0,"0",null,EopSetting.IMG_SERVER_DOMAIN + "/default/cmsline/Content/Admin/images/edit_default.png"));
				advertising.setDirection(0);
				advertising.setId(String.valueOf(pluginId));
				advertising.setInterval(0);
				advertising.setSetting(new Setting(null,"1","1","1000","2","0"));
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

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout12")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	@UrlPathMethod(name = "编辑", path = "/Goods/GetTagClass")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		GetTagClassResp resp = new GetTagClassResp();
		TagClass tag_class = new TagClass();
		tag_class.setTag_class_info(new ArrayList<TagClassInfo>());
		tag_class.setTag_class_sum("0");
		tag_class.setTag_item_sum("0");
		tag_class.setExtensionData(null);
		tag_class.setResult("OK");
		resp.setTag_class(tag_class);
		return resp;
	}

	@Override
	@UrlPathMethod(name = "获取", path = "/Store/GetAdvertising")
	public CmsLineResp get(String json) throws Exception {
		GetAdvertisingReq req = BeanUtils.jsonToBean(json, GetAdvertisingReq.class); 
		return req.get();
	}

	@Override
	@UrlPathMethod(name = "更新", path = "/Store/EditAdvertising")
	public CmsLineResp update(String json) throws Exception {
		EditAdvertisingReq req = BeanUtils.jsonToBean(json, EditAdvertisingReq.class);
		return req.edit();
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		GetAdvertisingResp resp = BeanUtils.jsonToBean(jsonReqStr, GetAdvertisingResp.class);
		return resp.getAdvertising();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
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
