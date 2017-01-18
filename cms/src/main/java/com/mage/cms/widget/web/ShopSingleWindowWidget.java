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
import com.mage.cms.common.params.goods.vo.SimpleGoods;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.params.window.resp.GetShowcaseResp;
import com.mage.cms.common.params.window.vo.Label;
import com.mage.cms.common.params.window.vo.Showcase;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.consts.CmsConst;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.utils.BeanUtils;

/**
 * cms单标签橱窗插件
 * @author pzh 2014-6-27
 */
@UrlPathService(pluginType="2",name="橱窗插件")
@Service
public class ShopSingleWindowWidget extends AbstractCmsWidget {
	protected void display(Map<String, String> params) {
		//this.putData("data", this.storeLay.getData());//存储数据
		this.freeMarkerPaser.setPageName("ShopSingleWindowWidgetStatic");
	}
	
	protected void config(Map<String, String> params) {

	}
	
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout2")
	public CmsLineResp add(String json) throws Exception {
		AddStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, AddStoreLayOutReq.class);
		return cmsReq.add(new CmsLayOutContentPraser() {
			//add by wui构造入参信息
			@Override
			public String genModualCnt(Modual modual) {
				//构建content字段对象
				String storeId = CmsLineOperator.getStoreId();
				Long pluginId = modual.getPluginId();
				GetShowcaseResp getShowcaseResp = new GetShowcaseResp();
				Showcase showcase = new Showcase();				
				showcase.setTitle("请填写标题名称");
				showcase.setType(CmsConst.WINDOW_SINGLE_TYPE_0);
				showcase.setStyle(0);
				showcase.setShowTitle(3);
				showcase.setGoodsStyle(0);
				showcase.setDirection(1);
				showcase.setInterval(2000);
				showcase.setRoll(false);
				Label label = new Label();
				label.setGoods(new ArrayList<SimpleGoods>());
				label.setId(new ArrayList<String>());
				label.setName("请填写标题名称");
				List<Label> labels = new ArrayList<Label>();
				labels.add(label);
				showcase.setLabels(labels);
				showcase.setStoreId(Long.valueOf(storeId));
				showcase.setShowcaseId(pluginId);
				getShowcaseResp.setSuccess(true);
				getShowcaseResp.setShowcase(showcase);
				
				String content = BeanUtils.beanToJson(getShowcaseResp);
				return content;
			}
		});
	}

	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout2")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}
	
	@Override
	public CmsLineResp edit(String jsonReqStr) throws Exception {
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
	public CmsLineResp getRespByContent(String jsonReqStr) throws Exception {
		GetShowcaseResp cmsResp = BeanUtils.jsonToBean(jsonReqStr, GetShowcaseResp.class);
		return cmsResp.getShowcase();
	}

	@Override
	public CmsLineResp get(String jsonReqStr) throws Exception {
		
		return null;
	}

	@Override
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp)throws Exception {
		Showcase cmsResp = (Showcase) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(cmsResp.getShowTitle());
		vo.setStyle(new Integer(cmsResp.getStyle()));
		vo.setTitle(cmsResp.getTitle());
		vo.setSearchKeyDisplay(cmsResp.getSearchKeyDisplay());
		return vo;
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}

}
