package com.example.demo.trade.service.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.trade.eventHandler.TradeEventHandler;
import com.example.demo.trade.mapper.TradeMapper;
import com.example.demo.trade.service.TradeService;
import com.example.demo.vo.StockWrapper;
import com.example.demo.vo.TradingDto;

import com4j.EventCookie;
import dashin.cptrade.ClassFactory;
import dashin.cptrade.ICpTdDib;
import dashin.cptrade.ICpTdUtil;
@Service
@Primary
public class TradeServiceImpl implements TradeService {
	static ICpTdUtil tdUtil = ClassFactory.createCpTdUtil();
	static String account;
	
	@Autowired
	TradeMapper tradeDao;
	
	//인터셉터로 가야될꺼 같아여~~
	@Override
	public void init() {
		tdUtil.tradeInit(0);
		String[] accounts = (String[])  tdUtil.accountNumber();
		account = accounts[0];
	}
	@Override
	public void order(StockWrapper stock) {
		ICpTdDib tdDib =ClassFactory.createCpTd0311();
		String position = stock.isPosition()? "1":"2";
		tdDib.setInputValue(0, position); //주문 종류코드( 매수 "1" 매도 "2")
		tdDib.setInputValue(1, account); //계좌번호
		tdDib.setInputValue(2, "10");		//종목 코드 
		tdDib.setInputValue(3, stock.getItemId());		//종목 코드 
		tdDib.setInputValue(4, stock.getAmount());		//주문수량
		tdDib.setInputValue(5, stock.getTargetPrice());		//목표가격
		tdDib.setInputValue(8, "01");		//주문 호가 구분코드 "01" 지정가
		tdDib.blockRequest();
		
		
		TradingDto tradingDto = new TradingDto();
		tradingDto.setItemId(stock.getItemId());
		tradingDto.setPortfolioTargetId(stock.getPortfolioTargetId());
		tradingDto.setPrice(stock.getTargetPrice());
		tradingDto.setAmount(stock.getAmount());
		tradingDto.setPosition(position);
		tradingDto.setOfferDate(new Date());
		tradingDto.setComplete(null);
		tradeDao.insertTrading(tradingDto);
	}
	
	//필요 없을 꺼 같기도 하다.
	public boolean checkAvailable(StockWrapper stock) {
		boolean available = false;
		if(stock.isPosition()) {
			ICpTdDib buy = ClassFactory.createCpTdNew5331A();
			buy.setInputValue(0, account); // 계좌번호
			buy.setInputValue(1, "10");	//상품관리 코드
			buy.setInputValue(2, stock.getItemId());	//상품관리 코드
			buy.setInputValue(3, "01");  //주문호가 구분코드
			buy.setInputValue(6, (int) '1');		
			buy.blockRequest();
		}else {
			ICpTdDib sell = ClassFactory.createCpTdNew5331B();
			sell.setInputValue(0, account); // 계좌번호
			sell.setInputValue(1, "10"); 	//상품관리 코드
			sell.setInputValue(2, stock.getItemId());	//상품관리 코드
			sell.setInputValue(3, (int) '1');  //주식 채권구분코드		
			sell.blockRequest();
		}
		
		return available;
	}
	@Override
	public void checkConlusion() {
		//주문에 대한 체결 내역은 CpDib에 있는 CpConclusion object 를 통하여 얻을 수 있습니다
		dashin.cpdib.IDib  dib = dashin.cpdib.ClassFactory.createCpConclusion();
		EventCookie cookie = dib.advise(dashin.cpdib.events._IDibEvents.class, new TradeEventHandler(dib, "conclude"));
		
	}
}
