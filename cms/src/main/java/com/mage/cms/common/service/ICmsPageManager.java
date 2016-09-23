package com.mage.cms.common.service;

import java.util.List;

import com.mage.cms.common.model.AppPage;
import com.mage.cms.common.params.news.vo.Page;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.DeleteStorePageReq;
import com.mage.cms.common.params.pagestore.req.GetStorePageReq;
import com.mage.cms.common.params.pagestore.req.PublishStorePageReq;
import com.mage.cms.common.params.pagestore.req.RecoverStorePageReq;
import com.mage.cms.common.params.pagestore.req.SaveStorePageReq;
import com.mage.cms.common.params.pagestore.req.UpdateStorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.params.pagestore.resp.DeleteStorePageResp;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.pagestore.resp.PublishStorePageResp;
import com.mage.cms.common.params.pagestore.resp.RecoverStorePageResp;
import com.mage.cms.common.params.pagestore.resp.SaveStorePageResp;
import com.mage.cms.common.params.pagestore.resp.UpdateStorePageResp;

/**
 * CMS 菜单管理器
 * @author pzh
 * @date 2016年6月3日 下午2:54:29
 */
public interface ICmsPageManager {
	
	/**
	 * 查询前端菜单列表
	 * @author pzh
	 * @date 2016年6月3日 下午3:17:41
	 * @param req
	 * @return
	 */
	public GetStorePageResp getStorePage(GetStorePageReq req);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public AddStorePageResp addStorePage(AddStorePageReq req);
	
	public GetStorePageResp getStorePageByAppId();
	
	public UpdateStorePageResp updateStorePage(UpdateStorePageReq req);
	
	public DeleteStorePageResp deleteStorePage(DeleteStorePageReq req);
	
	public PublishStorePageResp publishStorePage(PublishStorePageReq req);
	
	public SaveStorePageResp saveStorePage(SaveStorePageReq req);
	
	public RecoverStorePageResp recoverStorePage(RecoverStorePageReq req);
	
	public List<Page> getStorePage();
	
	public AddStorePageResp addPublishStorePage(AddStorePageReq req);
	
	public List<Page> getStoreNewsPage();
	
	public AppPage getPageById(String page_id);
}
