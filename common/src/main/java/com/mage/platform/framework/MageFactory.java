package com.mage.platform.framework;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.web.processor.IWebProcessor;

/**
 * 通用工厂类
 * @author pzh
 * @date 2016年7月13日 下午6:25:43
 */
public final class MageFactory {
	
	/**
	 * 获取界面通用处理器
	 * @author pzh
	 * @date 2016年7月13日 下午6:30:41
	 * @return
	 */
	public static IWebProcessor getWebProcessor(){
		IWebProcessor webProcessor = SpringContextHolder.getBean("backProcessor");
		return webProcessor;
	}
}
