package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Review;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface ReviewMapper extends MyMapper<Integer, Review>{
	public List<Review> listByIndex(Integer product_id) throws Exception;
	public List<Review> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
}
