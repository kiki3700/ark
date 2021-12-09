package com.example.demo.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.demo.vo.PriceVo;

import dashin.cputil.ICpCybos;

public interface IndexService {
	
	List<PriceVo> getPrice(HashMap param);
	
	void insertPrice(PriceVo vo);
	
	void getIndexHistory(Map<String, Object> paramMap) throws Exception;

	void insKorIndexDaishin(Map<String, Object> inParam);

	void insAllIndexHistory(Map<String, Object> inParam) throws Exception;

	void insAllKorIndexDaishin(Map<String, Object> inParam);

}
