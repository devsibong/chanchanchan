package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.OrderDetailMapper;

@Service
public class OrderDetailService implements MyService<Integer, OrderDetail>{
	
	@Autowired
	OrderDetailMapper mapper;

	@Override
	public void register(OrderDetail v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(OrderDetail v) throws Exception {
		mapper.update(v);
	}

	@Override
	public OrderDetail get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<OrderDetail> get() throws Exception {
		return mapper.selectall();
	}
	
	//주문내역 리스트
		public List<OrderDetail> list(Integer k) throws Exception {
			return mapper.list(k);
		}
		
	//주문내역 상세
	public OrderDetail orddetail(Integer order_id) throws Exception {
		return mapper.orddetail(order_id);
	}
	//리뷰 상품아이디
	public OrderDetail rvprid(Integer order_id) throws Exception {
		return mapper.rvprid(order_id);
	}
	
}
