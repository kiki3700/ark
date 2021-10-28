package com.example.demo.trade.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.StockWrapper;
import com.example.demo.vo.TradingDto;
@Mapper
public interface TradeDao {
	//public int insertTrading(StockWrapper stockWrapper);
	public int insertTrading(TradingDto tradingDto);
	public int updateTradingStatus(TradingDto tradingDto);
}
