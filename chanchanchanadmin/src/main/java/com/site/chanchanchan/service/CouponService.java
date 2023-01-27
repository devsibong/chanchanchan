package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Coupon;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.CouponMapper;

@Service
public class CouponService implements MyService<Integer, Coupon>{

	@Autowired
	CouponMapper couponmapper;
	
	@Override
	public void register(Coupon v) throws Exception {
		couponmapper.insert(v);
	}

	@Override
	public void modify(Coupon v) throws Exception {
		couponmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		couponmapper.delete(k);
	}

	@Override
	public Coupon get(Integer k) throws Exception {
		return couponmapper.select(k);
	}

	@Override
	public List<Coupon> getall() throws Exception {
		return couponmapper.selectall();
	}
	
	//SearchID
	public Coupon search(String id) throws Exception{
		return couponmapper.search(id);
	}
	
	//Paging
	public List<Coupon> getListByPaging(Criteria cri) throws Exception{
		return couponmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return couponmapper.getTotal(cri);
	}
	
	//changeStatus
	public void changeStatus(int apr) throws Exception{
		couponmapper.changeStatus(apr);
	}
	
}
