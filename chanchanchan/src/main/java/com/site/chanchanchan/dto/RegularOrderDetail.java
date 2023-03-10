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
public class RegularOrderDetail {
	private int regular_orderdetail_id;
	private int product_id;
	private int order_id;

	
	
	private String product_name;
	private int order_totalpayment;
	private String payment_method;
	private Date order_date;
	private String order_state;
	private String receiver;
	private int member_index;
	private Product product;
	private OrderList orderlist;
	private String product_thumbnail_imgpath;
	private String product_detail_imgpath;

	private int regular_price;	
	private RegularOrderSchedule regularOrderSchedule;
}
