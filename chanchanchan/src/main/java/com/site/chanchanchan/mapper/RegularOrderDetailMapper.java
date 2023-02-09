package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.RegularOrderDetail;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface RegularOrderDetailMapper extends MyMapper<Integer, RegularOrderDetail>{
	public List<RegularOrderDetail> list(Integer k) throws Exception;
}
