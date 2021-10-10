package com.example.demo.data.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.PriceVo;
@Mapper
public interface PriceDao {
	List<PriceVo> getPrice(HashMap param);
	void insertPrice(PriceVo vo);
}
