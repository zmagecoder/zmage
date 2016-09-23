package com.mage.cms.common.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.model.AppPage;
import com.mage.cms.common.params.news.vo.Page;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.DeleteStorePageReq;
import com.mage.cms.common.params.pagestore.req.GetStorePageReq;
import com.mage.cms.common.params.pagestore.req.PublishStorePageReq;
import com.mage.cms.common.params.pagestore.req.RecoverStorePageReq;
import com.mage.cms.common.params.pagestore.req.SaveStorePageReq;
import com.mage.cms.common.params.pagestore.req.StorePageReq;
import com.mage.cms.common.params.pagestore.req.UpdateStorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.params.pagestore.resp.DeleteStorePageResp;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.pagestore.resp.PublishStorePageResp;
import com.mage.cms.common.params.pagestore.resp.RecoverStorePageResp;
import com.mage.cms.common.params.pagestore.resp.SaveStorePageResp;
import com.mage.cms.common.params.pagestore.resp.UpdateStorePageResp;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.database.BaseSupport;
import com.mage.platform.framework.utils.ManagerUtils;

@Service
@SuppressWarnings("all")
public class CmsPageManager extends BaseSupport implements ICmsPageManager{

	public GetStorePageResp getStorePage(GetStorePageReq req){
		GetStorePageResp resp = new GetStorePageResp();
		CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
		try{
			String sql = "select a.device, a.keyword, a.sort \"order\" , a.isshow, c.page_id indexid, c.page_id pageId, b.save_level saveLevel, " +
				"a.title, a.page_type type, a.page_name name, a.page_url, a.description from es_cms_page a, es_cms_app_tpl b, " +
				"es_cms_app_tpl_rel_page c where a.disable = '0' and a.isshow = 'true' " +
				"and a.id = c.page_id and b.app_id = c.app_id and c.app_id = ? and c.nrecord_state <> '" + CmsConst.NRECORD_STATE_D + "' and b.save_level = ? and b.device = ? " +
				"and b.disabled = '0' and a.page_type = ? and b.source_from = c.source_from and c.source_from = a.source_from and a.source_from = '" + ManagerUtils.getSourceFrom() + "'  " +
				"innerString order by a.sort";
			if( CmsConst.STORE_PAGE_TYPE_0.equals(req.getType()))
				sql = sql.replaceAll("innerString", " and exists(select 1 from es_site_menu m where a.id = m.pageid)");
			else {
				sql = sql.replaceAll("innerString", "");
			}
			List<StorePageReq> list = this.getExecutor().queryForList(sql, StorePageReq.class, cmsContext.getApp().getApp_id(), req.getSave_level(), req.getDevice(), req.getType());
			resp.setStore_page(list);
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
			resp.setError("getStorePage：查询列表信息失败");
			e.printStackTrace();
		}
		return resp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public AddStorePageResp addStorePage(AddStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetStorePageResp getStorePageByAppId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateStorePageResp updateStorePage(UpdateStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteStorePageResp deleteStorePage(DeleteStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublishStorePageResp publishStorePage(PublishStorePageReq req) {
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
	public List<Page> getStorePage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddStorePageResp addPublishStorePage(AddStorePageReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Page> getStoreNewsPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppPage getPageById(String page_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
