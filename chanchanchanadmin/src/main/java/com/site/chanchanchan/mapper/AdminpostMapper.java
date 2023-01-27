package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.AdminPost;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface AdminpostMapper extends MyMapper<Integer, AdminPost>{
	public List<AdminPost> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
}