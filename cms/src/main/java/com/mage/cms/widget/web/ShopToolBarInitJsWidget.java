package com.mage.cms.widget.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.nav.vo.StoreNav;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.service.IApplicationManager;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.consts.CmsConst;
import com.mage.cms.utils.CmsUtil;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.utils.ManagerUtils;

/**
 * cms挂件
 * 
 * @author wui 2014-6-16
 */
@Service
public class ShopToolBarInitJsWidget extends AbstractCmsWidget {
		
	@Resource
	private ICmsPageManager cmsPageManager;
	@Resource
	private IApplicationManager applicationManager;
	
	protected void display(Map<String, String> params) {
		String page_list = "[{\"Description\":\"\",\"Device\":\"0\",\"ExtensionData\":null,\"IndexId\":\"100000\",\"IsShow\":true,\"Keyword\":\"\",\"Name\":\"PC商城\",\"Order\":\"1\",\"PageId\":\"100000\",\"Page_url\":\"sshop.html\",\"SaveLevel\":\"2\",\"Store_page_type\":\"\",\"Title\":\"\",\"Type\":\"0\"}]";
		this.putData("page_list", page_list);
		//快捷导航数据
		this.loadStoreNav();
	}

	protected void config(Map<String, String> params) {

	}

	@Override
	public CmsLineResp add(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp delete(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
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


	@Override
	public CmsLineResp get(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp update(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
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
	
	/**
	 * 加载快捷菜单数据
	 */
	private void loadStoreNav(){
		String store_nav = "{\"Class1Sum\":1,\"Class2Sum\":1,\"Class3Sum\":1,\"Error\":\"\",\"ExtensionData\":null,\"StoreNavClass1\":[],\"Success\":true}";
		this.putData("store_nav",store_nav);
	}
}
