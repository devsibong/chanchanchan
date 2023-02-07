package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface ReviewMapper extends MyMapper<Integer, Review>{
	
}