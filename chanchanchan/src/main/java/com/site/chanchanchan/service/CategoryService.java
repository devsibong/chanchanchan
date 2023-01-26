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
		mapper.delete(null);
	}

	@Override
	public void modify(Category v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
