package com.mage.platform.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILocalInterceptor {
	/**
	 * 前置处理器
	 * @author pzh
	 * @date 2017年1月18日 上午9:38:16
	 * @param request
	 * @param response
	 * @param handler
	 * @throws Exception
	 */
	public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception;
}
