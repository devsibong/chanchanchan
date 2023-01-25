package com.site.chanchanchan.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.MemberService;

@SpringBootTest
class InsertTests {

	@Autowired
	MemberService service;
	
	@Test
	void contextLoads() {
		Member mem = new Member(0, "id77", "pwd77", "칠칠이", "name77@naver.com", "010-7777-7777", 'A', 'Y', null, null);
		try {
			service.register(mem);
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
					
	}

}
