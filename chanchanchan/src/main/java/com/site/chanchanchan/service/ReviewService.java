package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.ReviewMapper;

@Service
public class ReviewService implements MyService<Integer, Review>{
	
	@Autowired
	ReviewMapper mapper;

	@Override
	public void register(Review v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Review v) throws Exception {
		mapper.update(v);
	}

	@Override
	public Review get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Review> get() throws Exception {
		return mapper.selectall();
	}
	
	public List<Review> listByIndex(Integer product_id) throws Exception{
		return mapper.listByIndex(product_id);
	}
	
	//Paging
	public List<Review> getListByPaging(Criteria cri) throws Exception{
		return mapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return mapper.getTotal(cri);
	}

}
