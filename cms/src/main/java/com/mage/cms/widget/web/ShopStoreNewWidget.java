package com.mage.cms.widget.web;

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
import com.mage.cms.common.params.freestore.resp.GetStoreFreeResp;
import com.mage.cms.common.params.freestore.resp.StoreFreeResp;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms挂件
 * 
 * @author wui 2014-6-16
 */
@UrlPathService(pluginType="20",name="自由编辑插件")
@Service
public class ShopStoreNewWidget extends AbstractCmsWidget {
	
	protected void display(Map<String, String> params) {
		//静态模式数据设置
		//this.putData("data", this.storeLay.getData());//存储数据
		this.freeMarkerPaser.setPageName("ShopStoreNewWidgetStatic");
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}
	protected void config(Map<String, String> params) {

	}
	
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout20")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return cmsReq.add(new CmsLayOutContentPraser() {
			//add by wui构造入参信息
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				Long pluginId = modual.getPluginId();
				String storeId = CmsLineOperator.getStoreId();
				GetStoreFreeResp getStoreFreeResp = new GetStoreFreeResp();
				StoreFreeResp storeFreeResp = new StoreFreeResp();
				storeFreeResp.setContent("\u003cdiv\u003e请在这里填写你的内容\u003c/div\u003e");
				storeFreeResp.setShowTitle(1);
				storeFreeResp.setStyle("0");
				storeFreeResp.setTitle("请填写标题名称");
				storeFreeResp.setFreeId(String.valueOf(pluginId));
				storeFreeResp.setStoreId(String.valueOf(storeId));
				getStoreFreeResp.setSuccess(true);
				getStoreFreeResp.setFree(storeFreeResp);
				String content = BeanUtils.beanToJson(getStoreFreeResp);
				return content;
			}
		});
	}

	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout20")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	public CmsLineResp publish(String jsonReqStr) {
		// TODO Auto-generated method stub
		return null;
	}

	public CmsLineResp update(String json) throws Exception {
		return null;
	}

	@Override
	public CmsLineResp getRespByContent(String json) throws Exception {
		GetStoreFreeResp cmsResp = BeanUtils.jsonToBean(json, GetStoreFreeResp.class);
		return cmsResp.getFree();
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp)throws Exception {
		StoreFreeResp cmsResp = (StoreFreeResp) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(cmsResp.getShowTitle());
		vo.setStyle(new Integer(cmsResp.getStyle()));
		vo.setTitle(cmsResp.getTitle());
		return vo;
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
}
