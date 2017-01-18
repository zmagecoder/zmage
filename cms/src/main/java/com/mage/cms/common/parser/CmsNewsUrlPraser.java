package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.platform.framework.config.EopSetting;

/**
 * 新闻页面重新跳转
 * @author xuefeng
 */
public class CmsNewsUrlPraser implements UrlPraser{
	public String perform(HttpServletRequest req,HttpServletResponse resp,String urlPath) {
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		try{
			String pageId = "";
			if(urlPath.equals("/Home/News")){
				pageId = req.getParameter("id");
			}else if(urlPath.equals("/Home/Index")){
				pageId = req.getParameter("index_id");
			}
			
			Long relId = cmsService.getCmsContext().getApp().getRel_id();
			String url = EopSetting.CONTEXT_PATH+"/sshop-page_id-" + pageId + ".html"; //门户页面
			resp.sendRedirect(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "true";
	}
}
