package com.mage.platform.sdk.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mage.consts.CoreConsts;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.EopUtil;
import com.mage.platform.sdk.utils.FreeMarkerUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 界面解析类
 * @author pzh
 * @date 2016年6月13日 下午2:53:52
 */
public final class FreeMarkerPaser implements Cloneable{

	private static final Log log = LogFactory.getLog(FreeMarkerPaser.class);
	
	private static ThreadLocal<FreeMarkerPaser> managerLocal = new ThreadLocal<FreeMarkerPaser>();			//线程变量
	
	private static Configuration  cfg;
	
	/*
	 * freemarker data model 通过putData方法设置模板中的值
	 */
	private Map<String, Object> data;

	/*
	 * 模板路径前缀 默认为"" 可以通过 {@link #setPathPrefix(String)} 设置
	 */
	private String pathPrefix;

	/*
	 * 模板文件的名字，默认为与插件类同名
	 */
	private String pageName;

	/*
	 * 模板页面的扩展名，默认为.html
	 */
	private String pageExt;
	
	/**
	 * 是否通过freemarker解析
	 */
	private String freemarker;
 
	/*
	 * 页面所在文件夹
	 * 默认为插件类所在文件夹
	 */
	private String pageFolder; 
	
	public FreeMarkerPaser(){ 
		data = new HashMap<String, Object>();
		this.clazz =null;
		this.pageFolder =null;
	}
	
	/**
	 * 获取当前线程的 fremarkManager
	 * @return
	 */
	public final static FreeMarkerPaser getInstance(){
		if(managerLocal.get() == null ){
			throw new RuntimeException("freemarker paser is null");
		}
		FreeMarkerPaser fmp = managerLocal.get();
		fmp.setPageFolder(null);
		fmp.setWrapPath(false);
		return fmp;
	}
	
	public final static FreeMarkerPaser getCurrInstance(){
		if( managerLocal.get()==null ){
			throw new RuntimeException("freemarker paser is null");
		}
		FreeMarkerPaser fmp = managerLocal.get();
		return fmp;
	}
	
	/**
	 * 设置线程变量
	 * @author pzh
	 * @date 2016年6月13日 下午3:51:26
	 * @param fp
	 */
	public final static void set(FreeMarkerPaser fp){
		 managerLocal.set(fp);
	}
	
	/**
	 * 清空线程变量
	 * @author pzh
	 * @date 2016年6月13日 下午3:51:38
	 */
	public final static void remove(){
		 managerLocal.remove();
	} 
	
	/**
	 * 挂件类名，在没有指定页面名称的情况下
	 * 取和挂件类相同名称的界面
	 */
	@SuppressWarnings("rawtypes")
	private Class clazz;
	
	@SuppressWarnings("rawtypes")
	public void setClz(Class clz){
		this.clazz = clz;
	}
	
	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key
	 * @param value
	 */
	public void putData(String key, Object value) {
		if (key != null && value != null)
			data.put(key, value);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void putData(Map  map){
		if(map!=null)
			data.putAll(map);
	}
	
	public Object getData(String key){
		if(key==null) 
			return null;
		return data.get(key);
	}
	
	/**
	 * 资源文件路径包装
	 * 可以扩展不同的主题风格
	 */
	private boolean wrapPath = false;
	
	public void setWrapPath(boolean wp){
		wrapPath = wp;
	}
	
	/**
	 * 解析界面内容
	 * @author pzh
	 * @date 2016年6月14日 下午12:36:13
	 * @return
	 */
	public String proessPageContent(){
		try {
			String name = this.clazz.getSimpleName();						//默认文件名为挂件类名称
			pageExt = pageExt == null ? "html" : pageExt;		
			name = pageName == null ? name : pageName;
		 
			cfg = this.getCfg();
			cfg.setNumberFormat("0.##");
			Template temp = cfg.getTemplate(name + "." + pageExt);
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			if(CoreConsts.YES.equals(freemarker))
				temp.process(data, out);
			out.flush();
			String content = stream.toString();								//文件流
			//重新设置引入的资源文件路径
			if(wrapPath){
				content = EopUtil.wrapjavascript(content, this.getResPath());
				content =  EopUtil.wrapcss(content, getResPath());
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "widget  processor error";
	}
	
	private Configuration getCfg(){
		if(cfg == null){
			 cfg = FreeMarkerUtil.getCfg();		
		}
		pathPrefix = pathPrefix == null ? "" : pathPrefix;
		if(pageFolder == null) {//默认使用挂件所在文件夹
			log.info(" folder null use "+ this.clazz.getName() );
			cfg.setClassForTemplateLoading(this.clazz, pathPrefix);
		} else{
		  cfg.setServletContextForTemplateLoading(ThreadContextHolder.getHttpRequest().getSession().getServletContext(), pageFolder);
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());			
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(java.util.Locale.CHINA);
		cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		return cfg;
	}

	/**
	 * 设置模板路径前缀
	 * 
	 * @param path
	 */
	public void setPathPrefix(String path) {
		this.pathPrefix = path;
	}
	
	/**
	 * 设置模板文件的名称
	 * 
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * 设置模板页面扩展名
	 * 
	 * @param pageExt
	 */
	public void setPageExt(String pageExt) {
		this.pageExt = pageExt;
	}

	public void setPageFolder(String pageFolder){
		this.pageFolder = pageFolder;
	}
	
	public void setFreemarker(String freemarker) {
		this.freemarker = freemarker;
	}

	/**
	 * 获取资源根路径
	 * @return
	 */
	private String getResPath(){
		String ctx = EopSetting.CONTEXT_PATH;
		ctx = ctx.equals("/") ? "" : ctx;
		if(this.pageFolder == null ){
			return ctx + "/appres/"+ this.clazz.getPackage().getName().replaceAll("\\.", "/")+"/";
		}else{
			return ctx + pageFolder+"/";
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object clone(){
		FreeMarkerPaser obj = null;
		try{
			obj = (FreeMarkerPaser)super.clone();
			obj.data = (Map<String, Object>)((HashMap<String, Object>) data).clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return obj;
	}
	
}
