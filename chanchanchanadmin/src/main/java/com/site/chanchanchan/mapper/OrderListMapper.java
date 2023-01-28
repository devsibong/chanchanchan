package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface OrderListMapper extends MyMapper<Integer, OrderList>{
	public List<OrderList> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public void changeState(OrderList ol) throws Exception;

}