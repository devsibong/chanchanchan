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
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.dto.RegularOrderDetail;
import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.service.AnswerService;
import com.site.chanchanchan.service.CartService;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.OrderDetailService;
import com.site.chanchanchan.service.OrderListService;
import com.site.chanchanchan.service.PostService;
import com.site.chanchanchan.service.ProductService;
import com.site.chanchanchan.service.RegularOrderDetailService;
import com.site.chanchanchan.service.RegularOrderScheduleService;
import com.site.chanchanchan.service.ReviewService;

@Controller
public class MyPageController {
	
	String dir = "mypage/";
	
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
	
	@Autowired
	RegularOrderDetailService rodservice;
	
	@Autowired
	ProductService prservice;

	@Autowired
	AnswerService aservice;
	
	@Autowired
	CartService cservice;
	
	@Autowired
	RegularOrderScheduleService rosservice;
	
	@RequestMapping("/mypage")
	public String main(HttpSession session, Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			Member mem = mservice.getByIndex(Integer.toString(loginMember.getMember_index()));
			int cart = cservice.count(Integer.toString(loginMember.getMember_index()));
			int precount = olservice.precount(Integer.toString(loginMember.getMember_index()));
			int ingcount = olservice.ingcount(Integer.toString(loginMember.getMember_index()));
			int comcount = olservice.comcount(Integer.toString(loginMember.getMember_index()));
			
			model.addAttribute("mem", mem);
			model.addAttribute("cart", cart);
			model.addAttribute("precount", precount);
			model.addAttribute("ingcount", ingcount);
			model.addAttribute("comcount", comcount);
			model.addAttribute("center", dir + "mypagecenter");
			return "index";
		}
	}
	
	//주문내역
	@RequestMapping("/ordshipselupd")
	public String ordshipselupd(HttpSession session,Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			List<OrderDetail> list = odservice.list(loginMember.getMember_index());
		
		System.out.println(list);
		model.addAttribute("list", list);
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/ordshipselupd");
		return "index";
		}
	}
	
	//주문내역 삭제
		@RequestMapping("/orderdel")  
		public String orderdel(Model model, Integer order_id) {
			try {
				odservice.remove(order_id);
				olservice.remove(order_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/orderdel");
			return "redirect:/ordshipselupd";
		}
	
	//주문내역 상세페이지
	@RequestMapping("/orddetail")
	public String orddetail(Model model,Integer orderdetail_id) {
		OrderDetail orderdetail = null;
		
		try {
			orderdetail = odservice.orddetail(orderdetail_id);
			model.addAttribute("orderdetail", orderdetail);
			System.out.println(orderdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/orddetail");
		return "index";
	}
	
	//정기주문 내역
	@RequestMapping("/regordshipselupd")
	public String regordshipselupd(HttpSession session,Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			List<RegularOrderDetail> list = rodservice.list(loginMember.getMember_index());
		
		
		model.addAttribute("list", list);
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/regordshipselupd");
		return "index";
		}
	}
	
	//정기주문내역 삭제
		@RequestMapping("/regorderdel")  
		public String regorderdel(Model model, Integer order_id) {
			try {
				rosservice.schdelete(order_id);
				rodservice.remove(order_id);
				olservice.remove(order_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/regorderdel");
			return "redirect:/regordshipselupd";
		}
	
	//정기 주문내역 상세페이지
	@RequestMapping("/regorddetail")
	public String regorddetail(Model model,Integer regular_orderdetail_id) {
		RegularOrderDetail regorderdetail = null;
		
		try {
			regorderdetail = rodservice.regorddetail(regular_orderdetail_id);
			model.addAttribute("regorderdetail", regorderdetail);
			System.out.println(regorderdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/regorddetail");
		return "index";
	}
			
	
	
	// 리뷰 목록
	@RequestMapping("/review")
	public String review(HttpSession session,Model model) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			List<OrderDetail> list = odservice.list(loginMember.getMember_index());
		
		
		model.addAttribute("list", list);
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/review");
		return "index";
		}
	}
	//리뷰작성폼
	@RequestMapping("/reviewdo")
	public String reviewdo(HttpSession session,Model model,Integer product_id) throws Exception {
		Member loginMember = (Member)session.getAttribute("loginmem");
		if (loginMember == null) {
			return "redirect:/login";
		} else {
			int memdex = loginMember.getMember_index();
			Product pr = prservice.get(product_id);
			
		model.addAttribute("pr", pr);
		model.addAttribute("memdex", memdex);
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/reviewdo");
		return "index";
		}
	}
	
	//리뷰작성 ok
	@RequestMapping("/reviewok")
	public String reviewok(Model model,Review review) throws Exception {
		rvservice.register(review);
		
		
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/reviewdetail");
		return "index"; 
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
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage","/mypage/inquiry");
		
		return "index";
	}
	
	//문의글 작성폼
	@RequestMapping("/inquiryQuestion")  
	public String  inquiryQuestion(Model model,HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginmem");
		int memdex = loginMember.getMember_index();
		
		model.addAttribute("memdex", memdex);
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/inquiryQuestion");
		return "index";
	}
	
	//문의글 작성폼OK
	@RequestMapping("/inquiryQuestionOk")  
	public String  inquiryQuestionOk(Model model,Post post ) {	
		try {
			pservice.register(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/inquiryQuestion");
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
		
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/inquiryview");
		return "index";
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
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/inquiryupd");
			return "index";
		}
	
	//문의글 수정 OK
			@RequestMapping("/inquiryupdok")  
			public String  inquiryupdok(Model model, Integer post_id,Post post) {	
				try {
					pservice.modify(post);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model.addAttribute("center", dir + "mypagecenter");
				model.addAttribute("mypage", "/mypage/inquiryupd");
				return "redirect:/inquiry";
			}
		
	//문의글 삭제
	@RequestMapping("/inquirydel")  
	public String  inquirydel(Model model, Integer post_id) {
		try {
			aservice.remove(post_id);
			pservice.remove(post_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/inquirydel");
		return "redirect:/inquiry";
	}
	
	@RequestMapping("/memuplogin")
	public String memuplogin(Model model,HttpSession session) throws Exception{
		
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/memuplogin");
		return "index";
	}
	
	@RequestMapping("/memuploginok")
	public String memuploginok(Model model,HttpSession session,String member_pw) throws Exception{
		Member loginMember = (Member)session.getAttribute("loginmem");
		String pw = loginMember.getMember_pw();
		model.addAttribute("pw", pw);
		if(member_pw.equals(loginMember.getMember_pw())) {
			model.addAttribute("member", loginMember);
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/memberupdate");
			
			return "index";
		}else {	
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/memuploginfail");
			return "index";
		}
	}
	
	@RequestMapping("/memuploginfail")
	public String memuploginfail(Model model,HttpSession session,String member_pw) throws Exception{
		Member loginMember = (Member)session.getAttribute("loginmem");
		String pw = loginMember.getMember_pw();
		model.addAttribute("pw", pw);
		if(member_pw.equals(loginMember.getMember_pw())) {
			model.addAttribute("member", loginMember);
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/memberupdate");
			
			return "index";
		}else {	
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/memuploginfail");
			return "index";
		}
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
		
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/memberupdate");
		return "index";
		
	}
	
	//회원정보 수정OK
		@RequestMapping("/memberupdateok")
		public String memberupdateok(Model model,Member member) throws Exception {
			mservice.modify(member);
			model.addAttribute("center", dir + "mypagecenter");
			model.addAttribute("mypage", "/mypage/memberupdateok");
			return "redirect:/mypage";
			
		}
	
	@RequestMapping("/coupon")
	public String coupon(Model model) {
		model.addAttribute("center", dir + "mypagecenter");
		model.addAttribute("mypage", "/mypage/coupon");
		return "index";
	}
	
}
