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

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.service.OrderListService;

@RequestMapping("/orderlist")
@Controller
public class OrderListController {
	
	@Autowired
	OrderListService olservice;
	
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
		List<OrderList> ols=null;
		
		try {
			ols= olservice.getListByPaging(cri);
			total = olservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("orderlist",ols);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"orderlist");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/orderlist/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			olservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/changestate")
	public String changeState(int order_id, String order_state) {
		try {
			olservice.changeState(new OrderList(order_id,order_state));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "list/orderlist";
	}
	
	//게시글 뷰페이지
	@GetMapping("/view")
	public String get(Model model, HttpSession session, 
			@RequestParam(value="id", defaultValue="0") Integer id){
		 OrderList ol = null;
		try {
			ol = olservice.get(id);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		model.addAttribute("orderlistView",ol);
		model.addAttribute("center","view/orderlistview");
		return "main";
	}
}
