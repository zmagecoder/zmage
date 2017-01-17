package com.mage.platform.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html 工具类
 * @author pzh
 * @date 2016年10月8日 下午2:18:37
 */
public class HtmlUtil {
	private HtmlUtil(){}
	
	/**
	 * 包装 html中的css路径<br>
	 * 将所有style link引入加入 指定的前缀,http开头被忽略
	 * @param html 
	 * @param wrapPath 要包装的前缀
	 * @return
	 */
	public  static  String  wrapcss(String html,String wrapPath){
		
		String pattern ="<link([^<|^>]*?)href=\"([^http|/eop|].*?)\"([^<|^>]*?)>";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(html);
		if(m.find()){
			html  =m.replaceAll("<link$1href=\""+wrapPath+"$2\"$3>");
		}
		return html;
	}

 
	/**
	 * 包装 html中的javascript路径<br>
	 * 将所有javascript引入加入 指定的前缀,http开头被忽略
	 * @param html
	 * @param wrapPath
	 * @return
	 */
	public  static  String  wrapjavascript(String html,String wrapPath){
		
		String pattern ="<script([^<|^>]*?)src=\"([^http|/eop].*?)\"([^<|^>]*?)>";
	 
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(html);
		
		if(m.find()){
			html  =m.replaceAll("<script$1src=\""+wrapPath+"$2\"$3>");
		}
		
		return html;
	}
	 
	/**
	 * 包装 html中的图片路径<br>
	 * 将所有图片引入加入 指定的前缀,http开头被忽略
	 * @param content
	 * @param wrapPath
	 * @return
	 */
	public  static  String  wrapimage(String content,String wrapPath){
		
		String pattern ="<img([^<|^>]*?)src=\"([^http|/eop|\\u0024].*?)\"([^<|^>]*?)>";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(content);
		if(m.find()){
			content  =m.replaceAll("<img$1src=\""+wrapPath+"$2\"$3>");
		}
		return content;
	}
	
}
