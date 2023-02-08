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

import com.site.chanchanchan.dto.Answer;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.service.AnswerService;
import com.site.chanchanchan.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	PostService postservice;
	
	@Autowired
	AnswerService answerservice;
	
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
	@RequestMapping("/answer/delete")
	public String delete(int del) {
		try {
			answerservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String postdelete(int del) {
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
		Answer answer = null;
		int existence=0;
		
		try {
			post= postservice.get(id);
			existence=postservice.answer_existence(id);
			if(existence>0) {
				post.setAnswer_existence("Y");
				answer= answerservice.select_post_id(id);
				model.addAttribute("answerView",answer);
				model.addAttribute("center2","view/answerview");
			}else {
			post.setAnswer_existence("N");
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		model.addAttribute("postView",post);
		model.addAttribute("center","view/postview");
		return "main";
	}
	
	@RequestMapping("/register")
	public String register(Model model, @RequestParam(value="post_id", defaultValue="0") Integer post_id) {

		model.addAttribute("center","view/answerregister");
		model.addAttribute("post_id",post_id);
		model.addAttribute("answer_container", new Answer());
		return "main";
	}
	
	@RequestMapping("/registerform")
	public String registerForm(@ModelAttribute("answer_container") Answer answer) {
		try {
			answerservice.register(answer);
		} catch (Exception e) {
			// e.printStackTrace();
			return "popup/answerregisterfail";
		}
		return "popup/answerregisterok";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model, @RequestParam(value="id", defaultValue="0") Integer id ){
		Answer answer = null;
		try {
			answer= answerservice.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("answer",answer);
		model.addAttribute("center","view/answermodify");
		model.addAttribute("answer_container", new Answer());
		return "main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute("answer_container") Answer answer) {
		try {
			answerservice.modify(answer);
		} catch (Exception e) {
			 e.printStackTrace();
			return "popup/answermodifyfail";
		}
		return "popup/answermodifyok";
	}
}
