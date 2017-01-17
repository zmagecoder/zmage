package com.mage.platform.framework.context;

import com.mage.platform.framework.session.ISessionService;

/**
 * 实现Session any where
 * @author pzh
 * @date 2016年6月15日 下午5:00:55
 */
public class SessionContextHolder  {
	
	private static ISessionService sessionService;
	
	/**
	 * 根据配置文件获取session的存储方式
	 * @return
	 */
	public static ISessionService getInstance(){
		//TODO 获取配置信息，默认取http方式
		sessionService = SpringContextHolder.getBean("httpSessionService");
		return sessionService;
	}
	
}
