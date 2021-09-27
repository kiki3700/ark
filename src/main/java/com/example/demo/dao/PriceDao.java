package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.PriceVo;

public interface PriceDao {
	List<PriceVo> getPrice(HashMap param);
	void insertPrice(com.example.demo.vo.PriceVo vo);
}
