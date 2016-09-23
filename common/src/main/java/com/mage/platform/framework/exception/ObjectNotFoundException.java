package com.mage.platform.framework.exception;

/**
 * 对像未找到异常<br>
 * 多用于根据某id查询一条记录，但此记录不存在
 * @author pzh
 * @date 2016年6月13日 下午4:17:12
 */
public class ObjectNotFoundException extends DBRuntimeException {
	
	private static final long serialVersionUID = -3403302876974180460L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
		
	public ObjectNotFoundException(Exception e, String sql) {
		super(e, sql);
	}
}
