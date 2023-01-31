package com.site.chanchanchan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.frame.MyService;
import com.site.chanchanchan.mapper.MemberMapper;

@Service
public class MemberService implements MyService<Integer, Member>{

	@Autowired
	MemberMapper memmapper;
	
	@Override
	public void register(Member v) throws Exception {
		memmapper.insert(v);
	}

	@Override
	public void modify(Member v) throws Exception {
		memmapper.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		memmapper.delete(k);
	}

	@Override
	public Member get(Integer k) throws Exception {
		return memmapper.select(k);
	}

	@Override
	public List<Member> getall() throws Exception {
		return memmapper.selectall();
	}
	
	//Search
	public List<Member> search(String member_rank) throws Exception{
		return memmapper.search(member_rank);
	}
	
	//Paging
	public List<Member> getListByPaging(Criteria cri) throws Exception{
		return memmapper.getListByPaging(cri);
	};
	
	//COUNT
	public int getTotal(Criteria cri) throws Exception{
		return memmapper.getTotal(cri);
	}
	
	//changeStatus
	public void changeStatus(int apr) throws Exception{
		memmapper.changeStatus(apr);
	}
	
	//getSumById
	public Member getSumById(int id) throws Exception{
		return memmapper.getSumById(id);
	}
	
	//changeRank
	public void changeRank(Member mem) throws Exception{
		memmapper.changeRank(mem);
	}
}
