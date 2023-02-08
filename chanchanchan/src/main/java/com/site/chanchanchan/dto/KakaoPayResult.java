package com.site.chanchanchan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class KakaoPayResult {
	private int total;
	private int tax_free;
	private int vat;
	private int point;
	private int discount;
}
