package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.OrderDetail;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface OrderDetailMapper extends MyMapper<Integer, OrderDetail>{
	public List<OrderDetail> list() throws Exception;
	public OrderDetail orddetail(Integer order_id) throws Exception;
}
