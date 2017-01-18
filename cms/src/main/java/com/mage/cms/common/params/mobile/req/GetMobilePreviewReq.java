package com.mage.cms.common.params.mobile.req;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.mobile.resp.GetMobilePreviewResp;
import com.mage.cms.common.params.pagestore.req.StorePageReq;
import com.mage.cms.consts.CmsConst;

public class GetMobilePreviewReq extends CmsLineReq implements Serializable{
	private String index_id;

	public String getIndex_id() {
		return index_id;
	}

	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}
	
	public CmsLineResp getPreview(){
		GetMobilePreviewResp resp = new GetMobilePreviewResp();
		CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
		String sql = ""; 		//SF.cmsSql("QRY_MOBILE_PREVIEW_PAGE");
		if(!StringUtils.isEmpty(index_id) && !"0".equals(index_id)){
			sql = sql.replaceAll("replace_str", "and a.id = " + index_id + "");
		}else {
			sql = sql.replaceAll("replace_str", "");
		}
		@SuppressWarnings("unchecked")
		List<StorePageReq> list = this.getExecutor().queryForList(sql, StorePageReq.class, 
				cmsContext.getApp().getApp_id(), CmsConst.DEVICE_1);
		if(null != list && !list.isEmpty()){
			resp.setUrl(list.get(0).getPage_url());
		}
		return resp;
	}
}
