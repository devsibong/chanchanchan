package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Shipping;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface ShippingMapper extends MyMapper<String, Shipping>{
}
