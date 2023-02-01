package com.site.chanchanchan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.Criteria;
import com.site.chanchanchan.dto.RegularOrderSchedule;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface RegularOrderScheduleMapper extends MyMapper<Integer, RegularOrderSchedule>{
	public List<RegularOrderSchedule> getListByPaging(Criteria cri) throws Exception;
	public int getTotal(Criteria cri) throws Exception;
	public void changeState(RegularOrderSchedule ros) throws Exception;
	public void remove(int roi) throws Exception;
}