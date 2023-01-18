package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Cart;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface CartMapper extends MyMapper<String, Cart>{

}
