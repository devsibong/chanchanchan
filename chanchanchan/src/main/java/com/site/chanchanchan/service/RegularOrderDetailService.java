package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.RegularOrderDetail;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.RegularOrderDetailMapper;

@Service
public class RegularOrderDetailService implements MyService<Integer, RegularOrderDetail>{
	
	@Autowired
	RegularOrderDetailMapper mapper;

	@Override
	public void register(RegularOrderDetail v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(RegularOrderDetail v) throws Exception {
		mapper.update(v);
	}

	@Override
	public RegularOrderDetail get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<RegularOrderDetail> get() throws Exception {
		return mapper.selectall();
	}
	
	//정기주문내역 리스트
	public List<RegularOrderDetail> list(Integer k) throws Exception {
		return mapper.list(k);
	}
	
	//정기주문내역 상세
	public RegularOrderDetail regorddetail(Integer regular_orderdetail_id) throws Exception {
		return mapper.regorddetail(regular_orderdetail_id);
	}

}
