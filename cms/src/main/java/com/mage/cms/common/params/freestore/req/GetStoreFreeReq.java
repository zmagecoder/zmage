package com.mage.cms.common.params.freestore.req;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.freestore.resp.GetStoreFreeResp;


/**
 * 查询对象入参
 * @author hu.uyi
 * @date 2014.06.19
 */
public class GetStoreFreeReq extends CmsLineReq{

	private String free_id;

	public String getFree_id() {
		return free_id;
	}

	public void setFree_id(String free_id) {
		this.free_id = free_id;
	}
	
	
	public CmsLineResp get() throws Exception{
		return getRecentStoreLayout(this.getFree_id(),GetStoreFreeResp.class);
	}
}
