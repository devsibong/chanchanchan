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
public class Product {
	private int product_id;
	private int category_id;
	private String product_name;
	private String product_description;
	private String product_thumbnail_imgpath;
	private String product_detail_imgpath;
	private int product_price;
	private String product_essential;
	private int product_discount;
	
}
