	package com.site.chanchanchan.controller;


import java.util.ArrayList;
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
import com.site.chanchanchan.dto.RegularOrderDetail;
import com.site.chanchanchan.dto.RegularOrderSchedule;
import com.site.chanchanchan.dto.Shipping;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.OrderDetailService;
import com.site.chanchanchan.service.OrderListService;
import com.site.chanchanchan.service.RegularOrderDetailService;
import com.site.chanchanchan.service.RegularOrderScheduleService;
import com.site.chanchanchan.service.ShippingService;

@Controller
public class OrderController {
	
	String dir = "order/";
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	OrderListService orderListService;
	
	@Autowired
	ShippingService shippingService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	RegularOrderDetailService regularOrderDetailService;
	
	@Autowired
	RegularOrderScheduleService regularOrderScheduleService;
	

	@RequestMapping("/normalorder")
	public String normalOrder(HttpSession session, Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("center", dir + "order");
			List<Cart> cartList = cartService.getByMember(Integer.toString(loginMember.getMember_index()));
			List<Cart> regularCartList = new ArrayList<Cart>();
			List<Cart> normalCartList = new ArrayList<Cart>();
			
			
			//정기배송, 일반배송 분리
			for (Cart cart : cartList) {
				if (cart.getProduct().getCategory_id() == 153) {
					regularCartList.add(cart);
				} else {
					normalCartList.add(cart);
				}
			}
			model.addAttribute("normalCartList", normalCartList);
			return "index";
		}
	}
	
	@RequestMapping("/regularorder")
	public String regularOrder(HttpSession session, Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("center", dir + "regularorder");
			List<Cart> cartList = cartService.getByMember(Integer.toString(loginMember.getMember_index()));
			List<Cart> regularCartList = new ArrayList<Cart>();
			List<Cart> normalCartList = new ArrayList<Cart>();
			
			
			//정기배송, 일반배송 분리
			for (Cart cart : cartList) {
				if (cart.getProduct().getCategory_id() == 153) {
					regularCartList.add(cart);
				} else {
					normalCartList.add(cart);
				}
			}
			model.addAttribute("regularCartList", regularCartList);
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
    public void payment(Model model, @RequestParam Map<String, Object> param) throws Exception {
		
		int member_index = Integer.parseInt(String.valueOf(param.get("member_index")));
		int product_totalprice = Integer.parseInt(String.valueOf(param.get("product_totalprice")));
		int shippingfee = Integer.parseInt(String.valueOf(param.get("shipping_fee")));
		int order_totalpayment = Integer.parseInt(String.valueOf(param.get("order_totalpayment")));
		String payment_method = (String)param.get("payment_method");
		String receiver = (String)param.get("receiver");
		String receiver_tel = (String)param.get("receiver_tel");
		String delivery_info = (String)param.get("delivery_info");
		String shipping_title = (String)param.get("shipping_title");
		String shipping_address = (String)param.get("shipping_address");
		String shipping_address_detail = (String)param.get("shipping_address_detail");
		String shipping_zipcode = (String)param.get("shipping_zipcode");
		List<Cart> cartList = cartService.getByMember(Integer.toString(member_index));
		String regular_orderdate = (String) param.get("regular_orderdate");
		String temp = regular_orderdate.replace("년 ", "").replace("월 ", "").replace("일 ", "")
										.replace("월요일", "").replace("화요일", "").replace("수요일", "")
										.replace("목요일", "").replace("금요일", "").replace("토요일", "").replace("일요일", "");
		String[] regular_order = temp.split("\n");
		
		
		//OrderList 객체 생성
		OrderList orderList = OrderList.builder()
		.member_index(member_index)
		.product_totalprice(product_totalprice)
		.shippingfee(shippingfee)
		.order_totalpayment(order_totalpayment)
		.payment_method(payment_method)
		.order_state((String)param.get("order_state"))
		.receiver(receiver)
		.receiver_tel(receiver_tel)
		.delivery_info(delivery_info)
		.build();
		
		//Shipping 객체 생성
		Shipping shipping = Shipping.builder()
							.member_index(member_index)
							.shipping_title(shipping_title)
							.shipping_address(shipping_address)
							.shipping_address_detail(shipping_address_detail)
							.shipping_zipcode(shipping_zipcode)
							.build();

		orderListService.register(orderList);
		shippingService.register(shipping);
		
		int order_id = orderList.getOrder_id();
		
		//cartList -> orderDetail 데이터 이동(일반배송)
		if(regular_orderdate == null) {
			System.out.println("normal order");
			for(Cart cart : cartList) {
				int cart_id = cart.getCart_id();
				int product_id = cart.getProduct_id();
				int orderdetail_count = cart.getProduct_count();
				int orderdetail_price = cart.getProduct().getProduct_price() * orderdetail_count;
				OrderDetail orderDetail = OrderDetail.builder()
						.product_id(product_id)
						.order_id(order_id)
						.orderdetail_count(orderdetail_count)
						.orderdetail_price(orderdetail_price)
						.build();
				orderDetailService.register(orderDetail);
				cartService.remove(Integer.toString(cart_id));
			}
		} else { // 정기배송
			System.out.println("regular order");
			for(Cart cart : cartList) {
				int cart_id = cart.getCart_id();
				int product_id = cart.getProduct_id();
				int regular_price = cart.getProduct().getProduct_price();
				RegularOrderDetail regularorderDetail = RegularOrderDetail.builder()
						.product_id(product_id)
						.order_id(order_id)
						.regular_price(regular_price)
						.build();
				regularOrderDetailService.register(regularorderDetail);
				cartService.remove(Integer.toString(cart_id));
				for (String orderschedule : regular_order) {
					int regular_orderdetail_id = regularorderDetail.getRegular_orderdetail_id();
					String regular_shippingdate = orderschedule + "000000";
					String regular_shipping_state = "결제완료"; 
					RegularOrderSchedule regularorderSchedule = RegularOrderSchedule.builder()
																.regular_orderdetail_id(regular_orderdetail_id)
																.regular_shippingdate(regular_shippingdate)
																.regular_shipping_state(regular_shipping_state)
																.build();
					regularOrderScheduleService.register(regularorderSchedule);
				}
				
			}
		}
    }
	
	@GetMapping("/ordercomplete")
	public String orderComplete(Model model) {
		model.addAttribute("center", dir + "ordercomplete");
		return "index";
	}
}
