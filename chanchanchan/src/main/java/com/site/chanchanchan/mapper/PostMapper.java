package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Post;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface PostMapper extends MyMapper<Integer, Post>{
	public List<Post> list();
}
