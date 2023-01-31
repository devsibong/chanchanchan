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
public class RegularOrderSchedule {
	private int regular_orderschedule_id;
	private int regular_orderdetail_id;
	private Date regular_shippingdate;
	private String regular_delivery_state;
}
