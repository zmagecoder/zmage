package com.mage.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mage.platform.framework.widget.processor.IWidgetPageParser;

@Controller
public class HomeController {
	
	@Resource
	private IWidgetPageParser cmsWidgetPageParser;
	
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, Model model){
		
		cmsWidgetPageParser.parse("sshop", model);
		
		return "view/cms/sshop";
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request,Model model){
		
		return "test/mvc/test";
	}
	
}
