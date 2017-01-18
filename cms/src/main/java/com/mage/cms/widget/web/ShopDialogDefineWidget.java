package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;

/**
 * cms挂件
 * 
 * @author wui 2014-6-16
 */
@Service
public class ShopDialogDefineWidget extends AbstractCmsWidget {

	protected void display(Map<String, String> params) {
		//快捷菜单导航数据加载(全分类)
		System.out.println("======================>>>>");
		
	}

	protected void config(Map<String, String> params) {

	}

	@Override
	public CmsLineResp add(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp delete(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp get(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) throws Exception {
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


}
