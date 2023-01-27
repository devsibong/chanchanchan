package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Admin;
import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.AdminMapper;

@Service
public class AdminService implements MyService<Integer, Admin>{

	@Autowired
	AdminMapper admmapper;
	
	@Override
	public void register(Admin v) throws Exception {
		admmapper.insert(v);
	}

	@Override
	public void modify(Admin v) throws Exception {
		admmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		admmapper.delete(k);
	}

	@Override
	public Admin get(Integer k) throws Exception {
		return admmapper.select(k);
	}

	@Override
	public List<Admin> getall() throws Exception {
		return admmapper.selectall();
	}
	
	//SearchID
	public Admin search(String id) throws Exception{
		return admmapper.search(id);
	}
	
	//Paging
	public List<Admin> getListByPaging(Criteria cri) throws Exception{
		return admmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return admmapper.getTotal(cri);
	}
	
	//changeStatus
	public void changeStatus(int apr) throws Exception{
		admmapper.changeStatus(apr);
	}
	
}
