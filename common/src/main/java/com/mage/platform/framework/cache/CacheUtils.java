package com.mage.platform.framework.cache;

import java.io.Serializable;

import com.mage.consts.CacheConsts;

public class CacheUtils {
	
	/**
	 * 添加缓存
	 * @param key
	 * @param serial
	 */
	public static void addCache(String key, Serializable serial){
		ICache cache = CacheFactory.getCacheByType("");
		cache.set(CacheConsts.CACHE_SPACE, key, serial);
	}
	
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T get(String key){
		ICache cache = CacheFactory.getCacheByType("");
		return (T)cache.get(key);
	}

}
