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

@Controller
public class CartController {
	
	String dir = "cart/";
	
	@Autowired
	CartService cartService;

	@RequestMapping("/cart")
	public String myCart(HttpSession session, Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("center", dir + "mycart");
			List<Cart> cartList = cartService.getByMember(Integer.toString(loginMember.getMember_index()));
			model.addAttribute("cartList", cartList);
			return "index";
		}
	}
	
	@PostMapping("/updatecart")
	@ResponseBody
    public Cart changeCartCount(@RequestBody Cart cart) throws Exception {
		cartService.modifyCount(cart);
		Cart resultCart = cartService.get(Integer.toString(cart.getCart_id()));
        return resultCart;
    }
	
	@PostMapping("/deletecart")
    public String deleteCart(@RequestBody Cart cart) throws Exception {
		String temp = Integer.toString(cart.getCart_id());
		cartService.remove(temp);
		return "index";
    }
	
	

}
