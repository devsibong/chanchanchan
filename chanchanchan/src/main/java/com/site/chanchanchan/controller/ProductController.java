package com.site.chanchanchan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.service.CategoryService;
import com.site.chanchanchan.service.ProductService;

@Controller
public class ProductController {

	String dir = "product/";
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CategoryService categoryservice;
	
	@RequestMapping("/product")
	public String product(Model model, int category_id) {
		List<Category> catelist = null;
		try {
			catelist = categoryservice.getcate();
			model.addAttribute("catelist", catelist);
			model.addAttribute("center", dir + "product");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
