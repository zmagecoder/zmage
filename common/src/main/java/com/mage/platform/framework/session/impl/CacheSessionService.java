package com.mage.platform.framework.session.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mage.platform.framework.cache.CacheFactory;
import com.mage.platform.framework.cache.ICache;
import com.mage.platform.framework.session.ISessionService;
import com.mage.platform.framework.session.vo.CacheSession;

/**
 * 缓存
 * @author pzh
 * @date 2016年6月15日 下午4:18:49
 */
public class CacheSessionService implements ISessionService{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	private static ICache cache  = CacheFactory.getCache();
	
	private String sessionId;						//sessionId 作为缓存的键值
	
	/**
	 * 每次动态创建缓存session类
	 * @param sessionId
	 */
	public CacheSessionService(String sessionId){
		this.sessionId = sessionId;
	}

	@Override
	public Object getAttribute(String arg0) {
		CacheSession cacheSession = this.getSession(this.sessionId);
		return cacheSession == null ? null : cacheSession.getAttribute(arg0);
	}

	@Override
	public String getId() {
		
		return this.sessionId;
	}

	@Override
	public void invalidate() {
		cache.set(sessionId, null);
	}

	@Override
	public void removeAttribute(String arg0) {
		CacheSession cacheSession = this.getSession(this.sessionId);
		if(null != cacheSession)
			cacheSession.removeAttribute(arg0);
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		addSession(arg0, arg1);
	}
	
	/**
	 * 获取session对象
	 * @author pzh
	 * @date 2016年6月17日 下午3:54:46
	 * @param sessionId
	 * @return
	 */
	private CacheSession getSession(String sessionId){
		CacheSession cacheSession = (CacheSession)cache.get(sessionId);
		if(cacheSession == null)
			logger.info("============================>>>sessoin 失效");
		return cacheSession;
	}
	
	/**
	 * 添加session
	 * @param sessionId
	 * @param sessionVal
	 */
	private void addSession(String key, Object sessionVal){
		CacheSession cacheSession = (CacheSession)cache.get(sessionId);
		if(null == cacheSession)
			cacheSession = new CacheSession(sessionId); 
		cacheSession.setAttribute(key, sessionVal);
		cache.set(sessionId, cacheSession);
	}
}
