package com.site.chanchanchan.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.CategoryService;
import com.site.chanchanchan.service.ProductService;


@Controller
public class MainController {
	

	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	

	@RequestMapping("/")
	public String main(HttpSession session, Model model) throws Exception {
		List<Product> prodlist = productService.getprod(154);
		model.addAttribute("prodlist", prodlist);
		return "index";
	}

}
