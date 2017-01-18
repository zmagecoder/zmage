package com.mage.cms.common.params.pagestore.resp;


import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.mage.cms.common.params.base.resp.CmsLineResp;
/**
 * 导航菜单更新
 * @author pzh
 * 
 */
public class PublishStorePageResp  extends CmsLineResp implements Serializable {
	
	private List<String> pageIds;

	@JSONField(serialize = false)
	public List<String> getPageIds() {
		return pageIds;
	}
	
	public void setPageIds(List<String> pageIds) {
		this.pageIds = pageIds;
	}
	
}
