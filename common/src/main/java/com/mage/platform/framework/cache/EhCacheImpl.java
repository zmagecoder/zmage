package com.mage.platform.framework.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * EhCache 缓存实现类
 * @author pzh
 * @date 2016年6月13日 下午4:11:03
 */
public class EhCacheImpl implements ICache {
	
	private static Logger logger = Logger.getLogger(EhCacheImpl.class);

	private static final EhCacheImpl inst = new EhCacheImpl();

	public static EhCacheImpl getCache() {
		return inst;
	}

	private Cache getCacheByNameSpace(String nameSpace) {
		Cache cache = null;
		try {
			CacheManager manager = CacheManager.getInstance();
			cache = manager.getCache(nameSpace);
			if (cache == null) {
				manager.addCache(nameSpace);
				cache = manager.getCache(nameSpace);
			}
		} catch (Exception e) {
			throw new CacheException(e);
		}
		return cache;
	}

	@Override
	public void clear() {
		clear(defaltNameSpace);
	}

	@Override
	public void clear(int nameSpace) {
		Cache cache = getCacheByNameSpace(nameSpace + "");
		try {
			cache.removeAll();
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void close() {
		throw new CacheException("method not implemented!");
	}

	@Override
	public void delete(String key) {
		delete(defaltNameSpace, key);
	}

	@Override
	public void delete(int nameSpace, String key) {
		Cache cache = getCacheByNameSpace(nameSpace + "");
		try {
			cache.remove((Serializable) key);
		} catch (CacheException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Serializable get(String key) {
		return get(defaltNameSpace, key);
	}

	@Override
	public Serializable get(int nameSpace, String key) {
		Cache cache = getCacheByNameSpace(nameSpace + "");
		Object obj = null;
		try {
			if (key != null) {
				Element element = cache.get((Serializable) key);
				if (element != null) {
					obj = element.getValue();
				}
			}
		} catch (CacheException e) {
			logger.error(e.getMessage());
		}
		return (Serializable) obj;
	}

	@Override
	public void set(String key, Serializable value) {
		set(defaltNameSpace, key, value);
	}

	@Override
	public void set(int nameSpace, String key, Serializable value) {
		Cache cache = getCacheByNameSpace(nameSpace + "");
		try {
			Element element = new Element((Serializable) key,
					(Serializable) value);
			cache.put(element);
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void set(int nameSpace, String key, Serializable value,
			int expireTime) {
		set(nameSpace, key, value);
	}
}
