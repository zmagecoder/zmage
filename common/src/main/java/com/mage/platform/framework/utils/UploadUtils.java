package com.mage.platform.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mage.platform.framework.context.EopContext;

public class UploadUtils {
	
	private UploadUtils(){
		
	}
	
	public static InputStream getResourceAsStream(String resource) {
		try {
			File file = new File(System.getProperty("CONFIG")+resource);
			InputStream stream = null;
			if(file.exists()){
				stream  = new FileInputStream(file);
			}
			if(null == stream){
				String stripped = resource.startsWith("/") ? resource.substring(1)
						: resource;
				stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(stripped);
			}
			return stream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换图片的名称
	 * @param filePath
	 * @param shortName 
	 * @return
	 */
	public static  String getThumbPath(String filePath, String shortName) {
		String pattern = "(.*)([\\.])(.*)";
		String thumbPath = "$1" + shortName + "$2$3";

		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(filePath);
		if (m.find()) {
			String s = m.replaceAll(thumbPath);

			return s;
		}
		return null;
	}
	
	/**
	 * 根据路径删除文件
	 * @param filePath
	 */
	public static void delete(String filePath) {
		//TODO 根据路径删除文件
		
	}
	
	public static String getEopContextPath(){
		
		String pathStr = EopContext.getContext() != null ? EopContext.getContext().getContextPath() : "/";
		return pathStr;
		
	}
	
}
