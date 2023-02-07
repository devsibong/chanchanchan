package com.site.chanchanchan.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.site.chanchanchan.dto.KakaoPayApprove;
import com.site.chanchanchan.dto.KakaoPayReady;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private final String cid = "TC0ONETIME";
    private final String admin_Key = "d56a06b0d1b0de711049f6f287e33dbb";
    private KakaoPayReady kakaoPayReady;
    
    //결제요청
    public KakaoPayReady kakaoPayReady() {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("item_name", "상품명");
        parameters.add("quantity", "2");
        parameters.add("total_amount", "3000");
        parameters.add("vat_amount", "500");
        parameters.add("tax_free_amount", "500");
        parameters.add("approval_url", "http://localhost/payment/success");
        parameters.add("cancel_url", "http://localhost/payment/cancel");
        parameters.add("fail_url", "http://localhost/payment/fail");
        
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        RestTemplate restTemplate = new RestTemplate();

        kakaoPayReady = restTemplate.postForObject(
		        		"https://kapi.kakao.com/v1/payment/ready",
		                requestEntity,
		                KakaoPayReady.class);
        
        System.out.println("kakaoPayReady.service");
        return kakaoPayReady;
    }
    
    //결제승인
    public KakaoPayApprove approveResponse(String pgToken) {
    
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoPayReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        RestTemplate restTemplate = new RestTemplate();
        
        KakaoPayApprove kakaoPayApprove = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoPayApprove.class);
         
       
        System.out.println("approveResponse.service");
        return kakaoPayApprove;
    }
    
    //Header
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}