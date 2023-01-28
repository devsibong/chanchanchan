package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.CategoryMapper;

@Service
public class CategoryService implements MyService<Integer, Category> {

	@Autowired
	CategoryMapper mapper;
	
	@Override
	public void register(Category v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Category v) throws Exception {
		mapper.update(v);
	}

	@Override
	public Category get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Category> get() throws Exception {
		return mapper.selectall();
	}

}
