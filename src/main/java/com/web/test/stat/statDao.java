package com.web.test.stat;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.test.common.constants.CpuVo;

@Repository
public interface statDao {
	
	public List<CpuVo> select1MinStat() throws Exception;
	
	public int insert5MinStat() throws Exception;
	
	
	public int insertPcInfo(Map paramMap) throws Exception;
	
}
