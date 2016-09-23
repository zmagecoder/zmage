package com.mage.platform.framework.exception;

/**
 * 缓存异常类
 * @author pzh
 * @date 2016年6月13日 下午4:01:12
 */
public class CacheException extends RuntimeException {
	
	private static final long serialVersionUID = -3381974266250940022L;

	public CacheException() {
		super();
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}
}
