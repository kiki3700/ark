package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PriceDao;
import com.example.demo.service.PriceService;
import com.example.demo.vo.PriceVo;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceDao priceDao;
	
	@Override
	public List<PriceVo> getPrice(HashMap param) {
		List<PriceVo> price = priceDao.getPrice(param);
		return price;
	}

	@Override
	public void insertPrice(PriceVo vo) {
		priceDao.insertPrice(vo);
	}
	

}
