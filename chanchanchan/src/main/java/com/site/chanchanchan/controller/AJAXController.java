package com.site.chanchanchan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.MemberService;

@RestController
public class AJAXController {
	
	@Autowired
	MemberService memservice;
	
	
	
	@RequestMapping("/checkid")
	public Object checkid(String member_id) {
		int result = 0;
		Member mem = null;
		try {
			mem = memservice.get(member_id);
			if(mem == null) {
				result = 1;
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/checkmail")
	public Object checkmail(String member_email) {
		int result = 0;
		Member mem = null;
		try {
			mem = memservice.get(member_email);
			if(mem == null) {
				result = 0;
			}else {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/checkpwd")
	public Object checkfindpwd(String member_id, String member_email) {
		int result = 0;
		Member mem = null;
		try {
			mem = memservice.get(member_id);
			if(mem == null || !member_email.equals(mem.getMember_email())) {
				result = 0;
			}else {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
}
