package com.site.chanchanchan.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.MemberService;

@Controller
public class OrderController {
	
	String dir = "order/";
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;

	@RequestMapping("/order")
	public String myCart(HttpSession session, Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("center", dir + "order");
			List<Cart> cartList = cartService.getByMember(Integer.toString(loginMember.getMember_index()));
			model.addAttribute("cartList", cartList);
			return "index";
		}
	}
	
	@PostMapping("/getmemberinfo")
	@ResponseBody
    public Member changeCartCount(@RequestBody Member member) throws Exception {
		Member resultMember = memberService.getByIndex(Integer.toString(member.getMember_index()));
        return resultMember;
    }

}
