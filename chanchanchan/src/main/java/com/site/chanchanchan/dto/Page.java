package com.site.chanchanchan.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Page {
	private int startPage;
	private int endPage;
	private boolean prev,next;

	private int total;
	private Criteria cri;
	
	public Page(Criteria cri, int total) {
		this.cri=cri;
		this.total=total;
	
		this.endPage =(int)(Math.ceil(cri.getPageNum()/10.0)) *10;
		this.startPage =this.endPage - 9;
		
		if(endPage == 0) {
			startPage=0;
		}
		
		int realEnd =(int)(Math.ceil((total)*1.0 /cri.getAmount()));
		if(realEnd < endPage) {
			this.endPage =realEnd;
		}
		this.prev = this.startPage >1;
		this.next = this.endPage < realEnd;
	}
}
