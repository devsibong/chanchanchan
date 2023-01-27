package com.site.chanchanchan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminPost {
	private int adminpost_id;
	private int admin_index;
	private String post_type;
	private String post_subject;
	private String post_content;
	private String post_attach1;
	private String post_attach2;
	private String register_date;
	
	public AdminPost(int admin_index, String post_type, String post_subject, String post_content, String post_attach1,
			String post_attach2) {
		super();
		this.admin_index = admin_index;
		this.post_type = post_type;
		this.post_subject = post_subject;
		this.post_content = post_content;
		this.post_attach1 = post_attach1;
		this.post_attach2 = post_attach2;
	}
	
	
}
