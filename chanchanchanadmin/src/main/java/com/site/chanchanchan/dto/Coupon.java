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
public class Coupon {
	private int coupon_id;
	private int member_index;
	private int coupon_minprice;
	private int coupon_discountper;
	private Date coupon_expiredate;
	private String coupon_status;
}
