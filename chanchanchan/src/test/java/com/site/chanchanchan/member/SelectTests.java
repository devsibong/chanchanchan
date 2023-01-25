package com.site.chanchanchan.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.service.MemberService;

@SpringBootTest
class SelectTests {

	@Autowired
	MemberService service;
	
	@Test
	void contextLoads() {
		Member mem = null;
		try {
			mem = service.get("id01");
			System.out.println(mem);
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
					
	}

}
