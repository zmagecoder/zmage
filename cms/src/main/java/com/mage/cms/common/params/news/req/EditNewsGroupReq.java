package com.mage.cms.common.params.news.req;

import java.io.Serializable;
import java.util.List;

import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.news.resp.GetNewsGroupResp;
import com.mage.cms.common.params.news.resp.TextNewsResp;
import com.mage.cms.common.params.news.vo.News;
import com.mage.cms.common.params.pagestore.req.AddStorePageReq;
import com.mage.cms.common.params.pagestore.req.StorePageReq;
import com.mage.cms.common.params.pagestore.resp.AddStorePageResp;
import com.mage.cms.common.service.ICmsPageManager;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.utils.BeanUtils;

public class EditNewsGroupReq extends CmsLineReq implements Serializable{
	
	private static final long serialVersionUID = -4551533968893355738L;
	
	private GetNewsGroupResp news_group;
	
	public GetNewsGroupResp getNews_group() {
		return news_group;
	}

	public void setNews_group(GetNewsGroupResp news_group) {
		this.news_group = news_group;
	}

	public CmsLineResp edit() throws Exception{
		TextNewsResp resp = new TextNewsResp();
		Modual modual = this.getEditModualByPluginId(this.news_group.getId());
		try{
			createNewLink(this.getNews_group());
			modual.setNrecord_state(CmsConst.NRECORD_STATE_M);
			TextNewsResp textNewsResp = new TextNewsResp();
			textNewsResp.setNews_group(news_group);
			editModual(this.news_group.getId(),null,BeanUtils.beanToJson(textNewsResp),modual);
			resp.setSuccess(true);
		}catch(Exception e){
			resp.setSuccess(false);
		}
		return resp;
	}
	
	/**
	 * 创建新闻页面
	 * @param jsonReqStr
	 * @throws Exception
	 */
	public TextNewsResp createNewLink(GetNewsGroupResp newsGroup) throws Exception {
		TextNewsResp resp = new TextNewsResp();
		resp.setNews_group(newsGroup);
		List<News> news = newsGroup.getNews();
		for(News n : news){
			if(n.getId().equals("0")){
				String newsId = this.getExecutor().getSequences("ES_CMS_NEWS", "1", 16);
				n.setId(newsId);
			}
			if(CmsConst.CMS_TEXT_NEWS_LINKTYPE.equals(n.getLinkType() + "")){
				if(n.getIndexId() == null || n.getIndexId().equals("")){			//不存在页面才添加
					
					AddStorePageReq req = new AddStorePageReq();
					req.setPublish_flag(true);
					StorePageReq store_page = new StorePageReq();
					req.setStore_page(store_page);
					store_page.setDevice(CmsConst.DEVICE_0);
					store_page.setName(n.getTitle());
					store_page.setTitle(n.getTitle());
					store_page.setKeyword(n.getAbstract());
					store_page.setDescription(n.getDescription());
					store_page.setType(CmsConst.STORE_PAGE_TYPE_2);
					store_page.setIsShow("true");
					store_page.setOrder(n.getOrder());
					ICmsPageManager cmsPageManager = SpringContextHolder.getBean("cmsPageManager");
					AddStorePageResp pageResp = cmsPageManager.addPublishStorePage(req);
					n.setIndexId(pageResp.getApp_page().getId());
				}
				
			}else {
				n.setIndexId(n.getLinkParam());					//设置新闻id
			}
		}
		return resp;
	}
}
