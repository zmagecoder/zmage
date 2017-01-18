package com.mage.platform.framework.store.plugin.dfs.pool;

import org.apache.log4j.Logger;

/**
 * 日志打印类
 * 通过在log4j中配置imageServerLogger，当连接出现问题时，可方便查看日志。
 * @author zhanghua
 *
 */
public class FileServerPoolSysout {
	
	private static Logger logger = Logger.getLogger(FileServerPoolSysout.class);
	
	public static void info(Object o){
		logger.info(o.toString());
	}
	
	public static void warn(Object o) {
		logger.error(o.toString());
	}
}
