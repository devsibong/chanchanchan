package com.site.chanchanchan.dto;

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
public class Coupon {
	private int coupon_id;
	private int member_index;
	private int coupon_minprice;
	private int coupon_discountper;
	private String coupon_expiredate;
	private String coupon_status;
	
	public Coupon(int member_index, int coupon_minprice, int coupon_discountper, String coupon_expiredate) {
		super();
		this.member_index = member_index;
		this.coupon_minprice = coupon_minprice;
		this.coupon_discountper = coupon_discountper;
		this.coupon_expiredate = coupon_expiredate;
	}
}
