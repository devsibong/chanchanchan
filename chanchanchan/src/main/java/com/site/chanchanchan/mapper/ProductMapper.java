package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface ProductMapper extends MyMapper<Integer, Product>{
	public List<Product> getprod(Integer k) throws Exception;
}
