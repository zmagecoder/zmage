package com.mage.cms.common.service;

import com.mage.cms.common.params.template.resp.GetStoreTemplateResp;

/**
 * 应用处理器
 * @author pzh
 * @date 2016年6月3日 下午2:51:03
 */
public interface IApplicationManager {
	
	/**
	 * 根据界面id 获取模板
	 * @param page_id
	 * @return
	 */
	public GetStoreTemplateResp getTemplateByPageId(String page_id);
	
	/**
	 * 获取应用模板
	 * @return
	 */
	public GetStoreTemplateResp getAppTemplate();
	
}
