package com.mage.context;

import java.io.Serializable;
import java.util.Map;

import com.mage.common.ServiceMethodHandler;

/**
 * ROP服务方法的处理者的注册表  
 * @author pzh
 * @version 1.0
 */
public interface RopContext extends Serializable {

	/**
	 * 注册一个服务处理器
	 * 
	 * @param methodName
	 * @param version
	 * @param serviceMethodHandler
	 */
	void addServiceMethod(String methodName, String version,
			ServiceMethodHandler serviceMethodHandler);

	/**
	 * 获取服务处理器
	 * 
	 * @param methodName
	 * @return
	 */
	ServiceMethodHandler getServiceMethodHandler(String methodName,
			String version);

	/**
	 * 是否是合法的服务方法
	 * 
	 * @param methodName
	 * @return
	 */
	boolean isValidMethod(String methodName);

	/**
	 * 是否是合法服务方法版本号
	 * 
	 * @param methodName
	 * @param version
	 * @return
	 */
	boolean isValidMethodVersion(String methodName, String version);

	/**
	 * 获取所有的处理器列表
	 * 
	 * @return
	 */
	Map<String, ServiceMethodHandler> getAllServiceMethodHandlers();

	/**
	 * 是开启签名功能
	 * 
	 * @return
	 */
	boolean isSignEnable();


}
