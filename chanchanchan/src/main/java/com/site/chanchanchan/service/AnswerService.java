package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Answer;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.AnswerMapper;

@Service
public class AnswerService implements MyService<Integer, Answer>{

	@Autowired
	AnswerMapper mapper;
	
	@Override
	public void register(Answer v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Answer v) throws Exception {
		mapper.update(v);		
	}

	@Override
	public Answer get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Answer> get() throws Exception {
		return mapper.selectall();
	}
}
