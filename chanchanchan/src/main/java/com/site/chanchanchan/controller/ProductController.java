package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.mapper.ProductMapper;
import com.site.chanchanchan.service.CategoryService;
import com.site.chanchanchan.service.PostService;
import com.site.chanchanchan.service.ProductService;
import com.site.chanchanchan.service.ReviewService;

@Controller
public class ProductController {

	String dir = "product/";
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	ProductMapper prodmapper;
	
	@Autowired
	PostService pservice;
	
	@Autowired
	ReviewService rvservice;
	
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
	public String productdetails(Model model, int product_id,HttpSession session,
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			@RequestParam(value="amount", defaultValue="10") Integer amount,
			@RequestParam(value="option",defaultValue="") String option,
			@RequestParam(value="searchVal",defaultValue="") String searchVal) {
		if(session.getAttribute("option")!=null && session.getAttribute("searchVal")!=null) {
			option=(String)session.getAttribute("option");
			searchVal=(String)session.getAttribute("searchVal");
		
		}
		
		boolean isSearchOk = false;
		if(option.equals("") || searchVal.equals("")) {
			isSearchOk=true;
		}
		
		Criteria cri = new Criteria(pageNum,amount,option,searchVal,isSearchOk);
		
		int total=0;
		
		List<Review> list=null;
		Product prod = null;
		try {
						
			prod = productservice.get(product_id);
			list= rvservice.listByIndex(product_id);
			total = pservice.getTotal(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Page page = new Page(cri,total);
		
		session.setAttribute("cri_value",cri);
		
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
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
