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
public class Post {
	private int post_id;
	private int member_index;
	private String post_subject;
	private String post_content;
	private String post_attach1;
	private String post_attach2;
	private Date register_date;
}
