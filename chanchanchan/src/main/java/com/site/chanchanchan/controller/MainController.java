package com.site.chanchanchan.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.CategoryService;


@Controller
public class MainController {
	

	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	CartService cartService;
	

	@RequestMapping("/")
	public String main(HttpSession session, Model model) {
		return "index";
	}

}