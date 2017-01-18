package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.DefaultCmsBeanDefine;
import com.mage.cms.common.params.base.req.AddStoreLayOutReq;
import com.mage.cms.common.params.base.req.DeleteStoreLayOutReq;
import com.mage.cms.common.params.base.resp.CmsLineResp;
import com.mage.cms.common.params.news.req.EditNewsGroupReq;
import com.mage.cms.common.params.news.req.GetNewsGroupReq;
import com.mage.cms.common.service.ICmsBaseManager;
import com.mage.cms.utils.CmsUtil;
import com.mage.common.ServiceMethodHandler;
import com.mage.common.ZteError;
import com.mage.consts.ApiConsts;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.utils.CommonTools;

public class CmsTplUrlPraser implements UrlPraser{
	
	public String perform(HttpServletRequest req,HttpServletResponse resp,String urlPath) {
		DefaultCmsBeanDefine context = DefaultCmsBeanDefine.getInstance();
		String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
		String methodName = urlPath,version="1.0",jsonStrResp="";
		try {
			// 检测方法是否存在
			urlPath=warpperUrl(urlPath);
			if (context.isValidMethodVersion(urlPath, version)) {
				ServiceMethodHandler serviceMethodHandler = context.getServiceMethodHandler(urlPath, version);
				CmsLineResp cmsResp =  (CmsLineResp) serviceMethodHandler.getHandlerMethod().invoke(serviceMethodHandler.getHandler(),jsonReq);
				String re_json = BeanUtils.beanToJsonNCls(cmsResp);
				if(urlPath.equals("/AdminOperation/CorpPhoto")){
					
				}else if(urlPath.equals("/Store/GetPluginStoreMessage")){
					re_json = CmsUtil.firstToUpperCase(re_json);
					re_json =re_json.replace("\"Message", "\"message");
					re_json =re_json.replaceAll("\"Success", "\"success");
				}else if(urlPath.equals("/AdminOperation/GetMobilePreviewUrl")){
					re_json = CmsUtil.firstToUpperCase(re_json);
					re_json =re_json.replaceAll("\"Success", "\"success");
					re_json =re_json.replaceAll("\"Url", "\"url");
				}else {
					re_json = CmsUtil.firstToUpperCase(re_json);
					re_json =re_json.replaceAll("\"Success", "\"success");
					re_json =re_json.replace("\"Store_layout", "\"store_layout");
					re_json = re_json.replace("\"Showcase\"", "\"showcase\"");
					
					re_json = re_json.replace("\"Simple_goods\"", "\"simple_goods\"");
					re_json = re_json.replace("\"Goods_tag\"", "\"goods_tag\"");
					re_json = re_json.replace("\"Goods_class\"", "\"goods_classs\"");
					re_json = re_json.replace("\"Total\"", "\"total\"");
					
					re_json =re_json.replace("\"Free", "\"free");
					re_json =re_json.replace("\"freeId", "\"FreeId");
					re_json =re_json.replace("\"Data", "\"data");
					re_json =re_json.replace("\"Store_template", "\"store_template");
				}

				
				return re_json;
			} else {
				CommonTools.addError("-1", urlPath+"==>找不到服务");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			CommonTools.addError(new ZteError(ApiConsts.ERROR_FAIL, "==>调用"+ methodName + "失败," + e.getMessage()));
		} finally {
		}
		return "";
	}
	
	/**
	 * add by wui模块添加路径改造
	 * @param urlPath
	 * @return
	 */
	public String warpperUrl(String urlPath){
		ICmsBaseManager cmsBaseManager = SpringContextHolder.getBean("cmsBaseManager");
		if(urlPath.equals("/Store/AddStoreLayout")){
			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
			AddStoreLayOutReq layOutReq = BeanUtils.jsonToBean(jsonReq, AddStoreLayOutReq.class);
			String plugin_type = layOutReq.getStore_layout().getPluginType();
			if(!StringUtils.isEmpty(plugin_type))
				urlPath=urlPath+plugin_type;
			return urlPath;
		}
		if(urlPath.equals("/Store/DeleteStoreLayout")){
			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
			DeleteStoreLayOutReq layOutReq = BeanUtils.jsonToBean(jsonReq, DeleteStoreLayOutReq.class);
			String plugin_type = cmsBaseManager.getPluginTypeByStoreLayoutId(layOutReq.getStore_layout_id());
			if(!StringUtils.isEmpty(plugin_type))
				urlPath=urlPath+plugin_type;
			return urlPath;
		}
		
		//新闻图片插件
		if(urlPath.equals("/Store/GetNewsGroup")){
			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
			GetNewsGroupReq layOutReq = BeanUtils.jsonToBean(jsonReq, GetNewsGroupReq.class);
			String plugin_type = cmsBaseManager.getPluginTypeByPluginId(layOutReq.getNews_group_id());
			if(!StringUtils.isEmpty(plugin_type))
				urlPath=urlPath+plugin_type;
			return urlPath;
		}
		
		if(urlPath.equals("/Store/EditNewsGroup")){
			String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();
			EditNewsGroupReq layOutReq = BeanUtils.jsonToBean(jsonReq, EditNewsGroupReq.class);
			String plugin_type = cmsBaseManager.getPluginTypeByPluginId(layOutReq.getNews_group().getId());
			if(!StringUtils.isEmpty(plugin_type))
				urlPath=urlPath+plugin_type;
			return urlPath;
		}
		
		return urlPath;
	}
}
