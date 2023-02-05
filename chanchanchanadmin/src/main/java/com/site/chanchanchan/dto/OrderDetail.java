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
	private int amount;
	private String product_name;
	private int rank;
	
	public OrderDetail(){
		this.limit=5;
	}
	
	public OrderDetail(int limit){
		this.limit=limit;
	}

}
