package com.mage.manage.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageController {
	
	@RequestMapping("/main")
	public String toIndex(HttpServletRequest request, Model model){
		
		return "manage/main";
	}
}