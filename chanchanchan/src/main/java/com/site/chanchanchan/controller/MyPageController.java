package com.site.chanchanchan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {
	
	@Autowired
	
	
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
	
	@RequestMapping("/mypage/inquiry")
	public String  inquiry(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiry");
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
