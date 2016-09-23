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
//		if("2bd0cd787a144e7db537c6ae9062cdb3".equals(uuid))
//			System.out.println("111111111111111");
//		if(!"20591B3487FC9548C8EEA8D8F7F7714C".equals(uuid))
//				System.out.println("2222222222");
		//System.out.println(uuid+"==============》当前sessionID");
		return uuid;
	}
	
	public void setUserSessionUUID(String usersessionId){
		userSessionIdLocal.set(usersessionId);
	}
	public void clear(){
		userSessionIdLocal.remove();
	}
	
	
}
