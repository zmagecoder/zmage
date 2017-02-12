package com.mage.platform.utils;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.database.IDaoSupport;

/**
 * sql工具类
 * @author pzh
 */
public class SqlUtils{
	
	/**
	 * 获取默认数据源
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static IDaoSupport getExecutor(){
		IDaoSupport daoSupport = SpringContextHolder.getBean("mySqlDBSupport");
		return daoSupport;
	}
	
	/**
	 * 获取指定数据源
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static IDaoSupport getExecutor(String dataSoure) {
		//TODO
	    return null;
	}
	
}
