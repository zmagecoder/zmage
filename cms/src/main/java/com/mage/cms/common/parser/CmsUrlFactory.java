package com.mage.cms.common.parser;

/**
 * 
 * @author wu.i
 * 页面执行器
 *
 */
public class CmsUrlFactory {
	private static final CmsUrlFactory inst;
	static{
		inst = new CmsUrlFactory(); 
	}
	public static CmsUrlFactory getInstance(){
		return inst;
	}
	public UrlPraser getUrlParser(String uri) {

		//插件类型处理器
		CmsTplPluginTypePraser pluginTypePraser = CmsPluginTypeFactory.getInstance().getPluginTypeParser(uri);
		pluginTypePraser.perform(uri);
		
		if(uri.equals("/Admin/Static") || uri.equals("/Admin/Preview") || uri.equals("/Admin/Design") || uri.equals("/Admin/MobileDesign") || uri.equals("/Admin/BasicSet_Start") ||
		   uri.equals("/AdminOperation/GetStoreNewStatus") || uri.equals("/Admin/LogOff") || 
		   uri.equals("/Account/LogOn") || uri.equals("/Account/Register"))
			return new CmsAdminUrlPraser();
		else if(uri.equals("/Store/AddStorePage") || uri.equals("/Store/GetStorePage") || uri.equals("/Store/UpdateStorePage"))
			return new CmsPageUrlPraser();
		else if(uri.equals("/Home/News") || uri.equals("/Home/Index")){
			return new CmsNewsUrlPraser();
		}else if(uri.equals("/Home/Search")){
			return new CmsSearchUrlPraser();
		}else
			return new CmsTplUrlPraser();
		
		//TODO 新增页面执行器也在此操作
	}
}
