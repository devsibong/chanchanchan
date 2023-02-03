package com.site.chanchanchan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
	private int orderdetail_id;
	private int product_id;
	private int order_id;
	private int orderdetail_count;
	private int orderdetail_price;
	
	private int limit;
	private int sum;
	private String product_name;
	
	public OrderDetail(){
		this.limit=10;
	}
	
	public OrderDetail(int limit){
		this.limit=limit;
	}

}
