package com.mage.introduce.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
//	@Resource
//	private IWidgetPageParser cmsWidgetPageParser;
	
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, Model model){
		
		return "introduce/index";
	}
}
