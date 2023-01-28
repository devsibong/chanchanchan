package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.PostMapper;

@Service
public class PostService implements MyService<Integer, Post>{
	
	@Autowired
	PostMapper mapper;

	@Override
	public void register(Post v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Post v) throws Exception {
		mapper.update(v);
	}

	@Override
	public Post get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Post> get() throws Exception {
		return mapper.selectall();
	}
	
	//문의글 리스트
	public List<Post> list() throws Exception {
		return mapper.list();
	}
	
	//Paging
	public List<Post> getListByPaging(Criteria cri) throws Exception{
		return mapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return mapper.getTotal(cri);
	}
	

}
