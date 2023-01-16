package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Member;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface MemberMapper extends MyMapper<Integer, Member>{
	public Member search(String id) throws Exception;
}
