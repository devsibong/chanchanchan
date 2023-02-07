package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Admin;
import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.service.AdminService;
import com.site.chanchanchan.service.OrderDetailService;
import com.site.chanchanchan.service.ProductService;

@Controller
public class MainController {
	
	@Autowired
	AdminService admservice;
	
	@Autowired
	OrderDetailService odservice;
	
	@Autowired
	ProductService productservice;
	
	@RequestMapping("/main")
	public String main(Model model) {
		List<Product> products=null;
		try {
			products=productservice.getall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<OrderDetail> ods= null;
		int limit=5;
		try {
			ods= odservice.bestProduct(new OrderDetail(limit));
			int rank=1;
		for(OrderDetail od :ods) {
			int product_id=0;
			product_id=od.getProduct_id();
			String name=productservice.getName(product_id);
			od.setProduct_name(name);
			od.setRank(rank++);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("product",products);
		model.addAttribute("bestproduct",ods);
		
		model.addAttribute("center","chart/chart1");
		model.addAttribute("center2","chart/bestproduct");
		return "main";
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
	