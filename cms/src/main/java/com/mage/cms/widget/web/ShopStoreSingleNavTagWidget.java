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
import com.mage.cms.common.params.nav.req.EditStoreNavigationReq;
import com.mage.cms.common.params.nav.req.GetStoreNavigationReq;
import com.mage.cms.common.params.nav.resp.GetStoreNavigationResp;
import com.mage.cms.common.params.nav.vo.Navigation;
import com.mage.cms.common.params.nav.vo.StoreNav;
import com.mage.cms.common.params.vo.StaticLayOutExtVo;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.consts.CmsConst;
import com.mage.cms.widget.AbstractCmsWidget;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.utils.MapUtils;
/**
 * cms挂件
 * 
 * @author pzh 2014-07-11
 */
@UrlPathService(pluginType="6",name="纵向单导航")
@Service
public class ShopStoreSingleNavTagWidget extends AbstractCmsWidget {

	@Override
	protected void display(Map<String, String> params) {
//		Navigation navigation = (Navigation) this.storeLay.getData();
//		StoreNav storeNav = getStoreNav(navigation.getClassId(), navigation.getClassLevel());
//		navigation.setStoreNav(storeNav);
//		this.putData("data", navigation);
		this.freeMarkerPaser.setPageName("ShopStoreSingleNavTagWidgetStatic");
	}

	@Override
	@UrlPathMethod(name = "添加", path = "/Store/AddStoreLayout6")
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
				nav.setExtensionData(null);
				nav.setClassId(0);
				nav.setClassLevel(0);
				nav.setContent(null);
				nav.setContentJson(null);
				nav.setNavId(String.valueOf(pluginId));
				nav.setShowTitle(3);
				nav.setStoreId(storeId);
				nav.setStyle(0);
				nav.setTitle("请填写标题名称");
				nav.setType(0);
				
				resp.setNav(nav);
				String content = BeanUtils.beanToJson(resp);
				return content;
			}
		});
	}

	@Override
	@UrlPathMethod(name = "删除", path = "/Store/DeleteStoreLayout6")
	public CmsLineResp delete(String json) throws Exception {
		DeleteStoreLayOutReq cmsReq = BeanUtils.jsonToBean(json, DeleteStoreLayOutReq.class);
		return cmsReq.delete();
	}

	@Override
	@UrlPathMethod(name = "获取", path = "/Store/GetStoreNavigation6")
	public CmsLineResp get(String json) throws Exception {
		GetStoreNavigationReq req = BeanUtils.jsonToBean(json, GetStoreNavigationReq.class); 
		return req.get();
	}

	@Override
	@UrlPathMethod(name = "更新", path = "/Store/EditStoreNavigation6")
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
	public StaticLayOutExtVo getStcStoreLayOutParamter(StoreLayOutResp storeLayOutResp)throws Exception {
		Navigation navigation = (Navigation) storeLayOutResp.getData();
		StaticLayOutExtVo vo  = new StaticLayOutExtVo();
		vo.setShowTitle(navigation.getShowTitle());
		vo.setStyle(new Integer(navigation.getStyle()));
		vo.setTitle(navigation.getTitle());
		return vo;
	}
	
	public boolean  staticFreeMarker(){
		return true;
	}

	@Override
	public CmsLineResp edit(String jsonReqStr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private StoreNav getStoreNav(Integer classId, Integer classLevel){
		StoreNav storeNav = (StoreNav)ThreadContextHolder.getSessionContext().getAttribute(CmsConst.CMS_STORE_NAV_DATA);
		List<Map<String, Object>> navClass1List = storeNav.getStoreNavClass1();
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		try{
			if(null != navClass1List && !navClass1List.isEmpty()){
				for(int i = 0; i < navClass1List.size(); i++){
					if(retList.size() >= 1)			//只可能有一条数据
						break;
					Map<String, Object> navClass1Map = navClass1List.get(i);
					if(null != navClass1Map && !navClass1Map.isEmpty()){
						if(classLevel == 2){//展示第三级菜单
							List<Map<String, Object>> navClass2List = (List<Map<String, Object>>)navClass1Map.get("StoreNavClass2");
							if(null != navClass2List && !navClass2List.isEmpty()){
								for(int j = 0; j < navClass2List.size(); j++){
									
									Map<String, Object> navClass2Map = navClass2List.get(j);
									if(MapUtils.getString(navClass2Map, "ID", "").equals(classId + "") ){
										retList.add(navClass1Map);
										break;
									}
								}
							}
						}else {			//展示第一级菜单
							if(MapUtils.getString(navClass1Map, "ID", "").equals(classId + "") ){
								retList.add(navClass1Map);
								break;
							}
						}
					}
				}
			}
			storeNav.setStoreNavClass1(retList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return storeNav;
	}
}
