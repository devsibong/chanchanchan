package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.OrderList;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface OrderListMapper extends MyMapper<Integer, OrderList>{
	
}
