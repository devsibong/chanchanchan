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
public class shipping {
	private int shipping_id;
	private int member_index;
	private String shipping_title;
	private String shipping_address;
	private String shipping_address_detail;
	private String shipping_zipcode;
	private char shipping_recent;
}
