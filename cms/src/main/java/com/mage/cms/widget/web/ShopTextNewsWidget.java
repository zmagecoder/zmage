package com.mage.cms.widget.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.mage.cms.common.params.news.resp.GetStoreLinkTypeResp;
import com.mage.cms.common.params.news.resp.TextNewsResp;
import com.mage.cms.common.params.news.vo.Domain;
import com.mage.cms.common.params.news.vo.LinkTypeInfo;
import com.mage.cms.common.params.news.vo.Page;
import com.mage.cms.common.params.news.vo.SalesRule;
import com.mage.cms.common.params.news.vo.TagClass;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * cms挂件
 * 
 * @author sguo 2014-6-23
 */
@UrlPathService(pluginType="8",name="文字新闻")
@Service
public class ShopTextNewsWidget extends AbstractCmsWidget {

	@Resource
	private ICmsPageManager cmsPageManager;
	
	@Override
	protected void display(Map<String, String> params) {
		//this.putData("data", this.storeLay.getData());//存储数据
		this.freeMarkerPaser.setPageName("ShopTextNewsWidgetStatic");
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout8")
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
				news_group.setType("0");
				resp.setNews_group(news_group);
				String content = BeanUtils.beanToJson(resp);
				return content;
			}
		});
	}

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout8")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	@UrlPathMethod(name = "编辑", path = "/Store/GetStoreLinkType")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		GetStoreLinkTypeResp resp = new GetStoreLinkTypeResp();
		LinkTypeInfo link_type_info = new LinkTypeInfo();
		resp.setLink_type_info(link_type_info);
		
		List<Domain> storeDomain = new ArrayList<Domain>();
		Domain domain = new Domain(null,null,"0","17639","shop1653831.mpoo.cn","","17639","","0","16544","18082713555","16538","综合商城","5","54","chenyanan","陈亚楠","2");
		storeDomain.add(domain);
		link_type_info.setStoreDomain(storeDomain);
		
		//新闻连接页面
		List<Page> storeNews = cmsPageManager.getStoreNewsPage();
		link_type_info.setStoreNews(storeNews);
		//自定义页面
		List<Page> storePage = cmsPageManager.getStorePage();
		link_type_info.setStorePage(storePage);
		//促销页面
		List<SalesRule> storeSalesRule = new ArrayList<SalesRule>();
		link_type_info.setStoreSalesRule(storeSalesRule);
		//标签类
		List<TagClass> tagClass = new ArrayList<TagClass>();
		link_type_info.setTagClass(tagClass);
		
		return resp;
	}

	@Override
	@UrlPathMethod(name = "获取", path = "/Store/GetNewsGroup8")
	public CmsLineResp get(String json) throws Exception {
		GetNewsGroupReq req = BeanUtils.jsonToBean(json, GetNewsGroupReq.class); 
		return req.get();
	}

	@Override
	@UrlPathMethod(name = "更新", path = "/Store/EditNewsGroup8")
	public CmsLineResp update(String json) throws Exception {
		EditNewsGroupReq req = BeanUtils.jsonToBean(json, EditNewsGroupReq.class);
		return req.edit();
	}

	

	@Override
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		TextNewsResp resp = BeanUtils.jsonToBean(jsonReqStr, TextNewsResp.class);
		return resp.getNews_group();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp) throws Exception {
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

	@Override
	public CmsLineResp publish(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
