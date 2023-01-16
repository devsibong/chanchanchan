package com.site.chanchanchan.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.service.AdminService;

@SpringBootTest
class DeleteTests {

	@Autowired
	AdminService service;
	
	@Test
	void contextLoads() {
		try {
			service.remove(104);
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
		
	}

}







