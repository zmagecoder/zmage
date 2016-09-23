package com.mage.cms.common.params.pagestore.resp;


import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.mage.cms.common.params.base.resp.CmsLineResp;
/**
 * 菜单恢复
 * @author pzh
 * 
 */
public class RecoverStorePageResp  extends CmsLineResp implements Serializable {
	private List<String> pageIds;

	@JSONField(serialize = false)
	public List<String> getPageIds() {
		return pageIds;
	}

	public void setPageIds(List<String> pageIds) {
		this.pageIds = pageIds;
	}
	
}