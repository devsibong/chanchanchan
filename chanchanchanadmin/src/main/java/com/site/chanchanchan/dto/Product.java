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
	public Product(int category_id, String product_name, String product_description, String product_thumbnail_imgpath,
			String product_detail_imgpath, int product_price, String product_essential, int product_discount) {
		super();
		this.product_id=0;
		this.category_id = category_id;
		this.product_name = product_name;
		this.product_description = product_description;
		this.product_thumbnail_imgpath = product_thumbnail_imgpath;
		this.product_detail_imgpath = product_detail_imgpath;
		this.product_price = product_price;
		this.product_essential = product_essential;
		this.product_discount = product_discount;
	}
	
	
	
}
