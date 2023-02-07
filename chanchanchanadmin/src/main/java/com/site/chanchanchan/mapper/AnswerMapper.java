package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.Answer;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface AnswerMapper extends MyMapper<Integer, Answer>{
	public List<Answer> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public int answer_existence(Integer id) throws Exception;
	public Answer select_post_id(Integer post_id) throws Exception;
}