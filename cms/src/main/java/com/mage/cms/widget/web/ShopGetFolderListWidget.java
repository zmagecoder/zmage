package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.photo.req.GetFolderReq;
import com.mage.cms.common.params.photo.resp.GetFolderResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
/**
 * cms获取图片文件路径
 * 
 * @author sguo 2014-6-23
 */
@UrlPathService(pluginType="",name="获取图片文件路径")
@Service
public class ShopGetFolderListWidget extends AbstractCmsWidget {

	@Override
	protected void display(Map<String, String> params) {
		// TODO Auto-generated method stub
		
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
	@UrlPathMethod(name = "图片文件路径", path = "/AdminOperation/GetFolderList")
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		GetFolderResp resp = new GetFolderResp();
		GetFolderReq req = new GetFolderReq();
		resp = req.edit();
		return resp;
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
