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
public class RegularOrderSchedule {
	private int regular_orderschedule_id;
	private int regular_orderdetail_id;
	private String regular_shippingdate;
	private String regular_shipping_state;
}
