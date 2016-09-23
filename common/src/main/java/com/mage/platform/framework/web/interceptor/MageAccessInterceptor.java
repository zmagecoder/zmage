package com.mage.platform.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mage.platform.framework.context.ThreadContextHolder;

public class MageAccessInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 * 拦截 spring mvc的所有请求
		 * 并设置request、response到线程变量中
		 * 实现request any where
		 */
		ThreadContextHolder.setHttpRequest(request);
		ThreadContextHolder.setHttpResponse(response);
		//TODO 目前只处理了请求对象
		return super.preHandle(request, response, handler);
	}
}
