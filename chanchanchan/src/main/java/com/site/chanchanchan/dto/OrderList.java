package com.site.chanchanchan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderList {
	private int order_id;
	private int member_index;
	private int product_totalprice;
	private int shippingfee;
	private int order_totalpayment;
	private String payment_method;
	private Date order_date;
	private String order_state;
	private String receiver;
	private String receiver_tel;
	private String delivery_info;
}
