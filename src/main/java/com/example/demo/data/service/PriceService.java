package com.example.demo.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.IndexHistoryDataDto;
import com.example.demo.vo.PriceVo;

public interface PriceService {
	List<PriceVo> getPrice(HashMap param);
	
	void insertPrice(PriceVo vo);
	
	IndexHistoryDataDto getIndexHistory(Map<Object,Object> inParam) throws Exception;
}
