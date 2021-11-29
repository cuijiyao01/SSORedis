package com.hackday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

	/**
	 * 打开首页
	 */
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}
}
