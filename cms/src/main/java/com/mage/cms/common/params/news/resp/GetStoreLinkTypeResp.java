package com.mage.cms.common.params.news.resp;

import java.io.Serializable;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.news.vo.LinkTypeInfo;

public class GetStoreLinkTypeResp extends CmsLineResp implements Serializable{
	private LinkTypeInfo link_type_info;

	public LinkTypeInfo getLink_type_info() {
		return link_type_info;
	}

	public void setLink_type_info(LinkTypeInfo link_type_info) {
		this.link_type_info = link_type_info;
	}
	
}
