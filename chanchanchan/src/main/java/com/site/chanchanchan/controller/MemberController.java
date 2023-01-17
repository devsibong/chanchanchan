package com.site.chanchanchan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {

	String dir = "member/";

	@Autowired
	MemberService mem_service;
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center", dir + "login");
		return "main";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
}
