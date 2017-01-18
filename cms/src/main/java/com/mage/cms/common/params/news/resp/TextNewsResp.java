package com.mage.cms.common.params.news.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;

public class TextNewsResp extends CmsLineResp implements Serializable{
	private GetNewsGroupResp news_group;

	public GetNewsGroupResp getNews_group() {
		return news_group;
	}

	public void setNews_group(GetNewsGroupResp news_group) {
		this.news_group = news_group;
	}
	
}
