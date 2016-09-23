package com.mage.platform.framework.widget.processor.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mage.platform.framework.widget.processor.IWidgetParamParser;
import com.mage.platform.framework.widget.utils.WidgetXmlUtil;

/**
 * xml 挂件参数解析器
 * @author pzh
 * @date 2016年6月13日 下午3:08:53
 */
@Service
public class WidgetParamParser implements IWidgetParamParser {
	
	public Map<String, Map<String, Map<String,String>>> parse() {
		//TODO 启用缓存
		String path = "widget/widgets.xml";
		return WidgetXmlUtil.parse(path);
	}
}
