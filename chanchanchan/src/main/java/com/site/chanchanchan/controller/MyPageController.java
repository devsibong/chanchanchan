package com.site.chanchanchan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.service.PostService;

@Controller
public class MyPageController {
	
	@Autowired
	PostService pservice;
	
	
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
	
	@RequestMapping("/mypage/inquiry")  //문의글 리스트
	public String  inquiry(Model model) {
		List<Post> list = null;
		try {
			list = pservice.list();
			model.addAttribute("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiry");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/inquiryQuestion")  //문의글 작성폼
	public String  inquiryQuestion(Model model,Post post) {
		try {
			pservice.register(post);
			System.out.println(post.getPost_subject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryQuestion");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/inquiryview")  //문의글 상세
	public String  inquiryview(Model model,Integer post_id) {
		Post post = null;
		try {
			pservice.get(post_id);
			model.addAttribute("post", post);
			System.out.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryview");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/mypage/inquirydel")  //문의글 상세
	public String  inquirydel(Model model,@RequestParam("post_id") Integer post_id) {
		try {
			pservice.remove(post_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryview");
		return "mypage/mypagemain";
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
