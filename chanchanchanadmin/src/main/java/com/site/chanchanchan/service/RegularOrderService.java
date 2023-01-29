package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.RegularOrder;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.RegularOrderMapper;

@Service
public class RegularOrderService implements MyService<Integer, RegularOrder>{

	@Autowired
	RegularOrderMapper regularordermapper;
	
	@Override
	public void register(RegularOrder v) throws Exception {
		regularordermapper.insert(v);
	}

	@Override
	public void modify(RegularOrder v) throws Exception {
		regularordermapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		regularordermapper.delete(k);
	}

	@Override
	public RegularOrder get(Integer k) throws Exception {
		return regularordermapper.select(k);
	}

	@Override
	public List<RegularOrder> getall() throws Exception {
		return regularordermapper.selectall();
	}
	
	//Paging
	public List<RegularOrder> getListByPaging(Criteria cri) throws Exception{
		return regularordermapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return regularordermapper.getTotal(cri);
	}
	
	//배송상태변경
	public void changeState(RegularOrder ol) throws Exception{
		 regularordermapper.changeState(ol);
	}
}
