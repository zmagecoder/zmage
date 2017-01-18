package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.cms.consts.CmsConst;


public class CmsAdminUrlPraser implements UrlPraser {
	public String perform(HttpServletRequest req,HttpServletResponse resp,String uri) throws Exception {
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		if(uri.equals("/Admin/Preview")) //预览模式
		{
			cmsService.getCmsContext().setIs_preview(true);
			if(CmsConst.BAR_TYPE_STATIC.equals(cmsService.getCmsContext().getBar_type())){
				//预览模式缺省预览PC模式的
				cmsService.getCmsContext().setIs_preview(false);
			}
			cmsService.getCmsContext().setDevice(CmsConst.DEVICE_0);
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_ADMIN_PREVIEW);
			cmsService.getCmsContext().resetApp();
			
		}else if(uri.equals("/Admin/esign")) //PC模式
		{
			cmsService.getCmsContext().setDevice(CmsConst.DEVICE_0);
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_ADMIN_DESIGN);
			cmsService.getCmsContext().resetApp();
			cmsService.getCmsContext().setIs_preview(false);
			String page_id = req.getParameter("index_id");
			cmsService.getCmsContext().setPage_id(page_id);
			
		}else if(uri.equals("/Admin/MobileDesign")) //手机模式
		{
			cmsService.getCmsContext().setDevice(CmsConst.DEVICE_1);
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_MOBILE_DESIGN);
			cmsService.getCmsContext().setIs_preview(false);
			cmsService.getCmsContext().resetApp();
		}else if(uri.equals("/Home/Preview")) //手机商城预览模式add by wui
		{
			cmsService.getCmsContext().setIs_preview(true);
			if(CmsConst.BAR_TYPE_STATIC.equals(cmsService.getCmsContext().getBar_type())){
				//手机预览模式
				cmsService.getCmsContext().setIs_preview(false);
			}
			cmsService.getCmsContext().setDevice(CmsConst.DEVICE_1);
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_MOBILE_PREVIEW);
			cmsService.getCmsContext().resetApp();
		}
		else if(uri.equals("/Admin/BasicSet_Start")) //后台管理
		{
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_ADMIN_BACKSTAGE);
			cmsService.getCmsContext().setIs_preview(false);
			cmsService.getCmsContext().resetApp();
		}else if(uri.equals("/Admin/Static")) //后台管理
		{
			cmsService.getCmsContext().setBar_type(CmsConst.BAR_TYPE_STATIC);
			cmsService.getCmsContext().setIs_preview(false);
		}else if(uri.equals("/Admin/LogOff")) //退出处理
		{
			
		}else if(uri.equals("/AdminOperation/GetMobilePreviewUrl")){
			//保存生成二维码预览展示 add by wui TODO
		}
		
		//TODO 待改造处理
		//获取登录信息验证
		if(uri.equals("/AdminOperation/GetStoreNewStatus")) {
			
		}
		
		//页面地址重定向处理
		String url =cmsService.getCmsContext().getAppDefaultPageUrl();
		
		url = url.replaceAll("//", "/");
		resp.sendRedirect(url);
		return "true";
	}
}
