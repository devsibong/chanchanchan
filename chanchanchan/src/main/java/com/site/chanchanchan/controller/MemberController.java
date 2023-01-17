package com.site.chanchanchan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.MemberService;


@Controller
public class MemberController {

	String dir = "login/";

	@Autowired
	MemberService memservice;
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center", dir + "login");
		return "main";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(HttpSession session, String id, String pwd, Model model) {
		Member mem = null;
		String result = "loginfail";
		try {
			mem = memservice.get(id);
			if(mem != null) {
				if(mem.getMember_pw().equals(pwd)) { // 성공
					result = "login";
					session.setAttribute("loginmem", mem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", result);
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
}
