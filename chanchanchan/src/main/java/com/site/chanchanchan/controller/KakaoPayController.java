package com.site.chanchanchan.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.dto.KakaoPayApprove;
import com.site.chanchanchan.dto.KakaoPayReady;
import com.site.chanchanchan.service.KakaoPayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    
    //결제요청
    @PostMapping("/ready")
    @ResponseBody
    public KakaoPayReady readyToKakaoPay(@RequestParam Map<String, Object> param) {

    	System.out.println("readyToKakaoPay.controller");
        return kakaoPayService.kakaoPayReady();
        
    }
     
    @GetMapping("/success")
    public String afterPayRequest(@RequestParam("pg_token") String pgToken) {

    	KakaoPayApprove kakaoPayApprove = kakaoPayService.approveResponse(pgToken);
    	
    	System.out.println("afterPayRequest.controller");
    	ResponseEntity kakaoPaySuccess =  new ResponseEntity<>(kakaoPayApprove, HttpStatus.OK);
    	return "order/kakaopaysuccess";
    }

}
