package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.AdminPost;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.service.AdminpostService;

@RequestMapping("/adminpost")
@Controller
public class AdminpostController {
	
	@Autowired
	AdminpostService admpostservice;
	
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
		
		List<AdminPost> admposts=null;
		
		try {
			admposts= admpostservice.getListByPaging(cri);
			total = admpostservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		session.setAttribute("cri_value",cri);
		
		model.addAttribute("admin_post",admposts);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"adminpost");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/adminpost/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			admpostservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	//게시글 뷰페이지
	@GetMapping("/view")
	public String get(Model model, HttpSession session, 
			@RequestParam(value="id", defaultValue="0") Integer id){
		AdminPost admpost = null;
		try {
			admpost= admpostservice.get(id);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		model.addAttribute("adminpostView",admpost);
		model.addAttribute("center","view/adminpostview");
		return "main";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {

		model.addAttribute("center","view/adminpostregister");
		model.addAttribute("AdminPost", new AdminPost());
		return "main";
	}
	
	@RequestMapping("/registerform")
	public String registerForm(@ModelAttribute("AdminPost") AdminPost admpost) {
		System.out.println(admpost.toString());
		try {
			admpostservice.register(admpost);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/adminpostregisterfail";
		}
		return "popup/adminpostregisterok";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model, @RequestParam(value="id", defaultValue="0") Integer adminpost_id ){
		AdminPost admpost = null;
		try {
			admpost= admpostservice.get(adminpost_id);
			System.out.println(admpost.toString());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		model.addAttribute("admpost",admpost);
		model.addAttribute("center","view/adminpostmodify");
		model.addAttribute("AdminPost", new AdminPost());
		return "main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute("AdminPost") AdminPost admpost) {
		try {
			admpostservice.modify(admpost);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/adminpostmodifyfail";
		}
		return "popup/adminpostmodifyok";
	}
}
