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

import com.site.chanchanchan.dto.Admin;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.service.AdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	AdminService admservice;
	
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
		List<Admin> adms=null;
		
		try {
			adms= admservice.getListByPaging(cri);
			total = admservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("admin",adms);
		model.addAttribute("pageMaker", page);
		
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"admin");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/admin/list";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("center","login/mypage");
		return "main";
	}
	
	@RequestMapping("/mypagemodify")
	public String mypagemodfiy(Model model) {
		model.addAttribute("center","login/mypagemodify");
		model.addAttribute("admin", new Admin());
		return "main";
	}
	
	@RequestMapping("/mypagemodifycomplete")
	public String mypagemodfiycomplete(@ModelAttribute("admin") Admin admin, HttpSession session) {
		try {
			System.out.println(admin.toString());
			admservice.modify(admin);
		} catch (Exception e) {
			return "login/mypagemodifyfail";
		}
		session.setAttribute("loginadm", admin);
		return "login/mypagemodifyok";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			admservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	//승인버튼
	@ResponseBody
	@RequestMapping("/approval")
	public String approval(int apr) {
		try {
			admservice.changeStatus(apr);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
}

