package com.mage.cms.widget.web;

import java.util.ArrayList;
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
import com.mage.cms.common.params.news.req.EditNewsGroupReq;
import com.mage.cms.common.params.news.req.GetNewsGroupReq;
import com.mage.cms.common.params.news.resp.GetNewsGroupResp;
import com.mage.cms.common.params.news.resp.TextNewsResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * cms挂件
 * 
 * @author sguo 2014-6-25
 */
@UrlPathService(pluginType="9",name="图片新闻")//与文字新闻区别
@Service
public class ShopImageNewsWidget extends AbstractCmsWidget {

	@Override
	protected void display(Map<String, String> params) {
		//this.putData("data", this.storeLay.getData());//存储数据
		this.freeMarkerPaser.setPageName("ShopImageNewsWidgetStatic");
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout9")//与文字新闻区别
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq req = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return req.add(new CmsLayOutContentPraser() {
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				Long pluginId = modual.getPluginId();
				String storeId = CmsLineOperator.getStoreId();
				TextNewsResp resp = new TextNewsResp();
				GetNewsGroupResp news_group = new GetNewsGroupResp();
				news_group.setExtensionData(null);
				news_group.setDirection("2");
				news_group.setId(String.valueOf(pluginId));
				news_group.setInterval("20000");
				news_group.setNews(new ArrayList());
				news_group.setRoll(false);
				news_group.setShowTitle(new Integer("3"));
				news_group.setStoreId(storeId);
				news_group.setStyle(0);
				news_group.setTitle("推荐阅读");
				news_group.setType("1");//与文字新闻区别
				resp.setNews_group(news_group);
				String content = BeanUtils.beanToJson(resp);
//				content = "{\"success\":true,\"news_group\":{\"ExtensionData\":null,\"Direction\":2,\"Id\":582125,\"Interval\":2000,\"News\":[],\"Roll\":false,\"ShowTitle\":3,\"StoreId\":16538,\"Style\":0,\"Title\":\"推荐阅读\",\"Type\":0}}";
				return content;
			}
		});
	}

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout9")//与文字新闻区别
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		return null;
	}

	@Override
	@UrlPathMethod(name = "获取", path = "/Store/GetNewsGroup9")
	public CmsLineResp get(String json) throws Exception {
		GetNewsGroupReq req = BeanUtils.jsonToBean(json, GetNewsGroupReq.class); 
		return req.get();
	}

	@Override
	@UrlPathMethod(name = "更新", path = "/Store/EditNewsGroup9")
	public CmsLineResp update(String json) throws Exception {
		EditNewsGroupReq req = BeanUtils.jsonToBean(json, EditNewsGroupReq.class);
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
	public CmsLineResp getRespByContent(String json) throws Exception {
		TextNewsResp cmsResp = BeanUtils.jsonToBean(json, TextNewsResp.class);
		return cmsResp.getNews_group();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
		
		GetNewsGroupResp cmsResp = (GetNewsGroupResp) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(cmsResp.getShowTitle());
		vo.setStyle(new Integer(cmsResp.getStyle()));
		vo.setTitle(cmsResp.getTitle());
		return vo;
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}

}
