package com.mage.platform.framework.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.platform.framework.session.ISessionService;

/**
 * 用ThreadLocal来存储Session
 * 实现Request any where 
 * @author pzh
 * @date 2016年6月13日 下午4:44:39
 */
public class ThreadContextHolder  {
	
	/**
	 * session 变量
	 */
	private static ThreadLocal<ISessionService> HttpSessionThreadLocalHolder = new ThreadLocal<ISessionService>();
	
	/**
	 * Request 变量
	 */
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	
	/**
	 * Response 变量
	 */
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();
	
	
	/**
	 * 通用线程变量
	 */
	private static ThreadLocal<Map<String,Object>> threadVar = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * 设置线程变量
	 * @author pzh
	 * @date 2016年6月15日 下午4:25:57
	 * @param key
	 * @param value
	 */
	public static void setThreadValue(String key, Object value){
		Map<String,Object> map = threadVar.get();
		if(map==null){
			map = new HashMap<String,Object>();
			map.put(key, value);
			threadVar.set(map);
		}else{
			map.put(key, value);
		}
	}

	/**
	 * 获取线程变量
	 * @author pzh
	 * @date 2016年6月15日 下午4:26:10
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getThreadValue(String key){
		Map<String, Object> map = threadVar.get();
		if(map!=null)
			return (T)map.get(key);
		return null;
	}
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestThreadLocalHolder.set(request);
	}
	
	public static HttpServletRequest getHttpRequest(){
		return  HttpRequestThreadLocalHolder.get();
	}
	
	
	public static void setHttpResponse(HttpServletResponse response){
		HttpResponseThreadLocalHolder.set(response);	
	}
	
	public static HttpServletResponse getHttpResponse(){
		
		return HttpResponseThreadLocalHolder.get();
	}

	/**
	 * 缓存的Session，用于存储用户状态
	 * @author pzh
	 * @date 2016年6月15日 下午4:27:10
	 * @return
	 */
	public static ISessionService  getSessionContext() {
		
		return HttpSessionThreadLocalHolder.get();
	}
	
	public static String getSessionId(){
		HttpServletRequest request = HttpRequestThreadLocalHolder.get();
		if(null != request)
			return request.getSession().getId();
		return null;
	}
}
