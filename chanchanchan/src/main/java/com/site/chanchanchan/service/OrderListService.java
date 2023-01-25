package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.OrderListMapper;

@Service
public class OrderListService implements MyService<Integer, OrderList>{
	
	@Autowired
	OrderListMapper mapper;

	@Override
	public void register(OrderList v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(OrderList v) throws Exception {
		mapper.update(v);
	}

	@Override
	public OrderList get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<OrderList> get() throws Exception {
		return mapper.selectall();
	}
	
}
