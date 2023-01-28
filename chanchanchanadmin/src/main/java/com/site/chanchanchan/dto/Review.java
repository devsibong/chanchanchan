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
public class Review {
	private int review_id;
	private int member_index;
	private int product_id;
	private int review_rank;
	private String review_content;
	private Date review_date;
}
