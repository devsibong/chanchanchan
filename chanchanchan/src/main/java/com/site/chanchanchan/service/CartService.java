package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.CartMapper;

@Service
public class CartService implements MyService<String, Cart>{
	
	@Autowired
	CartMapper mapper;

	@Override
	public void register(Cart v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Cart v) throws Exception {
		mapper.update(v);
	}

	@Override
	public Cart get(String k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Cart> get() throws Exception {
		return mapper.selectall();
	}

}
