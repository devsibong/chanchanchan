package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.OrderListMapper;

@Service
public class OrderDetailService implements MyService<Integer, OrderList>{

	@Autowired
	OrderListMapper olmapper;
	
	@Override
	public void register(OrderList v) throws Exception {
		olmapper.insert(v);
	}

	@Override
	public void modify(OrderList v) throws Exception {
		olmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		olmapper.delete(k);
	}

	@Override
	public OrderList get(Integer k) throws Exception {
		return olmapper.select(k);
	}

	@Override
	public List<OrderList> getall() throws Exception {
		return olmapper.selectall();
	}
	
	//Paging
	public List<OrderList> getListByPaging(Criteria cri) throws Exception{
		return olmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return olmapper.getTotal(cri);
	}
	
	//배송상태변경
	public void changeState(OrderList ol) throws Exception{
		 olmapper.changeState(ol);
	}
	
	//월별매출액
	public int monthsales(Integer month) throws Exception{
		return olmapper.monthsales(month);
	}
}
