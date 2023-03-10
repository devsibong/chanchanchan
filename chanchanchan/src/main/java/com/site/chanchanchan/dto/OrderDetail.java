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

	
	private String product_name;
	private int order_totalpayment;
	private String payment_method;
	private Date order_date;
	private String order_state;
	private String receiver;
	private int member_index;
	private String product_thumbnail_imgpath;
	private String product_detail_imgpath;
	private String member_id;
	private int shippingfee;
	private String receiver_tel;
	private String delivery_info;
	
	private Product product;
	private OrderList orderlist;
	

	private int orderdetail_count;

}
