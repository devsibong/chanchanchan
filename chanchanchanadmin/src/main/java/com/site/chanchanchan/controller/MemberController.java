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
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.dto.Page;
import com.site.chanchanchan.service.MemberService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
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
		List<Member> mems=null;
		
		try {
			mems= memservice.getListByPaging(cri);
			total = memservice.getTotal(cri);
			
		} catch (Exception e) {
		}
		
		Page page = new Page(cri,total);
		
		model.addAttribute("member",mems);
		model.addAttribute("pageMaker", page);
		session.removeAttribute("option");
		session.removeAttribute("searchVal");
		
		model.addAttribute("center",dir+"member");
		
		return "main";
	}
	
	// 
	@ResponseBody
	@RequestMapping("/searchlist")
	public String searchlist(String option, String searchVal,Model model, HttpSession session) {
		session.setAttribute("option",option);
		session.setAttribute("searchVal",searchVal);
		
		return "rediret:/member/list";
	}
	
	//삭제버튼
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int del) {
		try {
			memservice.remove(del);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	//승인버튼
	@ResponseBody
	@RequestMapping("/restore")
	public String restore(int res) {
		try {
			memservice.changeStatus(res);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping("/membershiplevel")
	public String memberShipLevel() {
		return "popup/membershiplevel";
	}

	@ResponseBody
	@RequestMapping("/membershiplevelapply")
	public String membershipapply(String member_rank, int rank_minprice, int rank_maxprice) {
		List<Member> listmems =null;
		try {
			listmems = memservice.getall();
			
			int sum;
			int member_index;
			Member x;
			for(int i=0;i<listmems.size();i++) {
				Member mem=listmems.get(i);
				member_index=mem.getMember_index();
				x =memservice.getSumById(member_index);
				sum =x.getSum();
				System.out.println(sum);
				if(sum>=rank_minprice && sum<=rank_maxprice) {
					memservice.changeRank(new Member(member_index,member_rank));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "popup/changememberlevelfail";
		}
		return "popup/changememberlevelok";
	}
	
}
