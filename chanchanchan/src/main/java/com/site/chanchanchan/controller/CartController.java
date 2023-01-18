package com.site.chanchanchan.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.service.CartService;


@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("/cart")
	public String myCart(Model model) {
		List<Cart> list = null;
		try {
			list = cartService.get();
			model.addAttribute("cartList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mycart";
	}
}
