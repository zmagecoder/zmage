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
import com.mage.cms.common.params.nav.req.EditStoreNavigationReq;
import com.mage.cms.common.params.nav.req.GetStoreNavigationReq;
import com.mage.cms.common.params.nav.resp.GetStoreNavigationResp;
import com.mage.cms.common.params.nav.resp.GetTagClassResp;
import com.mage.cms.common.params.nav.vo.Content;
import com.mage.cms.common.params.nav.vo.Navigation;
import com.mage.cms.common.params.news.vo.TagClass;
import com.mage.cms.common.params.news.vo.TagClassInfo;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;
/**
 * cms挂件
 * 
 * @author sguo 2014-6-25
 */
@UrlPathService(pluginType="13",name="标签导航")
@Service
public class ShopStoreNavTagWidget extends AbstractCmsWidget {

	@Override
	protected void display(Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout13")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq req = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return req.add(new CmsLayOutContentPraser() {
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				Long pluginId = modual.getPluginId();
				String storeId = CmsLineOperator.getStoreId();
				GetStoreNavigationResp resp = new GetStoreNavigationResp();
				Navigation nav = new Navigation();
				resp.setNav(nav);
				nav.setExtensionData(null);
				nav.setClassId(0);
				nav.setClassLevel(0);
				Content content = new Content();
				content.setExtensionData(null);
				content.setTagClass(new ArrayList());
				nav.setContent(content);
				nav.setContentJson("{\"TagClass\":null}");
				nav.setNavId(String.valueOf(pluginId));
				nav.setShowTitle(3);
				nav.setStoreId(storeId);
				nav.setStyle(0);
				nav.setTitle("请填写标题名称");
				nav.setType(0);
				String strJson = BeanUtils.beanToJson(resp);
				return strJson;
			}
		});
	}

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout13")
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
	@UrlPathMethod(name = "获取", path = "/Store/GetStoreNavigation")
	public CmsLineResp get(String json) throws Exception {
		GetStoreNavigationReq req = BeanUtils.jsonToBean(json, GetStoreNavigationReq.class); 
		return req.get();
	}

	@Override
	@UrlPathMethod(name = "更新", path = "/Store/EditStoreNavigation")
	public CmsLineResp update(String json) throws Exception {
		EditStoreNavigationReq req = BeanUtils.jsonToBean(json, EditStoreNavigationReq.class);
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
		GetStoreNavigationResp resp = BeanUtils.jsonToBean(jsonReqStr, GetStoreNavigationResp.class);
		return resp.getNav();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(
			StoreLayOutResp storeLayOutResp) throws Exception {
		// TODO Auto-generated method stub
		return new StaticLayOutExtVo();
	}

}
