package com.mage.cms.widget.web;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.StoreLayOutResp;
import com.mage.cms.common.params.photo.req.CorpPhotoReq;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms挂件
 * 
 * @author sguo 2014-6-20
 */
@UrlPathService(pluginType="",name="图片裁剪")
@Service
public class ShopCorpPhotoWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {

	}

	protected void config(Map<String, String> params) {

	}
	
    @UrlPathMethod(name = "添加", path = "")
	public CmsLineResp add(String json) throws Exception {
		return null;
	}

	@Override
	public CmsLineResp delete(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@UrlPathMethod(name = "编辑", path = "/AdminOperation/CorpPhoto")
	public CmsLineResp edit(String json) throws Exception {
		json = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
		CorpPhotoReq corpPhotoReq = BeanUtils.jsonToBean(json, CorpPhotoReq.class);
		return corpPhotoReq.edit();
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CmsLineResp get(String json) throws Exception {
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
