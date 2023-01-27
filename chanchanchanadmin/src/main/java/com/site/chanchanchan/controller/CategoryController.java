package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.service.CategoryService;

@RequestMapping("/category")
@Controller
public class CategoryController {
	
	@Autowired
	CategoryService cateservice;
	
	String dir ="list/";
	
	//페이징(일반,검색) 
	@GetMapping("/list")
	public String get(Model model, HttpSession session,
				@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
				@RequestParam(value="amount", defaultValue="10") Integer amount,
				@RequestParam(value="option",defaultValue="") String option,
				@RequestParam(value="searchVal",defaultValue="") String searchVal
				) {
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
		List<Category> categorys=null;
		
		try {
			categorys= cateservice.getListByPaging(cri);
			total = cateservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("category",categorys);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"category");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/category/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			cateservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	
	@RequestMapping("/popupmodify")
	public String popupModify() {
		return "popup/categorymodify";
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	public String modify(int category_id, String category_title, int category_parent) {
		Category category = new Category(category_id,category_title,category_parent);
		category.toString();
		try {
			cateservice.modify(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rediret:/category/popupmodify";
	}
	
	@RequestMapping("/popupsignUp")
	public String popupSignUp() {
		return "popup/categoryregister";
	}

	@ResponseBody
	@RequestMapping("/register")
	public String register(String category_title, int category_parent) {
		int category_id=0;
		Category category = new Category(category_id,category_title,category_parent);
		category.toString();
		try {
			cateservice.register(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rediret:/category/popupsignUp";
	}
}
