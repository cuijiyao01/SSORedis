package com.hackday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@GetMapping("/loginIndex")
	public ModelAndView showLogin(String redirect) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("redirect", redirect);
		modelAndView.setViewName("loginIndex");
		return modelAndView;
	}
	
}
