package com.site.chanchanchan.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.RegularOrderDetail;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface RegularOrderDetailMapper extends MyMapper<Integer, RegularOrderDetail>{
	//정기주문내역
	public List<RegularOrderDetail> list(Integer k) throws Exception;
	//정기주문내역 상세
		public RegularOrderDetail regorddetail(Integer regular_orderdetail_id) throws Exception;
}
