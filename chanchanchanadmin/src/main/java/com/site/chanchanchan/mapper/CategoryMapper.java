package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface CategoryMapper extends MyMapper<Integer, Category>{
	public Category search(String title) throws Exception;
	public List<Category> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;

}