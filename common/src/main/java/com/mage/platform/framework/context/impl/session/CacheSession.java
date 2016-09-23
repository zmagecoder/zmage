package com.mage.platform.framework.context.impl.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheSession implements Serializable {

	private static final long serialVersionUID = -95406145954923261L;
	
	private String sessionId;					//sessionID
	
	private Map<String, Object> attribute;		//session 属性值
	
	public CacheSession(String sessionId){
		this.sessionId = sessionId;
		attribute = new HashMap<String, Object>();
	}
	
	/**
	 * 获取属性值
	 * @author pzh
	 * @date 2016年6月16日 上午11:15:40
	 * @param arg0
	 * @return
	 */
	public Object getAttribute(String arg0) {
		return attribute.get(arg0);
	}

	/**
	 * 获取sessionID
	 * @author pzh
	 * @date 2016年6月16日 上午11:16:14
	 * @return
	 */
	public String getId() {
		return this.sessionId;
	}

	/**
	 * 获取session中的所有属性
	 * @author pzh
	 * @date 2016年6月16日 上午11:16:29
	 * @return
	 */
	public String[] getValueNames() {
		Set<String> keySet = this.attribute.keySet();
		return (String[])keySet.toArray();
	}

	/**
	 * session失效
	 * @author pzh
	 * @date 2016年6月16日 上午11:16:56
	 */
	public void invalidate() {
		this.attribute = null;
	}

	/**
	 * 清除session属性
	 * @author pzh
	 * @date 2016年6月16日 上午11:17:38
	 * @param arg0
	 */
	public void removeAttribute(String arg0) {
		this.attribute.remove(arg0);
	}

	/**
	 * 设置session的属性值
	 * @author pzh
	 * @date 2016年6月16日 上午11:17:57
	 * @param arg0
	 * @param arg1
	 */
	public void setAttribute(String arg0, Object arg1) {
		this.attribute.put(arg0, arg1);
	}
}
