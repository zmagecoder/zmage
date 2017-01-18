package com.mage.platform.framework.widget.processor;

import org.springframework.ui.Model;


/**
 * 挂件页面解析器
 * @author pzh
 * @date 2016年6月14日 下午2:32:53
 */
public interface IWidgetPageParser {
	
	/**
	 * 
	 * @author pzh
	 * @date 2016年6月14日 下午2:44:05
	 * @param path 当前界面路径 widgets.xml中的pageId
	 * @param model 前段参数
	 */
	public void parse(String path, Model model);

}
