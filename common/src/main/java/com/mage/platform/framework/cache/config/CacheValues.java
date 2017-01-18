package com.mage.platform.framework.cache.config;

import org.springframework.util.StringUtils;

/**
 * 缓存配置变量值
 * @author pzh
 * @date 2016年6月13日 下午4:25:32
 */
public class CacheValues {
	
	public static final String CORE_CACHE_TYPE = CacheConfig.get("core.cache.type");
	public static final int CORE_CACHE_DEFAULT_NAMESPACE = Integer.parseInt(CacheConfig.get("core.cache.default.namespace"));
	public static final String CORE_CACHE_DEFAULT_WRITELOG = CacheConfig.get("core.cache.default.writelog");
	public static final int CORE_CACHE_DEFAULT_SESSION_TIMEOUT = Integer.parseInt(CacheConfig.get("core.cache.default.session.timeout"));
	public static final int CORE_CACHE_DEFAULT_SESSION_NAMESPACE = Integer.parseInt(CacheConfig.get("core.cache.default.session.namespace"));
	
	public static final String CACHE_TYPE_MEMCACHED="memcached";
	public static final String CACHE_TYPE_DY_MEMCACHED="dymemcached";
	public static final String CACHE_TYPE_EHCACHE="ehcache";
	public static final String CACHE_TYPE_TAIR="tair";
	public static final String CACHE_TYPE_REDIS="redis";
	public static final String CACHE_TYPE_COHERENCE ="coherence";
	
	public static final String DY_MEMCACHED_SERVERS = StringUtils.isEmpty(CacheConfig.get("dymemcached.servers"))?CacheConfig.get("memcached.servers"):CacheConfig.get("dymemcached.servers"); //
	public static final String MEMCACHED_SERVERS = CacheConfig.get("memcached.servers") ;
	public static final String MEMCACHED_CONTEXT = CacheConfig.get("memcached.context") ;
	
	public static final int MEMCACHED_POOLSIZE = Integer.parseInt(CacheConfig.get("memcached.poolsize")) ;
	public static final long MEMCACHED_TIMEOUT = Long.parseLong(CacheConfig.get("memcached.timeout")) ;
	public static final long MEMCACHED_MULTIGETS_TIMEOUT = Long.parseLong(CacheConfig.get("memcached.multiGetTimeout")) ;
	public static final String MEMCACHED_XM = "xm";
	public static final String MEMCACHED_SPY = "spy";
	
	public static final String TAIR_MANAGE_SERVERS = CacheConfig.get("tair.manage.servers") ;
	public static final String TAIR_GROUPNAME = CacheConfig.get("tair.groupname") ;
	public static final String TAIR_CHARSET = CacheConfig.get("tair.charset") ;
	public static final int TAIR_MAXWAITTHREAD = Integer.parseInt(CacheConfig.get("tair.MaxWaitThread")) ;

	public static final String REDIS_SERVER_LIST = CacheConfig.get("redis.server.list") ;
	public static final int REDIS_MAX_TOTAL = Integer.parseInt(CacheConfig.get("redis.maxTotal")) ;
	public static final int REDIS_MAX_IDEL = Integer.parseInt(CacheConfig.get("redis.maxIdel")) ;
	public static final long REDIS_MAX_WAIT_MILLIS = Long.parseLong(CacheConfig.get("redis.maxWaitMillis"));
	public static final boolean REDIS_TEST_ON_BORROW = Boolean.parseBoolean(CacheConfig.get("redis.testOnBorrow"));
	public static final boolean REDIS_TEST_ON_RETURN = Boolean.parseBoolean(CacheConfig.get("redis.testOnReturn")) ;
}



