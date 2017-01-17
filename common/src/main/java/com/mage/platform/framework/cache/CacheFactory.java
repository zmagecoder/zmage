package com.mage.platform.framework.cache;

import com.mage.platform.framework.cache.config.CacheValues;
import com.mage.platform.framework.cache.impl.EhCacheImpl;

/**
 * 缓存工厂类
 * @author pzh
 * @date 2016年6月13日 下午3:57:57
 */
public final class CacheFactory {
	
	public static ICache getCache(){
		return getCacheByType(CacheValues.CORE_CACHE_TYPE);
	}
	
	public static ICache getCacheByType(String cacheType){
		//未配置cache类型，则默认使用配置级别
		if(cacheType==null || cacheType.equals("")){
			cacheType = CacheValues.CORE_CACHE_TYPE;
		}
		
		ICache cache = null;
		if(cacheType.equals(CacheValues.CACHE_TYPE_EHCACHE)){//ehcache
			cache = EhCacheImpl.getCache();
		}
		return cache;
	}
}
