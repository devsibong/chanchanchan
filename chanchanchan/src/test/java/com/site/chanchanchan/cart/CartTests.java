package com.site.chanchanchan.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.service.CartService;

@SpringBootTest
public class CartTests {
	
	@Autowired
	CartService service;
	
	@Test
	void cartInsetTest() {
		
		Cart cart = new Cart(0, 100, 100, 100);
		try {
			service.register(cart);
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}
}
