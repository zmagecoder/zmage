package com.mage.cms.common.params.pagestore.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.RecoverStorePageResp;

/**
 * @author pzh
 * 页面恢复入参对象
 * 
 */
public class RecoverStorePageReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public RecoverStorePageResp recover(){
		return recoverStorePage(this);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
