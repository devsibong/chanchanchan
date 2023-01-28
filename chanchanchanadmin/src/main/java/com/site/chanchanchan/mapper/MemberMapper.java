package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface MemberMapper extends MyMapper<Integer, Member>{
	public List<Member> search(String member_rank) throws Exception;
	public List<Member> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public void changeStatus(int apr) throws Exception;
	public Member getSumById(int id) throws Exception;
	public void changeRank(Member mem) throws Exception;
}