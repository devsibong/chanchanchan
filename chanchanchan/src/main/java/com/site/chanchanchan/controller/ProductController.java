package com.site.chanchanchan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.dto.Product;
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
		Category cate = null;
		List<Category> catelist = null;
		List<Product> prodlist = null;
		try {
			cate = categoryservice.get(category_id);
			catelist = categoryservice.get();
			prodlist = productservice.getprod(category_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("ca", cate);
		model.addAttribute("catelist", catelist);
		model.addAttribute("prodlist", prodlist);
		model.addAttribute("center", dir + "product");
		
		return "index";
	}
}
