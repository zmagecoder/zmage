package com.mage.platform.framework.widget.processor;

import java.util.Map;

/**
 * 挂件参数解析器
 * @author pzh
 * @date 2016年6月13日 下午3:06:02
 */
public interface IWidgetParamParser {
	
	/**
	 * 解析widgets.xml的配置信息，
	 * @return 解析后的参数，一个Map嵌套，格式如下:<br>
	 *  String[pageId]->Map String[widgetId] -> Map String[paramName] -> String[paramValue] 
	 */
	public  Map<String, Map<String, Map<String,String>>> parse();

}
