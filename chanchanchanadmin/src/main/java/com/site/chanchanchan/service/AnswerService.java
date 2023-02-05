package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Answer;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.AnswerMapper;

@Service
public class AnswerService implements MyService<Integer, Answer>{

	@Autowired
	AnswerMapper answermapper;
	
	@Override
	public void register(Answer v) throws Exception {
		answermapper.insert(v);
	}

	@Override
	public void modify(Answer v) throws Exception {
		answermapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		answermapper.delete(k);
	}

	@Override
	public Answer get(Integer k) throws Exception {
		return answermapper.select(k);
	}

	@Override
	public List<Answer> getall() throws Exception {
		return answermapper.selectall();
	}
	
	//Paging
	public List<Answer> getListByPaging(Criteria cri) throws Exception{
		return answermapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return answermapper.getTotal(cri);
	}
	
}
