package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.RegularOrder;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface RegularOrderMapper extends MyMapper<Integer, RegularOrder>{
	public List<RegularOrder> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public void changeState(RegularOrder ol) throws Exception;

}