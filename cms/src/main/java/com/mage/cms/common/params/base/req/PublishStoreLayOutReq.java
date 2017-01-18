package com.mage.cms.common.params.base.req;

import com.mage.cms.common.params.base.resp.PublishrStoreLayOutResp;

/**
 * 发布页面入参
 * @author hu.yi
 * @date 2014.06.25
 */
public class PublishStoreLayOutReq extends CmsLineReq{

	
	public PublishrStoreLayOutResp publish() throws Exception{
		
		return publishStoreLayOut();
	}
}
