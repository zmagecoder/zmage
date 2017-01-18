package com.mage.cms.common.service.impl;

import org.apache.log4j.Logger;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.service.ICmsLineService;

public final class CmsLineServiceImpl implements ICmsLineService {
	private CmsContext cmsContext;

	protected static final Logger logger = Logger.getLogger(CmsLineServiceImpl.class);
	
	/**
	 * 设置cms上下文
	 */
	public CmsLineServiceImpl() {
//		WebSessionContext<CmsContext> webSessionContext = ThreadContextHolder.getHttpSessionContext();
//		cmsContext = webSessionContext.getAttribute(CURRENT_CMSLINE_KEY);
//		if(cmsContext == null){
//			cmsContext = new CmsContext(webSessionContext.getSession().getId());
//			webSessionContext.setAttribute(CURRENT_CMSLINE_KEY, cmsContext);
//		}
	}
	
	/**
	 * 获取cms上下文
	 */
	public CmsContext getCmsContext() {
//		WebSessionContext<CmsContext> webSessionContext = ThreadContextHolder.getHttpSessionContext();
//		cmsContext = webSessionContext.getAttribute(CURRENT_CMSLINE_KEY);
		
		
		cmsContext = new CmsContext("123123231");
		return cmsContext;
	}
}
