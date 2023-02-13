package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface OrderListMapper extends MyMapper<Integer, OrderList>{
	public List<OrderList> list(Integer k) throws Exception;
	public List<OrderList> reviewlist(Integer k) throws Exception;
	public OrderList rvprid(Integer order_id) throws Exception;
	public List<OrderList> ordlist(Integer k) throws Exception;
	public List<OrderList> regordlist(Integer k) throws Exception;
}
