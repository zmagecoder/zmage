package com.mage.platform.framework.web.processor.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.web.processor.ILocalFrontProcessor;
import com.mage.platform.framework.web.processor.IWebProcessor;

/**
 * 前端统一处理类
 * @author pzh
 * @date 2016年7月13日 下午6:21:47
 */
@Service
public class FrontProcessor implements IWebProcessor{
	
	private ILocalFrontProcessor localFrontProcessor;			//本地自定义拦截器
	
	@Override
	public void process(Map<String, Object> model, HttpServletRequest request) {
		
		//本地代码处理逻辑
		localFrontProcessor = SpringContextHolder.getBean("localFrontProcessor");
		if(null != localFrontProcessor)
			localFrontProcessor.process(model, request);
		
		//TODO 通用前台处理类
	}
	
}
