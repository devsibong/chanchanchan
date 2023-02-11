package com.site.chanchanchan.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Shipping;
import com.site.chanchanchan.service.ShippingService;


@Controller
public class MapController {
	String dir = "cart/";
	
	@Autowired
	ShippingService shippingService;
	

	@RequestMapping("/map")
	public String mapMain(HttpSession session, Model model) throws Exception {
		model.addAttribute("center", dir + "map");
		return "index";
	}
	
	@RequestMapping("/address")
	@ResponseBody
	public List<Shipping> address(HttpSession session, Model model) throws Exception {
		List<Shipping> shippingList = shippingService.get();
		return shippingList;
	}
}
