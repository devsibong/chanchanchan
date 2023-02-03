package com.site.chanchanchan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Admin;
import com.site.chanchanchan.service.AdminService;

@Controller
public class MainController {
	
	@Autowired
	AdminService admservice;
	
	@RequestMapping("/main")
	public String main(Model model) {
		return "redirect:/product/info";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/loginadmin")
	public String loginiadmin(HttpSession session, String id, String pwd, Model model) {
		Admin adm = null;
		try {
			adm = admservice.search(id);
			if(adm != null) {
				if(adm.getAdmin_pwd().equals(pwd)) {
					if(adm.getAdmin_status()=='N') {
						return "login/loginadmfail";
					}	
					session.setAttribute("loginadm", adm);

					return "redirect:/main";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/loginfail";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "login/register";
	}
	
	@RequestMapping("/registeradm")
	public String registeradm(Admin adm) {
		try {
			admservice.register(adm);
		} catch (Exception e) {
			return "login/registerfail";
		}
		return "login/registerok";
	}
	
	@RequestMapping("/forgotpwd")
	public String forgotpwd() {
		return "forgot-password";
	}
}
	