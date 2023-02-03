package com.site.chanchanchan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OrderDetail {
	private int orderdetail_id;
	private int product_id;
	private int order_id;
	private int orderdetail_price;
	private int orderdetail_count;
}
