package com.example.demo.trade.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.StockWrapper;
@Mapper
public interface TradeDao {
	public int insertTrading(StockWrapper stockWrapper);
}
