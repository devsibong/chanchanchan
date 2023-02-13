package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Answer;
import com.site.chanchanchan.frame.MyMapper;

@Repository
@Mapper
public interface AnswerMapper extends MyMapper<Integer, Answer>{
}
