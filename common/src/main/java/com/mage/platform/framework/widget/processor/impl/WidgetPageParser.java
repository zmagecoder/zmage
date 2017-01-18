package com.mage.platform.framework.widget.processor.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.widget.processor.IWidget;
import com.mage.platform.framework.widget.processor.IWidgetPageParser;
import com.mage.platform.framework.widget.processor.IWidgetParamParser;

/**
 * 挂件界面解析器
 * @author pzh
 * @date 2016年6月14日 下午2:34:20
 */
@Service
public class WidgetPageParser implements IWidgetPageParser {
	
	private static final Log log = LogFactory.getLog(WidgetPageParser.class);
	
	@Resource
	private IWidgetParamParser widgetParamParser;
	
	@Override
	public void parse(String path, Model model) {
		Map<String, Map<String, Map<String,String>>> pages = widgetParamParser.parse();
		if(null == pages || pages.isEmpty()){
			log.error("================>>界面[" + path + "]，没有定义插件");
			return;
		}
		
		Map<String, Map<String,String>> widgets = pages.get(path);				//获取当前页面用到的插件
		if(null != widgets && !widgets.isEmpty()){
			Set<String> widgetSet = widgets.keySet();
			for (String key : widgetSet) {
				IWidget widgetBean = SpringContextHolder.getBean(key);
				if(widgetBean == null)
					throw new RuntimeException("=====================>>插件[" + key + "]未定义");
				Map<String,String> widget = widgets.get(key);					//插件配置
				String widgetContent = widgetBean.process(widget);				//解析
				model.addAttribute("widget_" + key, widgetContent);
			}
		}
	}
}
