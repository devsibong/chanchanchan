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
public class KakaoPayReady {
	private String tid;
	private String next_redirect_pc_url;
	private String partner_order_id;
}
