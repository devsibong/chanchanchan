package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.MemberMapper;

@Service
public class MemberService implements MyService<String, Member>{

	@Autowired
	MemberMapper mapper;
	
	@Override
	public void register(Member v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Member v) throws Exception {
		mapper.update(v);		
	}

	@Override
	public Member get(String k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Member> get() throws Exception {
		return mapper.selectall();
	}

	public Member getByIndex(String k) throws Exception {
		return mapper.selectByIndex(k);
	}
	
}
