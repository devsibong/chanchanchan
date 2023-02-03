package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.OrderDetailMapper;

@Service
public class OrderDetailService implements MyService<Integer, OrderDetail>{

	@Autowired
	OrderDetailMapper olmapper;
	
	@Override
	public void register(OrderDetail v) throws Exception {
		olmapper.insert(v);
	}

	@Override
	public void modify(OrderDetail v) throws Exception {
		olmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		olmapper.delete(k);
	}

	@Override
	public OrderDetail get(Integer k) throws Exception {
		return olmapper.select(k);
	}

	@Override
	public List<OrderDetail> getall() throws Exception {
		return olmapper.selectall();
	}
	
	//Paging
	public List<OrderDetail> getListByPaging(Criteria cri) throws Exception{
		return olmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return olmapper.getTotal(cri);
	}

	
	//월별매출액
	public List<OrderDetail> bestProduct(OrderDetail od) throws Exception{
		return olmapper.bestProduct(od);
	}
}
