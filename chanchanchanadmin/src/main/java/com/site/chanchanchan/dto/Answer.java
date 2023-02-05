package com.site.chanchanchan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer {
	private int answer_id;
	private int post_id;
	private int admin_no;
	private String answer_content;
	private Date register_date;
	
}
