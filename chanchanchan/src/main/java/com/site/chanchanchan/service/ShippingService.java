package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Shipping;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.ShippingMapper;

@Service
public class ShippingService implements MyService<String, Shipping> {

	@Autowired
	ShippingMapper mapper;

	@Override
	public void register(Shipping v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(Shipping v) throws Exception {
		mapper.update(v);
	}

	@Override
	public Shipping get(String k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<Shipping> get() throws Exception {
		return mapper.selectall();
	}



}
