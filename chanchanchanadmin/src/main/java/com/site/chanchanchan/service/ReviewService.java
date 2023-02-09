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
	ReviewMapper reviewmapper;
	
	@Override
	public void register(Review v) throws Exception {
		reviewmapper.insert(v);
	}

	@Override
	public void modify(Review v) throws Exception {
		reviewmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		reviewmapper.delete(k);
	}

	@Override
	public Review get(Integer k) throws Exception {
		return reviewmapper.select(k);
	}

	@Override
	public List<Review> getall() throws Exception {
		return reviewmapper.selectall();
	}
	
	//Paging
	public List<Review> getListByPaging(Criteria cri) throws Exception{
		return reviewmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return reviewmapper.getTotal(cri);
	}

}
