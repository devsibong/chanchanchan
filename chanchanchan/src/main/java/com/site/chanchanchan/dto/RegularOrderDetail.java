package com.site.chanchanchan.dto;

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
	private int regular_price;	
	private RegularOrderSchedule regularOrderSchedule;
}
