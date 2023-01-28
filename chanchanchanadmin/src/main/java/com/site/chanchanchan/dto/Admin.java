package com.site.chanchanchan.dto;

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
public class Admin {
	private int admin_index;
	private String admin_id;
	private String admin_pwd;
	private String admin_name;
	private String admin_class;
	private char admin_status;
	
}
