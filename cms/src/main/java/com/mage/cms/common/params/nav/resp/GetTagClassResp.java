package com.mage.cms.common.params.nav.resp;

import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.news.vo.TagClass;

public class GetTagClassResp extends CmsLineResp{
	private TagClass tag_class;

	public TagClass getTag_class() {
		return tag_class;
	}

	public void setTag_class(TagClass tag_class) {
		this.tag_class = tag_class;
	}
	
}
