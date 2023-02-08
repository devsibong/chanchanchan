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
	private int admin_index;
	private String answer_content;
	private String register_date;
	
	public Answer(int post_id, int admin_index, String answer_content) {
		super();
		this.post_id = post_id;
		this.admin_index = admin_index;
		this.answer_content = answer_content;
	}
}
