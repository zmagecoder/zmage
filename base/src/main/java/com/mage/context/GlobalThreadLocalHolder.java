package com.mage.context;

import java.util.UUID;

/**
 * 线程变量接口
* @作者 MoChunrun 
* @创建日期 2013-10-12 
* @版本 V 1.0
 */
public class GlobalThreadLocalHolder {

	private static final GlobalThreadLocalHolder local = new GlobalThreadLocalHolder();

	
	private ThreadLocal<String> userSessionIdLocal = new ThreadLocal<String>();
	
	private GlobalThreadLocalHolder(){}
	
	public static GlobalThreadLocalHolder getInstance(){
		return local;
	}

	public String getUUID(){
		String uuid = userSessionIdLocal.get();
		if("".equals(uuid) || null ==uuid || "null".equals(uuid)){
			uuid = UUID.randomUUID().toString().replace("-", "");
			userSessionIdLocal.set(uuid);
		}
		return uuid;
	}
	
	public void setUserSessionUUID(String usersessionId){
		userSessionIdLocal.set(usersessionId);
	}
	public void clear(){
		userSessionIdLocal.remove();
	}
	
	
}
