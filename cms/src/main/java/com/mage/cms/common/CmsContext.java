package com.mage.cms.common;

import java.io.Serializable;

import com.mage.cms.common.model.AppPage;
import com.mage.cms.common.model.Application;
import com.mage.cms.common.model.Template;
import com.mage.cms.common.model.Widget;
import com.mage.cms.common.params.pagestore.resp.GetStorePageResp;
import com.mage.cms.common.params.template.vo.StoreTemplate;
import com.mage.cms.common.service.ITemplateManager;
import com.mage.cms.consts.CmsConst;
import com.mage.platform.framework.context.SpringContextHolder;


/**
 * CMS 上下文
 * @author pzh
 * @date 2016年6月3日 下午2:17:01
 */
public class CmsContext implements Serializable{
	
	private static final long serialVersionUID = 752513002L;
	
	private String userid;
	private String bar_type = CmsConst.BAR_TYPE_STATIC; //缺省为静态操作模式
	private boolean is_preview; //判断是够为预览模式
	private String device =CmsConst.DEVICE_0; //设备号
	private String page_id;
	
	private Application app; //当前应用，切换
	private String jsonReq;//json请求入参数
	
	private Template template;  //模板操作时，此对象不为空
	private Widget widget;//模板操作时，此对象不为空
	private boolean is_edit =false; //判断是否允许编辑
	
	private StoreTemplate store_template;
	
	private GetStorePageResp storePage;
	
	private AppPage currPage;
	
	
	private String sessionId;
	
	private static ThreadLocal<StorePage> storeThreadLocalHolder = new ThreadLocal<StorePage>();
	
	public  CmsContext(String sessionId){
		this.sessionId =sessionId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBar_type() {
		return bar_type;
	}

	public void setBar_type(String bar_type) {
		this.bar_type = bar_type;
	}


	public boolean isIs_preview() {
		return is_preview;
	}


	public void setIs_preview(boolean is_preview) {
		this.is_preview = is_preview;
	}


	public Application getApp() {
		return app;
	}

	/**
	 * 门户页面访问使用
	 */
	@SuppressWarnings("rawtypes")
	public void setAppByAppId(String app_rel_id) {
		
	}
	
	public Template getTpl(){
		StorePage storePage = this.getStoreThreadLocalHolder(); 
		return storePage.getTemplate();
	}
	
	public void setTplByPluginType(String plugin_type){
		ITemplateManager templateManager =SpringContextHolder.getBean("templateManager");
		StorePage storePage = this.getStoreThreadLocalHolder();
		template = templateManager.getTplByPluginType(plugin_type);
		storePage.setTemplate(template);
		storePage.setWidget(templateManager.getWidgetByWidgetId(template.getWidget_id()));
	}
	
	/**
	 * 编辑模式切换使用
	 */
	public void resetApp() {
		
	}

	public String getAppDefaultPageUrl() {
		String url = "/sshop.html";
		return url;
	}
	
	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}

	public void setApp(Application app) {
		this.app = app;
	}


	public Template getTemplate() {
		StorePage storePage = this.getStoreThreadLocalHolder(); 
		return storePage.getTemplate();
	}

	public Widget getWidget() {
		StorePage storePage = this.getStoreThreadLocalHolder(); 
		return storePage.getWidget();
	}

	public String getJsonReq() {
		StorePage storePage = this.getStoreThreadLocalHolder();
		return storePage.getJsonReq();
	}


	public void setJsonReq(String jsonReq) {
		StorePage storePage = this.getStoreThreadLocalHolder();
		storePage.setJsonReq(jsonReq);
	}


	public String getSessionId() {
		return sessionId;
	}


	public String getPage_id() {
		StorePage storePage = this.getStoreThreadLocalHolder();
		return storePage.getPage_id();
	}


	public void setPage_id(String page_id) {
		StorePage storePage = this.getStoreThreadLocalHolder();
		if(null == page_id || "".equals(page_id)){
			page_id = this.getApp().getAppPage().getId();
		}
		storePage.setPage_id(page_id);
	}

	//线程变量内部类
	public class StorePage{
		
		private String page_id;			//页面id
		private String jsonReq;			//json请求入参数
		private Template template;  	//模板操作时，此对象不为空
		private Widget widget;			//模板操作时，此对象不为空
				
		public String getPage_id() {
			return page_id;
		}
		public void setPage_id(String page_id) {
			this.page_id = page_id;
		}
		public String getJsonReq() {
			return jsonReq;
		}
		public void setJsonReq(String jsonReq) {
			this.jsonReq = jsonReq;
		}
		public Template getTemplate() {
			return template;
		}
		public void setTemplate(Template template) {
			this.template = template;
		}
		public Widget getWidget() {
			return widget;
		}
		public void setWidget(Widget widget) {
			this.widget = widget;
		}
		
	}

	public StorePage getStoreThreadLocalHolder() {
		if (storeThreadLocalHolder.get() == null) {
			storeThreadLocalHolder.set(new StorePage());
		}
		return storeThreadLocalHolder.get();
	}

	public boolean isIs_edit() {
		return is_edit;
	}

	public void setIs_edit(boolean is_edit) {
		this.is_edit = is_edit;
	}

	public StoreTemplate getStore_template() {
		return store_template;
	}

	public void setStore_template(StoreTemplate store_template) {
		this.store_template = store_template;
	}

	public GetStorePageResp getStorePage() {
		return storePage;
	}

	public void setStorePage(GetStorePageResp storePage) {
		this.storePage = storePage;
	}


	public AppPage getCurrPage() {
		return currPage;
	}

	public void setCurrPage(AppPage currPage) {
		this.currPage = currPage;
	}
}
