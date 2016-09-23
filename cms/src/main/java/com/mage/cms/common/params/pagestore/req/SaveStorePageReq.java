package com.mage.cms.common.params.pagestore.req;

import java.io.Serializable;

import com.mage.cms.common.params.base.req.CmsLineReq;
import com.mage.cms.common.params.pagestore.resp.SaveStorePageResp;
/**
 * 
 * @author pzh
 * 页面添加入参对象
 * 
 */
public class SaveStorePageReq extends CmsLineReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	public SaveStorePageResp save(){
		return saveStorePage(this);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
