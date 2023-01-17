package com.site.chanchanchan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart")
	public String myCart() {
		return "mycart";
	}
}
