package com.mage.cms.common.parser;

import org.springframework.util.StringUtils;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.DefaultCmsBeanDefine;
import com.mage.cms.common.params.base.req.AddStoreLayOutReq;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.service.ICmsBaseManager;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.common.ServiceMethodHandler;
import com.mage.common.ZteError;
import com.mage.consts.ApiConsts;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.utils.CommonTools;

public class CmsTplPluginTypePraser {

	public void perform(String urlPath){
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		DefaultCmsBeanDefine context = DefaultCmsBeanDefine.getInstance();
		String methodName = urlPath,version="1.0",plugin_type;
		try {
			// 检测方法是否存在
			urlPath=warpperUrl(urlPath);
			if (context.isValidMethodVersion(urlPath, version)) {
				ServiceMethodHandler serviceMethodHandler = context.getServiceMethodHandler(urlPath, version);
				plugin_type = serviceMethodHandler.getServiceMethodDefinition().getMethodGroup();
				if(!StringUtils.isEmpty(plugin_type))
					cmsService.getCmsContext().setTplByPluginType(plugin_type);
			} else {
				return;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			CommonTools.addError(new ZteError(ApiConsts.ERROR_FAIL, "==>调用插件失败"+ methodName + "失败," + e.getMessage()));
		} finally {
		}
	}
	
	/**
	 * 模块添加路径改造
	 * @param urlPath
	 * @return
	 */
	public String warpperUrl(String urlPath){
//		ICmsBaseManager cmsBaseManager = SpringContextHolder.getBean("cmsBaseManager");
//		if(urlPath.equals("/Store/AddStoreLayout")){
//			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
//			AddStoreLayOutReq layOutReq = BeanUtils.jsonToBean(jsonReq, AddStoreLayOutReq.class);
//			String plugin_type = layOutReq.getStore_layout().getPluginType();
//			if(!StringUtils.isEmpty(plugin_type))
//				urlPath=urlPath+plugin_type;
//			return urlPath;
//		}
//		if(urlPath.equals("/Store/DeleteStoreLayout")){
//			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
//			DeleteStoreLayOutReq layOutReq = BeanUtils.jsonToBean(jsonReq, DeleteStoreLayOutReq.class);
//			String plugin_type = cmsBaseManager.getPluginTypeByStoreLayoutId(layOutReq.getStore_layout_id());
//			if(!StringUtils.isEmpty(plugin_type))
//				urlPath=urlPath+plugin_type;
//			return urlPath;
//		}
		return urlPath;
	}
}
