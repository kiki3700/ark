package com.example.demo.trade.service.serviceImpl;

import com.example.demo.trade.service.OrderService;
import com.example.demo.vo.StockWrapper;

import dashin.cptrade.ClassFactory;
import dashin.cptrade.ICpTdDib;
import dashin.cptrade.ICpTdUtil;

public class OrderServiceImpl implements OrderService {
	static ICpTdUtil tdUtil = ClassFactory.createCpTdUtil();
	static String account;
	
	//인터셉터로 가야될꺼 같아여~~
	public void init() {
		tdUtil.tradeInit(0);
		String[] accounts = (String[])  tdUtil.accountNumber();
		account = accounts[0];
	}
	public void order(StockWrapper stock) {
		ICpTdDib tdDib =ClassFactory.createCpTd0311();
		String position = stock.isPosition()? "1":"2";
		tdDib.setInputValue(0, position); //주문 종류코드( 매수 "1" 매도 "2")
		tdDib.setInputValue(1, account); //계좌번호
		tdDib.setInputValue(2, "10");		//종목 코드 
		tdDib.setInputValue(3, stock.getAmount());		//주문수량
		tdDib.setInputValue(4, stock.getTargetPrice());		//목표가격
		tdDib.setInputValue(8, "01");		//주문 호가 구분코드 "01" 지정가
		tdDib.blockRequest();
		
		//주문에 대한 체결 내역은 CpDib에 있는 CpConclusion object 를 통하여 얻을 수 있습니다
	}
	
	//필요 없을 꺼 같기도 하다.
	public boolean checkAvailable(StockWrapper stock) {
		boolean available = false;
		if(stock.isPosition()) {
			ICpTdDib buy = ClassFactory.createCpTdNew5331A();
			buy.setInputValue(0, account); // 계좌번호
			buy.setInputValue(1, "10");	//상품관리 코드
			buy.setInputValue(2, stock.getTicker());	//상품관리 코드
			buy.setInputValue(3, "01");  //주문호가 구분코드
			buy.setInputValue(6, (int) '1');		
			buy.blockRequest();
		}else {
			ICpTdDib sell = ClassFactory.createCpTdNew5331B();
			sell.setInputValue(0, account); // 계좌번호
			sell.setInputValue(1, "10"); 	//상품관리 코드
			sell.setInputValue(2, stock.getTicker());	//상품관리 코드
			sell.setInputValue(3, (int) '1');  //주식 채권구분코드		
			sell.blockRequest();
		}
		
		return available;
	}
	
	public void checkConlusion() {
		
	}
}
