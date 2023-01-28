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
public class Member {
	private int sum;

	private int member_index;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_email;
	private String member_tel;
	private String member_rank;
	private char member_status;
	private Date member_joindate;
	private Date member_dropdate;
	
	
	public Member(int member_index, String member_rank) {
		super();
		this.member_index = member_index;
		this.member_rank = member_rank;
	}
}
