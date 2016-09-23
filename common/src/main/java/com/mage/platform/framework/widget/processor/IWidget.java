package com.mage.platform.framework.widget.processor;

import java.util.Map;

/**
 * 挂件接口类
 * @author pzh
 * @date 2016年6月13日 下午2:48:23
 */
public interface IWidget {
	
	/**
	 * 解析挂件并返回解析后的html片段
	 * @author pzh
	 * @date 2016年6月14日 上午11:28:59
	 * @param params 挂件参数Map
	 * @return 解析后的html片段
	 */
	public  String process(Map<String,String> params);
	
	/**
	 * 挂件是否可以缓存
	 * 根据配置获取，默认不可缓存
	 * @author pzh
	 * @date 2016年6月14日 上午11:26:11
	 * @return
	 */
	public boolean cacheAble();
	
	/**
	 * 是否为静态模式
	 * @author pzh
	 * @date 2016年6月14日 上午11:30:20
	 * @return true:静态模式  false 编辑、预览模式
	 */
	public boolean isStaticMode();
	
	/**
	 * 传送数据
	 */
	public void initParam();
}
