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
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	PostService postservice;
	
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
		
		List<Post> posts=null;
		
		try {
			posts= postservice.getListByPaging(cri);
			total = postservice.getTotal(cri);
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		session.setAttribute("cri_value",cri);
		
		model.addAttribute("post",posts);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"post");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/post/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			postservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	//게시글 뷰페이지
	@GetMapping("/view")
	public String get(Model model, HttpSession session, 
			@RequestParam(value="id", defaultValue="0") Integer id){
		Post post = null;
		try {
			post= postservice.get(id);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		model.addAttribute("postView",post);
		model.addAttribute("center","view/postview");
		return "main";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {

		model.addAttribute("center","view/postregister");
		model.addAttribute("post_container", new Post());
		return "main";
	}
	
	@RequestMapping("/registerform")
	public String registerForm(@ModelAttribute("post_container") Post post) {
		try {
			postservice.register(post);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/postregisterfail";
		}
		return "popup/postregisterok";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model, @RequestParam(value="id", defaultValue="0") Integer post_id ){
		Post post = null;
		try {
			post= postservice.get(post_id);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		model.addAttribute("post",post);
		model.addAttribute("center","view/postmodify");
		model.addAttribute("post_container", new Post());
		return "main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute("post_container") Post post) {
		try {
			postservice.modify(post);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/postmodifyfail";
		}
		return "popup/postmodifyok";
	}
}
