package com.mage.test.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mage.platform.framework.widget.processor.impl.AbstractWidget;

@Service
public class UserWidget extends AbstractWidget {

	private static Logger logger = Logger.getLogger(UserWidget.class);
	
	@Override
	protected void display(Map<String, String> params) {
		logger.info("===========================>>>挂件测试开始");
		params.put("user_name", "盆朝辉");
		logger.info("===========================>>>挂件测试结束");
	}
	
}
