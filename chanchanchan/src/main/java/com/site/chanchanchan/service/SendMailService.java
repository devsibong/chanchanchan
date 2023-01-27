package com.site.chanchanchan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Member;

@Service
public class SendMailService {
	
	@Autowired
	MemberService memservice;
	
	@Autowired
	MailSender sender;
	
	public void sendPwdMail(Member mem, String member_email) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setTo(member_email);  	
	message.setSubject("찬찬찬 임시 비밀번호입니다.");
	message.setText("안녕하세요. 회원님의 임시 비밀번호는 "+mem.getMember_pw()+" 입니다.");
	sender.send(message);

	}
}
	
