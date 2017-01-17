package com.mage.platform.utils;

import java.io.File;
import java.io.IOException;

import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.freemarker.DateFormateDirectiveModel;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * freemarker工具
 * @author pzh
 * @date 2016年6月14日 下午2:12:50
 */
public class FreeMarkerUtil {
	
	private static Configuration cfg;									//freemark配置对象
	/**
	 * 获取servlet上下文件的Configuration
	 * @param pageFolder
	 * @return
	 */
	public static Configuration getServletCfg(String pageFolder) {
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(ThreadContextHolder.getHttpRequest().getSession().getServletContext(), pageFolder);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		return cfg;
	}
	
	/**
	 * 初始化freeMark的基本配置
	 * @author pzh
	 * @date 2016年6月14日 下午12:44:01
	 * @return
	 */
	public static Configuration getCfg(){
		if (cfg == null) {
			cfg = new Configuration();
			cfg.setTemplateUpdateDelay(6000);
			cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));

			DateFormateDirectiveModel df = new DateFormateDirectiveModel();  
			cfg.setSharedVariable("dateFormat", df);
			 
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		}
		return cfg;
	}
	
	public static Configuration getFolderCfg(String pageFolder)
			throws IOException {
		cfg = getCfg();
		cfg.setDirectoryForTemplateLoading(new File(pageFolder));
		return cfg;
	}
}
