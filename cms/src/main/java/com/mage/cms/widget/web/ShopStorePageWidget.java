package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.DeleteStorePageReq;
import com.mage.cms.common.params.pagestore.req.GetStorePageReq;
import com.mage.cms.common.params.pagestore.req.UpdateStorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.utils.CmsUtil;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms页面挂件
 * 
 * @author pzh 2014-6-19
 */
@UrlPathService(pluginType="101",name="页面新增插件")
@Service
public class ShopStorePageWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}

	protected void config(Map<String, String> params) {

	}
	
    @UrlPathMethod(name = "新增页面", path = "/Store/AddStorePage")
	public CmsLineResp add(String json) throws Exception {
    	AddStorePageReq cmsReq = BeanUtils.jsonToBean(json, AddStorePageReq.class);
    	AddStorePageResp resp = cmsReq.add();
    	
    	//重置缓存
    	ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		try{
			ICmsPageManager cmsPageManager = SpringContextHolder.getBean("cmsPageManager");
			GetStorePageResp storePage = cmsPageManager.getStorePageByAppId();
			cmsService.getCmsContext().setStorePage(storePage);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resp;
	}

	@Override
	@UrlPathMethod(name = "删除页面", path = "/Store/DeleteStorePage")
	public CmsLineResp delete(String jsonReqStr) throws Exception{
		DeleteStorePageReq cmsReq =BeanUtils.jsonToBean(jsonReqStr, DeleteStorePageReq.class);
		return cmsReq.delete();
	}

	@Override
	public CmsLineResp edit(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@UrlPathMethod(name = "获取页面列表", path = "/Store/GetStorePage")
	public CmsLineResp get(String jsonReqStr) throws Exception {
		GetStorePageReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, GetStorePageReq.class);
		return cmsReq.get();
	}

	@Override
	@UrlPathMethod(name = "更新页面布局,编辑页面", path = "/Store/UpdateStorePage")
	public CmsLineResp update(String jsonReqStr) throws Exception {
		jsonReqStr = CmsUtil.firstToLowerCase(jsonReqStr);
		UpdateStorePageReq cmsReq = BeanUtils.jsonToBean(jsonReqStr, UpdateStorePageReq.class);
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
