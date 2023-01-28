package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Coupon;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface CouponMapper extends MyMapper<Integer, Coupon>{
	public Coupon search(String id) throws Exception;
	public List<Coupon> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public void changeStatus(int apr) throws Exception;

}