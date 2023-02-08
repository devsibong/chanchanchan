package com.site.chanchanchan.mypage;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.service.OrderDetailService;

@SpringBootTest
class SelectTests {

	@Autowired
	OrderDetailService odservice;
	
	@Test
	void contextLoads() {
		List<OrderDetail> list;
		try {
			list = odservice.list(100);
			System.out.println(list);
			System.out.println("OK");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
					
	}

}
