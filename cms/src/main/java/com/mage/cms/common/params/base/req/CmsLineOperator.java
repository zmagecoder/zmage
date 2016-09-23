package com.mage.cms.common.params.base.req;

import java.util.List;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.resp.AddStoreLayOutResp;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.base.resp.DeleteStoreLayoutResp;
import com.mage.cms.common.params.base.resp.GetStoreLayOutResp;
import com.mage.cms.common.params.base.resp.PublishrStoreLayOutResp;
import com.mage.cms.common.params.base.resp.UpdateStoreLayoutResp;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.DeleteStorePageReq;
import com.mage.cms.common.params.pagestore.req.GetStorePageReq;
import com.mage.cms.common.params.pagestore.req.RecoverStorePageReq;
import com.mage.cms.common.params.pagestore.req.SaveStorePageReq;
import com.mage.cms.common.params.pagestore.req.UpdateStorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.params.pagestore.resp.DeleteStorePageResp;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.pagestore.resp.RecoverStorePageResp;
import com.mage.cms.common.params.pagestore.resp.SaveStorePageResp;
import com.mage.cms.common.params.pagestore.resp.UpdateStorePageResp;
import com.mage.cms.common.parser.CmsLayOutContentPraser;
import com.mage.cms.common.service.IApplicationManager;
import com.mage.cms.common.service.ICmsBaseManager;
import com.mage.cms.common.service.ICmsLineOperator;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.common.service.ICmsPageTplManager;
import com.mage.platform.framework.database.BaseSupport;

/**
 * cms 核心处理器
 * @author pzh
 * @date 2016年6月3日 下午2:42:45
 */
@SuppressWarnings("rawtypes")
public class CmsLineOperator extends BaseSupport implements ICmsLineOperator {
	
	IApplicationManager applicationManager;
	
	ICmsBaseManager cmsBaseManager;
	
	ICmsPageTplManager cmsPageTplManager;
	
	ICmsPageManager cmsPageManager;
	
	@Override
	public GetStorePageResp getStorePage(GetStorePageReq req) throws Exception {
		
		return cmsPageManager.getStorePage(req);
	}
	
	@Override
	public GetStoreLayOutResp getPageStoreLayout(GetStoreLayOutReq cmsReq)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AddStoreLayOutResp addStoreLayOut(CmsLayOutContentPraser cntParser,
			AddStoreLayOutReq addStoreLayOutReq) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AddStorePageResp addStorePage(AddStorePageReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UpdateStorePageResp updateStorePage(UpdateStorePageReq req)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeleteStorePageResp deleteStorePage(DeleteStorePageReq req)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SaveStorePageResp saveStorePage(SaveStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public RecoverStorePageResp recoverStorePage(RecoverStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UpdateStoreLayoutResp updateStoreLayout(UpdateStoreLayoutReq cmsReq)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Modual getEditModualByPluginId(String pluginid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Modual getEditModualByStoreLayoutId(String storeLayoutId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Modual getSingleModual(List<Modual> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void editModual(String pluginId, String storeLayoutId,
			String content, Modual modual) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public CmsLineResp getRecentStoreLayout(String pluginId, Class clazz)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeleteStoreLayoutResp deleteStoreLayOut(DeleteStoreLayOutReq cmsReq)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PublishrStoreLayOutResp publishStoreLayOut() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void publishStoreModuals(List<String> pageIds) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void recoverStoreModual(List<String> pageIds) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveStoreModual(List<String> pageIds) {
		// TODO Auto-generated method stub
		
	}
	
	public static String getStoreId(){
		
		return "";
	}
}
