package com.mage.common;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Map
 * @作者 pzh
 * @创建日期 2016-05-27
 * @版本 V 1.0
 */
public class MageMap<K,V> extends HashMap<K, V> implements Serializable{

	private static final long serialVersionUID = 4723675013862692528L;
	
	/**
	 * 获取String类型数据
	 * @param key
	 * @return
	 */
	public String getStr(String key){
		String val = "";
		try{
			Object valObj = this.get(key);
			if(null != valObj)
				val = valObj.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}
}
