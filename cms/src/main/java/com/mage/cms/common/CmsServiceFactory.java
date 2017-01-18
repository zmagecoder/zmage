package com.mage.cms.common;

import com.mage.cms.common.service.ICmsLineService;
import com.mage.cms.common.service.impl.CmsLineServiceImpl;

/**
 * CMS服务工厂，返回服务服务
 * @author pzh
 *
 */
public final class CmsServiceFactory {
	
	private static ICmsLineService cmsLineService;
	
	public static void set(ICmsLineService _cmslineService){
		cmsLineService =_cmslineService;
	}
	
	public static ICmsLineService getCmsLineService(){
		if(cmsLineService != null){
            return cmsLineService;
        }
		return new CmsLineServiceImpl();
	}
}



