package com.mage.platform.framework.context;

import javax.servlet.http.HttpServletRequest;

import com.mage.platform.framework.config.EopSetting;

public class EopContext {
	private static ThreadLocal<HttpServletRequest> HttpRequestHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<EopContext> EopContextHolder = new ThreadLocal<EopContext>();
	
	public static void setContext(EopContext context){
		EopContextHolder.set(context);
	}
	
	public static EopContext getContext(){
		
		EopContext context =  EopContextHolder.get();
		return context;
	}
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestHolder.set(request);
	}
	
	
	public static HttpServletRequest getHttpRequest(){
		return HttpRequestHolder.get();
	}
	
	//得到当前站点上下文
	public String getContextPath(){
		if("2".equals(EopSetting.RUNMODE) ){
			StringBuffer context = new StringBuffer("/user");
			context.append("/");
			return context.toString();
		}else{
			return "";
		}
	}
}
