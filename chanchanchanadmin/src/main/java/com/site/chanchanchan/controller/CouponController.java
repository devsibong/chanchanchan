package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Coupon;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.service.CouponService;
import com.site.chanchanchan.service.MemberService;

@RequestMapping("/coupon")
@Controller
public class CouponController {
	
	@Autowired
	CouponService couponservice;
	
	@Autowired
	MemberService memservice;
	
	String dir ="list/";
	
	//페이징(일반,검색) 
	@GetMapping("/list")
	public String get(Model model, HttpSession session,
				@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
				@RequestParam(value="amount", defaultValue="10") Integer amount,
				@RequestParam(value="option",defaultValue="") String option,
				@RequestParam(value="searchVal",defaultValue="") String searchVal
				) {
		if(session.getAttribute("option")!=null && session.getAttribute("searchVal")!=null) {
			option=(String)session.getAttribute("option");
			searchVal=(String)session.getAttribute("searchVal");
		
		}
		
		boolean isSearchOk = false;
		if(option.equals("") || searchVal.equals("")) {
			isSearchOk=true;
		}
		
		Criteria cri = new Criteria(pageNum,amount,option,searchVal,isSearchOk);
		
		int total=0;
		List<Coupon> coupons=null;
		
		try {
			coupons= couponservice.getListByPaging(cri);
			total = couponservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("coupon",coupons);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"coupon");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/coupon/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			couponservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "rediret:/coupon/list";
	}
	
	//승인버튼
	@ResponseBody
	@RequestMapping("/restore")
	public String restore(int res) {
		try {
			couponservice.changeStatus(res);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping("/createcoupon")
	public String createCoupon() {
		return "popup/createcoupon";
	}
	
	@ResponseBody
	@RequestMapping("/popupcreatecoupon")
	public String popupCreateCoupon(String member_rank, int coupon_minprice, int coupon_discountper, String coupon_expiredate) {
		List<Member> listmems =null;
		try {
			listmems = memservice.search(member_rank);
			int member_index;
			Coupon coupon;
			for(int i=0;i<listmems.size();i++) {
				Member mem=listmems.get(i);
				member_index=mem.getMember_index();
				
				coupon=new Coupon(member_index,coupon_minprice,coupon_discountper,coupon_expiredate);
				couponservice.register(coupon);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "popup/createcouponfail";
		}
		return "popup/createcouponok";
	}
	
}
