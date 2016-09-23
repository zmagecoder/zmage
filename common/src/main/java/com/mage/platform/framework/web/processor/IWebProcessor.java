package com.mage.platform.framework.web.processor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IWebProcessor {
	
	/**
	 *  界面通用处理类
	 * @author pzh
	 * @date 2016年7月13日 下午6:17:29
	 * @param model
	 * @param request
	 */
	public void process(Map<String, Object> model, HttpServletRequest request);
	
}
