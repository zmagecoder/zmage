package com.mage.platform.framework.context.impl.session;

import com.mage.session.Session;

/**
 * session 处理类，分http，cache
 * @author pzh
 * @date 2016年6月16日 上午11:25:10
 */
public interface ISessionService {

	public static String sessionAttributeKey = "cacheSessionKey";

	/**
	 * 获取session值
	 * @author pzh
	 * @date 2016年6月16日 上午11:15:40
	 * @param key  键值
	 * @return
	 */
	public Object getAttribute(String key);

	/**
	 * 获取sessionID
	 * @author pzh
	 * @date 2016年6月16日 上午11:16:14
	 * @return
	 */
	public String getId();

	/**
	 * session失效
	 * @author pzh
	 * @date 2016年6月16日 上午11:16:56
	 */
	public void invalidate();

	/**
	 * 清除session属性
	 * @author pzh
	 * @date 2016年6月16日 上午11:17:38
	 * @param arg0
	 */
	public void removeAttribute(String arg0);

	/**
	 * 设置session的属性值
	 * @author pzh
	 * @date 2016年6月16日 上午11:17:57
	 * @param arg0
	 * @param arg1
	 */
	public void setAttribute(String arg0, Object arg1);

}