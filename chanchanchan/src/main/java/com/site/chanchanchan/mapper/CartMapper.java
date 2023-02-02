package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface CartMapper extends MyMapper<String, Cart>{
	public List<Cart> selectByMember(String k) throws Exception;
	public int updateCount(Cart v) throws Exception;
}
