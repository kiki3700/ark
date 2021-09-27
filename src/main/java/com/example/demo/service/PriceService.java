package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.vo.PriceVo;

public interface PriceService {
	List<PriceVo> getPrice(HashMap param);
	void insertPrice(PriceVo vo);
}
