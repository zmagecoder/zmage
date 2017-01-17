package com.mage.platform.framework.widget.processor.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;

import com.mage.consts.CoreConsts;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.database.BaseSupport;
import com.mage.platform.framework.freemarker.FreeMarkerPaser;
import com.mage.platform.framework.widget.processor.IWidget;

/**
 * 基于freemarker的挂件基类
 * @author pzh
 * @date 2016年6月13日 下午2:51:06
 */
@SuppressWarnings("rawtypes")
abstract public class AbstractWidget extends BaseSupport implements IWidget {

	protected FreeMarkerPaser freeMarkerPaser;
	
	protected String customPage; 	//自定义挂件页面,以当前模板为上下文
	
	protected String folder; 		//自定义挂件页面所在文件夹，不指定为当前模板
	
	protected String action;		//插件动作
	
	protected String pageExt;		//文件后缀
	
	protected String freemarker;	//是否freemarker解析
	
	public  String process(Map<String, String> params) {
		FreeMarkerPaser.set(new FreeMarkerPaser());
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		action = request.getParameter("action");
		String html = show(params);					//解析挂件html
		FreeMarkerPaser.remove();
		return html;
	}

	public boolean cacheAble(){
		return false;
	}

	/**
	 * 根据参数字串压入request的参数
	 * @author pzh
	 * @date 2016年6月14日 上午11:31:29
	 * @param reqparams
	 * @param params 要获取reqeust中参数的参数名字，以,号隔开，如：name1,name2
	 */
	private void putRequestParam(String reqparams,Map<String, String> params ){
		if(!StringUtils.isEmpty(reqparams)){
			HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
			String[] reqparamArray = reqparams.split(",");
			for(String paramname :reqparamArray){
				String value = httpRequest.getParameter(paramname);
				  params.put(paramname, value);
			}
		}
	}
	
	/**
	 * 解析html界面
	 * @author pzh
	 * @date 2016年6月14日 上午11:32:07
	 * @param params
	 * @return
	 */
	private String show(Map<String, String> params){
		//初始化freemark对象
		this.initFreemarkConfig();	
		//界面的基本信息
		this.initPageConfig(params);
		//压入request中的参数值
		String reqparams =  params.get("reqparams");
		putRequestParam(reqparams, params);
		//由业务处理类实现
		display(params);
		//将业务参数写入freemark
		freeMarkerPaser.putData(params);
		if(!StringUtils.isEmpty(customPage)){
			String contextPath  = ThreadContextHolder.getHttpRequest().getContextPath();
			if(StringUtils.isEmpty(this.folder)){
				this.freeMarkerPaser.setPageFolder(contextPath+"/");
			}else{
				this.freeMarkerPaser.setPageFolder(contextPath+"/" + folder);
			}
		}
		//解析页面内容
		String html = "";
		if(CoreConsts.YES.equals(freemarker))
			html = freeMarkerPaser.proessPageContent();
		else 
			html = this.getPageContent();
		if("yes".equals(params.get("ischild")) ){
			this.putData("widget_" + params.get("widgetid"), html);
		}
		return html;
	}
	
	/**
	 * 子类需要实现在挂件处理方法<br/>
	 * 一般子类在此方法中处理挂件的业务逻辑，设置页面变量。
	 * 
	 * @param params
	 *            挂件参数
	 * @return
	 */
	abstract protected void display(Map<String, String> params);

	/**
	 * 设置挂件模板的变量
	 * @param key
	 * @param value
	 */
	protected void putData(String key, Object value) {
		this.freeMarkerPaser.putData(key, value);
	}
	
	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key 
	 * @param value
	 */
	protected void putData(Map<String,Object> map) {
		this.freeMarkerPaser.putData(map);
	}

	protected Object getData(String key){
		return this.freeMarkerPaser.getData(key);
	}
	
	/**
	 * 设置模板路径前缀
	 * @param path
	 */
	protected void setPathPrefix(String path) {
		this.freeMarkerPaser.setPathPrefix(path);
	}

	/**
	 * 设置模板文件的名称
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		this.freeMarkerPaser.setPageName(pageName);
	}
	
	/**
	 * 强制设定页面名称
	 * @param pageName
	 */
	public void makeSureSetPageName(String pageName){
		this.freeMarkerPaser.setPageName(pageName);
	}

	/**
	 * 设置模板页面扩展名
	 * 
	 * @param pageExt
	 */
	public void setPageExt(String pageExt) {
		this.pageExt = pageExt;
		this.freeMarkerPaser.setPageExt(pageExt);
	}
	
	/**
	 * 设置模板页面路径
	 * @author pzh
	 * @date 2016年6月14日 下午1:03:27
	 * @param pageFolder
	 */
	public void setPageFolder(String pageFolder){
		this.freeMarkerPaser.setPageFolder(pageFolder);
	}
	
	/**
	 * 清除freemark 配置
	 * @author pzh
	 * @date 2016年6月20日 下午4:37:14
	 */
	private void initFreemarkConfig(){
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		freeMarkerPaser.setPageFolder(null);
		freeMarkerPaser.setPageName(null);
	}
	
	/**
	 * 初始化页面配置
	 * @author pzh
	 * @date 2016年6月20日 下午4:42:00
	 */
	private void initPageConfig(Map<String, String> params){
		this.customPage = params.get("custom_page");				//页面名称
		this.folder = params.get("folder");							//页面路径
		this.freeMarkerPaser.setPageName(customPage);
		
		//界面后缀
		pageExt = params.get("page_ext");
		this.setPageExt(pageExt);
		
		//是否使用freemarker解析
		freemarker = MapUtils.getString(params, "freemarker", CoreConsts.YES);
		this.freeMarkerPaser.setFreemarker(freemarker);
	}
	
	/**
	 * 不需要freemark 解析的界面
	 * 直接获取页面内容
	 * @return
	 */
	private String getPageContent(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		try{
			String pagePath = (pageExt == null ? "html" : pageExt);
			if(StringUtils.isEmpty(customPage))
				pagePath = this.getClass().getSimpleName() + "." + pagePath;
			else 
				pagePath = (folder == null ? "" : folder)  + customPage + "." + pagePath;
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(pagePath);
	        int i = -1; 
	        while((i = is.read()) != -1){ 
	        	baos.write(i); 
	        }
		}catch(Exception e){}
		return baos.toString();
	}
	
	/**
	 * freemark 默认配置
	 * @author pzh
	 * @date 2016年6月20日 下午4:46:37
	 */
	protected void setDefaultValue(){
        HttpServletRequest request=ThreadContextHolder.getHttpRequest();
        if(null!=request){
            this.putData("ctx",request.getContextPath());
        }
        this.freeMarkerPaser.setPathPrefix("");
        this.freeMarkerPaser.setPageFolder(this.folder);
        this.freeMarkerPaser.setPageExt("html");
    }
    
    public boolean isStaticMode(){
		return true;
	}
    
    public void initParam(){}
}
