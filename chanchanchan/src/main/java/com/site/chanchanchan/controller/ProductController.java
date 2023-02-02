package com.site.chanchanchan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.mapper.ProductMapper;
import com.site.chanchanchan.service.CategoryService;
import com.site.chanchanchan.service.ProductService;

@Controller
public class ProductController {

	String dir = "product/";
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	ProductMapper prodmapper;
	
	@RequestMapping("/product")
	public String product(Model model, int category_id) {
		Category cate = null;
		List<Product> prodlist = null;
		try {
			cate = categoryservice.get(category_id);
			
			prodlist = productservice.getprod(category_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("ca", cate);
		model.addAttribute("prodlist", prodlist);
		model.addAttribute("center", dir + "product");
		
		return "index";
	}
	
	@RequestMapping("/productdetails")
	public String productdetails(Model model, int product_id) {
		Product prod = null;
		try {
						
			prod = productservice.get(product_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pr", prod);
		model.addAttribute("center", dir + "productdetails");
		
		return "index";
	}
	
	@RequestMapping("/searchimpl")
	public String searchimpl(Model model, String txt) {
		List<Product> prodlist = null;
		try {
			prodlist=prodmapper.searchprod(txt);
				if(prodlist.isEmpty()) {
					model.addAttribute("center", dir + "searchfail");
				}else {
					model.addAttribute("prodlist", prodlist);
					model.addAttribute("center", dir + "search");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	
	}
}
