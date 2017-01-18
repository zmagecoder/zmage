package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.platform.framework.config.EopSetting;

/**
 * 搜索页面重新跳转
 * @author xuefeng
 */
public class CmsSearchUrlPraser implements UrlPraser{
	public String perform(HttpServletRequest req,HttpServletResponse resp,String urlPath) {
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		try{
			Long relId = cmsService.getCmsContext().getApp().getRel_id();

			String page_id =  "";//CacheUtils.getConfigInfo(CmsConst.CMS_BUSINESS_WSSQUERY);
			String url = EopSetting.CONTEXT_PATH+"/wssquery-page_id-" + page_id;

			//搜索关键字
			String key = req.getParameter("key");
			if(null != key && !"".equals(key))
				url += "-keyword-" + key;
			
			//目录展示
			String c3 = req.getParameter("c3");				//三级目录
			String c2 = req.getParameter("c2");				//二级目录
			String c1 = req.getParameter("c1");				//一级目录

			if(null != c3 || null != c2 || null != c1){
				String cat_id = (c3 == null ? (c2 == null ? c1 : c2) : c3);
				url += "-cat-" + cat_id;
			}
			url += ".html"; //门户页面
			resp.sendRedirect(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "true";
	}
}
