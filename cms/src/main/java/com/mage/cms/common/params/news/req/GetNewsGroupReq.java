package com.mage.cms.common.params.news.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.news.resp.TextNewsResp;

public class GetNewsGroupReq extends CmsLineReq{
	private String news_group_id;
	
	public String getNews_group_id() {
		return news_group_id;
	}
	
	public void setNews_group_id(String news_group_id) {
		this.news_group_id = news_group_id;
	}
	
	public TextNewsResp get() throws Exception{
		return (TextNewsResp) getRecentStoreLayout(this.news_group_id,TextNewsResp.class);
	}
}
