package com.site.chanchanchan.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.KakaoAPI;
import com.site.chanchanchan.service.MemberService;
import com.site.chanchanchan.service.NaverAPI;
import com.site.chanchanchan.service.SendMailService;


@Controller
public class MemberController {

	String dir = "member/";
	
	@Autowired
	MemberService memservice;
	
	@Autowired
	SendMailService mailservice;
	
	@Autowired
	KakaoAPI kakao;
	
	@Autowired
	NaverAPI naver;
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("center", dir + "login");
		return "index";
	}

	@RequestMapping("/loginimpl")
	public String loginimpl(HttpSession session, String member_id, String member_pw) {
		String str = null;
		try {
			Member mem = memservice.get(member_id);
			if(!member_pw.equals(mem.getMember_pw())) {
				str = "redirect:loginfail";
			}else {
				session.setAttribute("loginmem", mem);
				str = "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/loginfail")
	public String loginfail(Model model) {
		model.addAttribute("center", dir + "loginfail");
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("center", dir + "register");
		return "index";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model model, Member mem) {		
		try {
			memservice.register(mem);
			model.addAttribute("registermem", mem);
			model.addAttribute("center", dir + "registerok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/findid")
	public String findid(Model model) {
		model.addAttribute("center", dir + "findid");
		return "index";
	}
	
	@RequestMapping("/findidimpl")
	public String findidimpl(Model model, String member_email) {
		try {
			Member mem = memservice.get(member_email);
			model.addAttribute("findmem", mem);
			model.addAttribute("center", dir + "findidok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/findpwd")
	public String findPwd(Model model) {
		model.addAttribute("center", dir + "findpwd");
		return "index";
	}
	
	@RequestMapping("/sendmail")
	public String sendmail(Model model, Member mem, String member_id, String member_email) { 
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String newPwd =""; 

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            newPwd += charSet[idx];
        }
		try {
			mem = memservice.get(member_id);
			mem.setMember_pw(newPwd);
			memservice.modify(mem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailservice.sendPwdMail(mem, member_email);
		model.addAttribute("center", dir + "login");
		return "redirect:login";
	}
	
	@RequestMapping("/kakaologin")
	public String kakaologin(Model model, String code, HttpSession session) {
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, String> userInfo = kakao.getUserInfo(access_Token);
		try {
			Member memexist = memservice.get(userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")));

			if(memexist == null) {
				Member mem = new Member(0, userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("name"), userInfo.get("email"), "010-1111-2222", 'C',  'Y', null, null);
				memservice.register(mem);
				session.setAttribute("loginmem", mem);
			}else {
				session.setAttribute("loginmem", memexist);
			}
			session.setAttribute("access_Token", access_Token);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/naverlogin")
	public String naverlogin(Model model, String code, HttpSession session) {
		String access_Token = naver.getAccessToken(code);
		HashMap<String, String> userInfo = naver.getUserInfo(access_Token);
		try {
			Member memexist = memservice.get(userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")));

			if(memexist == null) {
				Member mem = new Member(0, userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("email").substring(0,userInfo.get("email").indexOf("@")), userInfo.get("name"), userInfo.get("email"), "010-1111-2222", 'C',  'Y', null, null);
				memservice.register(mem);
				session.setAttribute("loginmem", mem);
			}else {
				session.setAttribute("loginmem", memexist);
			}
			session.setAttribute("access_Token", access_Token);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
