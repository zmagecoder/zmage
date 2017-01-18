package com.mage.platform.framework.cache.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mage.platform.framework.exception.CacheException;

/**
 * 加载缓存配置类
 * @author pzh
 * @date 2016年6月13日 下午4:25:55
 */
public class CacheConfig {
	private static Logger logger = Logger.getLogger(CacheConfig.class);
	private static final String config_properties="cacheConfig.properties" ;
	private static Properties properties ;
	
	static{
		properties = getConfigFile()  ;
	}
	
	public static String get(String key){
		return properties.getProperty(key) ;
	}

	private CacheConfig(){
		
	}
	
	public  static Properties getConfigFileProperties(String fileName) {
		InputStream is;
		Properties configFile  = new Properties() ;
		try {
			String config = System.getProperty("CONFIG");
			is = getOutFileInputStream(config+fileName);
			if(is == null){
				is = getFileInputStream(config+fileName);
			}
			configFile.load(is) ;
			is.close() ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CacheException(e);
		}
		return configFile ;
	}
	
	/**
	 * 工程下配置文件
	 */
	private static InputStream getFileInputStream(String fileName) throws Exception {
		return CacheConfig.class.getClassLoader().getResourceAsStream(fileName) ;
	}
	
	/**
	 * 独立配置的文件
	 */
	private static InputStream getOutFileInputStream(String fileName) throws Exception {
		File file = new File(fileName);
		return new FileInputStream(file);
	}
	
	public  static Properties getConfigFile()  {
		return getConfigFileProperties(config_properties) ;
	}
	
	public static void writeBackWhileAPPExit() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					FileWriter fw = new FileWriter(CacheValues.CORE_CACHE_DEFAULT_WRITELOG);
					String info = "the application ended! " + (new Date()).toString();
					logger.info(info);
					fw.write(info);
					fw.close();
				} catch (IOException ex) {
					throw new CacheException(ex);
				}
			}
		});
	}
}
