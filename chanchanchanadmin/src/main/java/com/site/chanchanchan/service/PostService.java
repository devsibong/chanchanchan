package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.AnswerMapper;
import com.site.chanchanchan.mapper.PostMapper;

@Service
public class PostService implements MyService<Integer, Post>{

	@Autowired
	PostMapper postmapper;
	
	@Autowired
	AnswerMapper answermapper;
	
	@Override
	public void register(Post v) throws Exception {
		postmapper.insert(v);
	}

	@Override
	public void modify(Post v) throws Exception {
		postmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		postmapper.delete(k);
	}

	@Override
	public Post get(Integer k) throws Exception {
		return postmapper.select(k);
	}

	@Override
	public List<Post> getall() throws Exception {
		return postmapper.selectall();
	}
	
	//Paging
	public List<Post> getListByPaging(Criteria cri) throws Exception{
		return postmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return postmapper.getTotal(cri);
	}
	
	public int answer_existence(Integer id) throws Exception{
		return answermapper.answer_existence(id);
	}
}
