package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.RegularOrderSchedule;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.RegularOrderScheduleMapper;

@Service
public class RegularOrderScheduleService implements MyService<Integer, RegularOrderSchedule>{

	@Autowired
	RegularOrderScheduleMapper rosmapper;
	
	@Override
	public void register(RegularOrderSchedule v) throws Exception {
		rosmapper.insert(v);
	}

	@Override
	public void modify(RegularOrderSchedule v) throws Exception {
		rosmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		rosmapper.delete(k);
	}

	@Override
	public RegularOrderSchedule get(Integer k) throws Exception {
		return rosmapper.select(k);
	}

	@Override
	public List<RegularOrderSchedule> getall() throws Exception {
		return rosmapper.selectall();
	}
	
	//Paging
	public List<RegularOrderSchedule> getListByPaging(Criteria cri) throws Exception{
		return rosmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return rosmapper.getTotal(cri);
	}
	
	//배송상태변경
	public void changeState(RegularOrderSchedule ros) throws Exception{
		 rosmapper.changeState(ros);
	}
	
	//delete by regulor_order_id
	public void delete(int roi) throws Exception{
		rosmapper.remove(roi);
	}
}
