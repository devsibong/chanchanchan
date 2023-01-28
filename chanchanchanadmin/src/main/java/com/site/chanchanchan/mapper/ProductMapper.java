package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Product;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Integer, Product>{
	public List<Product> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public int changeSale(int id, int salerate) throws Exception;
}