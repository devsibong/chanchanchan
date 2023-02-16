package com.site.chanchanchan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.site.chanchanchan.dto.RegularOrderSchedule;
import com.site.chanchanchan.frame.MyMapper;

@Mapper
@Repository
public interface RegularOrderScheduleMapper extends MyMapper<Integer, RegularOrderSchedule>{
	public void schdelete(Integer k) throws Exception;
}
