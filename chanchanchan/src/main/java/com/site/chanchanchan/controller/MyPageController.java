package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.OrderDetailService;
import com.site.chanchanchan.service.OrderListService;
import com.site.chanchanchan.service.PostService;
import com.site.chanchanchan.service.ReviewService;

@Controller
public class MyPageController {
	
	@Autowired
	PostService pservice;
	
	@Autowired
	OrderListService olservice;
	
	@Autowired
	OrderDetailService odservice;
	
	@Autowired
	MemberService mservice;
	
	@Autowired
	ReviewService rvservice;
	
	@RequestMapping("/mypage")
	public String main() {
		return "mypage/mypagemain";
	}
	
	//주문내역
	@RequestMapping("/ordshipselupd")
	public String ordshipselupd(HttpSession session,Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			List<OrderList> list = olservice.list(loginMember.getMember_index());
		
		
		model.addAttribute("list", list);
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/ordshipselupd");
		return "mypage/mypagemain";
		}
	}
	
	//주문내역 삭제
		@RequestMapping("/orderdel")  
		public String  orderdel(Model model, Integer order_id) {
			try {
				olservice.remove(order_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("left", "mypageleft");
			model.addAttribute("center", "/mypage/orderdel");
			return "redirect:/ordshipselupd";
		}
	
	//주문내역 상세페이지
	@RequestMapping("/orddetail")
	public String orddetail(Model model,Integer order_id) {
		OrderDetail orderdetail = null;
		System.out.println(order_id);
		try {
			orderdetail = odservice.orddetail(order_id);
			model.addAttribute("orderdetail", orderdetail);
			System.out.println(orderdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/orddetail");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/regordshipselupd")
	public String regordshipselupd(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/regordshipselupd");
		return "mypage/mypagemain";
	}
	
	@RequestMapping("/review")
	public String review(HttpSession session,Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			List<OrderList> list = olservice.reviewlist(loginMember.getMember_index());
		
		
		model.addAttribute("list", list);
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/review");
		return "mypage/mypagemain";
		}
	}
	
	@RequestMapping("/reviewdo")
	public String reviewdo(HttpSession session,Model model,Integer order_id) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			int memdex = loginMember.getMember_index();
			OrderDetail od = odservice.rvprid(order_id);
			
		model.addAttribute("od", od);
		model.addAttribute("memdex", memdex);
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/reviewdo");
		return "mypage/mypagemain";
		}
	}
	
	@RequestMapping("/reviewok")
	public String reviewok(Model model,Review review) throws Exception {
		rvservice.register(review);
		
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/reviewdetail");
		return "mypage/mypagemain";
	}
	
	
	@GetMapping("/inquiry")
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
		
		List<Post> posts=null;
		
		try {
			posts= pservice.getListByPaging(cri);
			total = pservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		session.setAttribute("cri_value",cri);
		
		model.addAttribute("list",posts);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center","/mypage/inquiry");
		
		return "mypage/mypagemain";
	}
	
	//문의글 작성폼
	@RequestMapping("/inquiryQuestion")  
	public String  inquiryQuestion(Model model) {	
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryQuestion");
		return "mypage/mypagemain";
	}
	
	//문의글 작성폼OK
	@RequestMapping("/inquiryQuestionOk")  
	public String  inquiryQuestionOk(Model model,Post post ) {	
		try {
			pservice.register(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryQuestion");
		return "redirect:/inquiry";
	}
	
	//문의글 상세
	@RequestMapping("/inquiryview")  
	public String inquiryview(Model model,Integer post_id) {
		Post post = null;
		try {
			post = pservice.get(post_id);
			model.addAttribute("post", post);
			System.out.println(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquiryview");
		return "mypage/mypagemain";
	}
	
	//문의글 수정
		@RequestMapping("/inquiryupd")  
		public String  inquiryupd(Model model, Integer post_id) {	
			Post post=null;
			try {
				post = pservice.get(post_id);
				model.addAttribute("post", post);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("left", "mypageleft");
			model.addAttribute("center", "/mypage/inquiryupd");
			return "mypage/mypagemain";
		}
	
	//문의글 수정 OK
			@RequestMapping("/inquiryupdok")  
			public String  inquiryupdok(Model model, Integer post_id,Post post) {	
				try {
					pservice.modify(post);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model.addAttribute("left", "mypageleft");
				model.addAttribute("center", "/mypage/inquiryupd");
				return "redirect:/inquiry";
			}
		
	//문의글 삭제
	@RequestMapping("/inquirydel")  
	public String  inquirydel(Model model, Integer post_id) {
		try {
			pservice.remove(post_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/inquirydel");
		return "redirect:/inquiry";
	}
	
	//회원정보 수정
	@RequestMapping("/memberupdate")
	public String memberupdate(HttpSession session,Model model) throws Exception{
		Member loginMember = (Member)session.getAttribute("loginmem");
		
		if (loginMember == null) {
			return "redirect:/login";
		} else {
		Member member = mservice.get(loginMember.getMember_id());
		model.addAttribute("member", member);
		}
		
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/memberupdate");
		return "mypage/mypagemain";
		
	}
	
	//회원정보 수정OK
		@RequestMapping("/memberupdateok")
		public String memberupdateok(Model model,Member member) throws Exception {
			mservice.modify(member);
			model.addAttribute("left", "mypageleft");
			model.addAttribute("center", "/mypage/memberupdateok");
			return "mypage/mypagemain";
			
		}
	
	@RequestMapping("/coupon")
	public String coupon(Model model) {
		model.addAttribute("left", "mypageleft");
		model.addAttribute("center", "/mypage/coupon");
		return "mypage/mypagemain";
	}
	
}
