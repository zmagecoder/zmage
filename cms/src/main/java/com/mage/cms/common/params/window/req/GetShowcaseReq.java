package com.mage.cms.common.params.window.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.window.resp.GetShowcaseResp;
/**
 * 
 * @author pzh
 * 读取橱窗入参
 */
public class GetShowcaseReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String showcase_id;

	public String getShowcase_id() {
		return showcase_id;
	}

	public void setShowcase_id(String showcase_id) {
		this.showcase_id = showcase_id;
	}
	
	public CmsLineResp get()throws Exception{
		return getRecentStoreLayout(this.getShowcase_id(),GetShowcaseResp.class);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
