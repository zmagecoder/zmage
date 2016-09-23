package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.DefaultCmsBeanDefine;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.utils.CmsUtil;
import com.mage.common.ServiceMethodHandler;
import com.mage.common.ZteError;
import com.mage.consts.ApiConsts;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.utils.CommonTools;


public class CmsPageUrlPraser  implements UrlPraser{
	public String perform(HttpServletRequest req,HttpServletResponse resp,String urlPath)throws Exception {
		DefaultCmsBeanDefine context = DefaultCmsBeanDefine.getInstance();
		String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
		String methodName = urlPath,version="1.0",jsonStrResp="OK";
		CmsLineResp cmsResp = new CmsLineResp();
		try {
			// 检测方法是否存在
			if (context.isValidMethodVersion(urlPath, version)) {
				ServiceMethodHandler serviceMethodHandler = context.getServiceMethodHandler(urlPath, version);
				cmsResp =  (CmsLineResp) serviceMethodHandler.getHandlerMethod().invoke(serviceMethodHandler.getHandler(),jsonReq);
			} else {
				CommonTools.addError("-1", "找不到服务");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			CommonTools.addError(new ZteError(ApiConsts.ERROR_FAIL, "==>调用"+ methodName + "失败," + e.getMessage()));
		}
		jsonStrResp = CmsUtil.firstToUpperCase(BeanUtils.beanToJson(cmsResp));
		jsonStrResp = jsonStrResp.replaceAll("\"Store_page\"", "\"store_page\"");
		jsonStrResp = jsonStrResp.replaceAll("\"Success\"", "\"success\"");
		jsonStrResp = jsonStrResp.replaceAll("\"True\"", "true");
		jsonStrResp = jsonStrResp.replaceAll("\"true\"", "true");
		return jsonStrResp;
	}
}
