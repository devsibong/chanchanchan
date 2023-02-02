package com.site.chanchanchan.mypage;

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
		OrderDetail od = null;
		try {
			od = odservice.orddetail(102);
			System.out.println(od);
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
					
	}

}
