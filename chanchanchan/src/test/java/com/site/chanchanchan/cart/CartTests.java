package com.site.chanchanchan.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.mapper.CartMapper;
import com.site.chanchanchan.service.CartService;

@SpringBootTest
public class CartTests {

	@Autowired
	CartService service;

	@Autowired
	CartMapper mapper;

	void cartSelectAllTest() throws Exception {
		try {
			service.get();
			System.out.println(service.get());
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}

	void cartSelectTest() throws Exception {
		try {
			service.get("100");
			System.out.println(service.get("100"));
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}
	
	@Test
	void cartSelectByMemberTest() throws Exception {
		try {
			service.getByMember("100");
			System.out.println(service.getByMember("100"));
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}
}
