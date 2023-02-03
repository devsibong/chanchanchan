package com.site.chanchanchan.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.OrderListService;

@Controller
public class OrderController {
	
	String dir = "order/";
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	OrderListService orderListService;
	

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
	
	@PostMapping("/payment")
    public String payment(Model model, @RequestParam Map<String, Object> param) throws Exception {
		
		int member_index = Integer.parseInt(String.valueOf(param.get("member_index")));
		int product_totalprice = Integer.parseInt(String.valueOf(param.get("product_totalprice")));
		int shippingfee = Integer.parseInt(String.valueOf(param.get("shippingfee")));
		int order_totalpayment = Integer.parseInt(String.valueOf(param.get("order_totalpayment")));
		String payment_method = (String)param.get("payment_method");
		String receiver = (String)param.get("receiver");
		String receiver_tel = (String)param.get("receiver_tel");
		String delivery_info = (String)param.get("delivery_info");
		String shipping_title = (String)param.get("shipping_title");
		String shipping_address = (String)param.get("shipping_address");
		String shipping_zipcode = (String)param.get("shipping_zipcode");
		OrderList orderList = OrderList.builder()
								.member_index(Integer.parseInt(String.valueOf(param.get("member_index"))))
								.product_totalprice(Integer.parseInt(String.valueOf(param.get("product_totalprice"))))
								.shippingfee(Integer.parseInt(String.valueOf(param.get("shippingfee"))))
								.order_totalpayment(Integer.parseInt(String.valueOf(param.get("order_totalpayment"))))
								.payment_method((String)param.get("payment_method"))
								.order_state((String)param.get("order_state"))
								.receiver((String)param.get("receiver"))
								.receiver_tel((String)param.get("receiver_tel"))
								.delivery_info((String)param.get("delivery_info"))
								.build();
		
//		Shipping shipping = Shipping.builder()
//							.member_index(Integer.parseInt(String.valueOf(param.get("member_index"))))
//							.shipping_title((String)param.get("shipping_title"))
//							.shipping_address((String)param.get("shipping_address"))
//							.shipping_address_detail((String)param.get("shipping_address_detail"))
//							.shipping_zipcode((String)param.get("shipping_zipcode"))
//							.build();
//		OrderDetail orderDetail = OrderDetail.builder()
//									.product_id(product_id)
//									.order_id(order_id)
//									.orderdetail_count(orderdetail_count)
//									.orderdetail_price
//				
//				
//				
//				
//				
//							.build();
		
		orderListService.register(orderList);
		orderList.getOrder_id();
		model.addAttribute("center", dir + "ordercomplete");
		return "redirect:/";
    }
	
	@GetMapping("/ordercomplete")
	public String orderComplete(Model model) {
		model.addAttribute("center", dir + "ordercomplete");
		return "index";
	}
	

}
