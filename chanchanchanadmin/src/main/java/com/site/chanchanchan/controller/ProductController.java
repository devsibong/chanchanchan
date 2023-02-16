package com.site.chanchanchan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.service.OrderDetailService;
import com.site.chanchanchan.service.ProductService;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	OrderDetailService odservice;
	
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
		List<Product> products=null;
		
		try {
			products= productservice.getListByPaging(cri);
			total = productservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("product",products);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"product");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/product/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			productservice.remove(del);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/changesale")
	public String changeSale(int product_id, int product_discount) {
		try {
			productservice.changeSale(product_id,product_discount);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "list/product";
	}
	
	//게시글 뷰페이지
	@GetMapping("/view")
	public String get(Model model, HttpSession session, 
			@RequestParam(value="id", defaultValue="0") Integer id){
		 Product product = null;
		try {
			product= productservice.get(id);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		model.addAttribute("productView",product);
		model.addAttribute("center","view/productview");
		return "main";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {

		model.addAttribute("center","view/productregister");
		model.addAttribute("Product", new Product());
		return "main";
	}
	
	@RequestMapping("/registerform")
	public String registerForm(@ModelAttribute("Product") Product product) {
		
		System.out.println(product.toString());
		try {
			productservice.register(product);
		} catch (Exception e) {
			e.printStackTrace();
			return "popup/productregisterfail";
		}
		return "popup/productregisterok";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model, @RequestParam(value="id", defaultValue="0") Integer id ){
		Product product = null;
		try {
			product= productservice.get(id);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		model.addAttribute("product",product);
		model.addAttribute("center","view/productmodify");
		model.addAttribute("Product", new Product());
		return "main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute("Product") Product product) {
		try {
			productservice.modify(product);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/productmodifyfail";
		}
		return "popup/productmodifyok";
	}
	
	@RequestMapping("/info")
	public String info(Model model,@RequestParam(value="limit",defaultValue="5") Integer limit) {
		List<OrderDetail> ods= null;
		List<OrderDetail> ods2= null;
		try {
			ods= odservice.bestProduct(new OrderDetail(limit));
			ods2= odservice.worstProduct(new OrderDetail(limit));
			int rank=1;
		for(OrderDetail od :ods) {
			int product_id=0;
			product_id=od.getProduct_id();
			String name=productservice.getName(product_id);
			od.setProduct_name(name);
			od.setRank(rank++);
		}
			int rank2=1;
		for(OrderDetail od :ods2) {
			int product_id=0;
			product_id=od.getProduct_id();
			String name=productservice.getName(product_id);
			od.setProduct_name(name);
			od.setRank(rank2++);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("bestproduct",ods);
		model.addAttribute("worstproduct",ods2);
		model.addAttribute("center","chart/bestproduct");
		model.addAttribute("center2","chart/worstproduct");
		
		return "main";
	}
}
