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
public class Cart {
	private int cart_id;
	private int member_index;
	private int product_id;
	private int product_count;
	
	private Product product;
	private int count;
}
