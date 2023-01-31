package com.site.chanchanchan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.service.CategoryService;

@Controller
public class MainController {
	
	@Autowired
	CategoryService categoryservice;
	
	@RequestMapping("/")
	public String main(Model model) {
		return "index";
	}
}