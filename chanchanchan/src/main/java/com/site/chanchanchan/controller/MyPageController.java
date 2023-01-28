package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.PostService;

@Controller
public class MyPageController {
	
	@Autowired
	PostService pservice;
	
	@Autowired
	MemberService mservice;
	
	
	@RequestMapping("/mypage")
	public String main() {
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/ordshipselupd")
	public String ordshipselupd(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/ordshipselupd");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/regordshipselupd")
	public String regordshipselupd(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/regordshipselupd");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/review")
	public String review(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/review");
		return "mypage/mypagemain";
	}
	
	@GetMapping("/mypage/inquiry")
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
		
		List<Post> posts=null;
		
		try {
			posts= pservice.getListByPaging(cri);
			total = pservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		session.setAttribute("cri_value",cri);
		
		model.addAttribute("list",posts);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center","/mypage/inquiry");
		
		return "mypage/mypagemain";
	}
	
	//문의글 작성폼
	@RequestMapping("/mypage/inquiryQuestion")  
	public String  inquiryQuestion(Model model) {	
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryQuestion");
		return "mypage/mypagemain";
	}
	
	//문의글 작성폼OK
	@RequestMapping("/mypage/inquiryQuestionOk")  
	public String  inquiryQuestionOk(Model model,Post post ) {	
		try {
			pservice.register(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryQuestion");
		return "redirect:/mypage/inquiry";
	}
	
	//문의글 상세
	@RequestMapping("/mypage/inquiryview")  
	public String inquiryview(Model model,Integer post_id) {
		Post post = null;
		try {
			post = pservice.get(post_id);
			model.addAttribute("post", post);
			System.out.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryview");
		return "mypage/mypagemain";
	}
	
	//문의글 수정
		@RequestMapping("/mypage/inquiryupd/{post_id}")  
		public String  inquiryupd(Model model,@PathVariable("post_id") Integer post_id) {	
			Post post=null;
			try {
				post = pservice.get(post_id);
				model.addAttribute("post", post);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("left", "mypageleft");
			model.addAttribute("center", "/mypage/inquiryupd");
			return "mypage/mypagemain";
		}
	
	//문의글 수정 OK
			@RequestMapping("/mypage/inquiryupdok/{post_id}")  
			public String  inquiryupdok(Model model,@PathVariable("post_id") Integer post_id,Post post) {	
				try {
					pservice.modify(post);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model.addAttribute("left", "mypageleft");
				model.addAttribute("center", "/mypage/inquiryupd");
				return "redirect:/mypage/inquiry";
			}
		
	//문의글 삭제
	@RequestMapping("/mypage/inquirydel")  
	public String  inquirydel(Model model, Integer post_id) {
		try {
			pservice.remove(post_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquirydel");
		return "redirect:/mypage/inquiry";
	}
	
	@RequestMapping("/mypage/memberupdate")
	public String memberupdate(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/memberupdate");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/coupon")
	public String coupon(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/coupon");
		return "mypage/mypagemain";
	}
	
}
