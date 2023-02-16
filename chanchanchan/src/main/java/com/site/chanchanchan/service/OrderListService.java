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
	
	public List<OrderList> list(Integer k) throws Exception{
		return mapper.list(k);
	}
	
	public List<OrderList> reviewlist(Integer k) throws Exception{
		return mapper.list(k);
	}
	//리뷰 상품아이디 가져오기
		public OrderList rvprid(Integer order_id) throws Exception {
			return mapper.rvprid(order_id);
		}
	//일반 주문내역	
	public List<OrderList> ordlist(Integer k) throws Exception{
		return mapper.list(k);
	}
	//정기 주문내역	
	public List<OrderList> regordlist(Integer k) throws Exception{
		return mapper.list(k);
	}
	public int precount(String k) throws Exception {
		return mapper.precount(k);
	}
	public int ingcount(String k) throws Exception {
		return mapper.ingcount(k);
	}
	public int comcount(String k) throws Exception {
		return mapper.comcount(k);
	}
}
