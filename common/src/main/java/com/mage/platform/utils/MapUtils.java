package com.mage.platform.utils;

import java.util.Map;

/**
 * map 通用操作类
 * @author pzh
 * add 2016-05-27
 */
public class MapUtils extends org.apache.commons.collections.MapUtils{
	
	/**
	 * 删除map中的键值
	 * @param paramMap
	 * @param keys
	 */
	public static  void removeKeys(Map<String, Object> paramMap,String [] keys){
		for (int i = 0; i < keys.length; i++) {
			paramMap.remove(keys[i]);
		}
	}
	
}
