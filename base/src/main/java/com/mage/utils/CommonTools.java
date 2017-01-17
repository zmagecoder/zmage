package com.mage.utils;

import com.mage.context.GlobalThreadLocalHolder;

public class CommonTools {
	
	public static String getUserSessionId() {
		String session_id = getUUID();
		return session_id;
	}
	
	public static String getUUID(){
		String session_id = GlobalThreadLocalHolder.getInstance().getUUID();
		return session_id;
	}
}