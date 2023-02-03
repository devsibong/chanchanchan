package com.site.chanchanchan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
	private int orderdetail_id;
	private int product_id;
	private int order_id;
	private int orderdetail_count;
	private int orderdetail_price;

}
