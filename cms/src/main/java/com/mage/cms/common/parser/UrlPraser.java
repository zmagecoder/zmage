package com.mage.cms.common.parser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面路径执行器
 * @author wu.i
 *
 */
public interface UrlPraser {
	public String perform(HttpServletRequest req,HttpServletResponse resp,String urlPath) throws Exception;
}
