package com.mage.platform.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.context.ThreadContextHolder;

public class MageAccessInterceptor extends HandlerInterceptorAdapter{
	
	private ILocalInterceptor localInterceptor;			//本地自定义拦截器
	
	/**
	 * 拦截 spring mvc的所有请求
	 * 并设置request、response到线程变量中
     * 实现request any where
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ThreadContextHolder.setHttpRequest(request);
		ThreadContextHolder.setHttpResponse(response);
		
		//父类只处理通用部分，本地化业务由具体业务实现
		localInterceptor = SpringContextHolder.getBean("localInterceptor");
		if(null != localInterceptor)
			localInterceptor.preHandle(request, response, handler);
		
		return super.preHandle(request, response, handler);
	}
}
