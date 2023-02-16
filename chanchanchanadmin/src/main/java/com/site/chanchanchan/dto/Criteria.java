package com.site.chanchanchan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;

	private String option;
	private String searchVal;
	private boolean isSearchOk=true;
	
	private int pageStartNum;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
		
		this.pageStartNum=(pageNum-1)*amount+1;
	}
	
	public Criteria(int pageNum, int amount, String option, String searchVal,boolean isSearchOk) {
		this.pageNum=pageNum;
		this.amount=amount;
		this.option=option;
		this.searchVal=searchVal;
		this.isSearchOk=isSearchOk;
		
		this.pageStartNum=(pageNum-1)*amount;
	}
}
