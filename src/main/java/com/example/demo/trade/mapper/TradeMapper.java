package com.example.demo.trade.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.StockWrapper;
import com.example.demo.vo.TradingDto;
@Mapper
public interface TradeMapper {
	//public int insertTrading(StockWrapper stockWrapper);
	public int insertTrading(TradingDto tradingDto);
	public int updateTradingStatus(TradingDto tradingDto);
}
