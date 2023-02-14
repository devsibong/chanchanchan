package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.RegularOrderSchedule;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.RegularOrderScheduleMapper;

@Service
public class RegularOrderScheduleService implements MyService<Integer, RegularOrderSchedule>{
	
	@Autowired
	RegularOrderScheduleMapper mapper;

	@Override
	public void register(RegularOrderSchedule v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(RegularOrderSchedule v) throws Exception {
		mapper.update(v);
	}

	@Override
	public RegularOrderSchedule get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<RegularOrderSchedule> get() throws Exception {
		return mapper.selectall();
	}
	
	public void schdelete(Integer k) throws Exception {
		mapper.schdelete(k);
	}

}
