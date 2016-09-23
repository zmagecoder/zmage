package com.mage.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mage.test.model.User;
import com.mage.test.service.IUserService;

@Controller
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/user/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		User user = this.userService.getUserById("31");
		model.addAttribute("user", user);
		return "test/mvc/index";
	}
}
