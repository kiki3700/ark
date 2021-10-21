package com.example.demo.trading;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.util.CybosConnection;

import dashin.cptrade.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Deposit {
	static ICpTdUtil tdUtil = ClassFactory.createCpTdUtil();
	
	@Autowired
	CybosConnection connection;
	
	String account;
	@Before
	public void init() throws IOException {
		connection.connectionCheck();
		
		tdUtil.tradeInit(0);
		String[] accounts = (String[])  tdUtil.accountNumber();
		int len = accounts.length;
		System.out.println(len);
		account= new String();
		
		//계좌 번호 취득
		for(int i = 0; i< len ; i++) {
			System.out.println(i+" "+accounts[i]);
			account = accounts[i];
		}
	}
	
	
	//예수금 체크
	@Test
	public void depositTest() {
		//트레이드 이닛
		//계좌별 잔고 및 주문체결 평가 현황 데이터를 요청하고 수신한다.
		ICpTdDib data = ClassFactory.createCpTd6033();
//		System.out.println("계좌명"+account);
		data.setInputValue(0, account);
		data.setInputValue(1, "10");
		data.blockRequest();
		System.out.println("종목명"+data.getHeaderValue(0));
		System.out.println("계좌명"+data.getHeaderValue(1));
		System.out.println("대출일"+data.getHeaderValue(2));
		System.out.println("평가금"+data.getHeaderValue(3));
		
		//계좌별 매수주문 가능 금액/수량 데이터를 요청하고 수신한다.
		ICpTdDib calcuBuy = ClassFactory.createCpTdNew5331A();
		calcuBuy.setInputValue(0, account);
		calcuBuy.setInputValue(1, "10");
		calcuBuy.setInputValue(3, "01");
		calcuBuy.setInputValue(6, (int) '1');
		calcuBuy.blockRequest();
		System.out.println(calcuBuy.getHeaderValue(45));
		
		//주식 결제 예정 예수금 가계산 데이터를 요청하고 수신한다.
		ICpTdDib calDepo = ClassFactory.createCpTd0732();
		calDepo.setInputValue(0, account);
		calDepo.setInputValue(1, "10");
		calDepo.blockRequest();
		System.out.println("예수금"+calDepo.getHeaderValue(3));
		System.out.println("계좌번호"+calDepo.getHeaderValue(0));
		System.out.println("계상품관리 구분코드"+calDepo.getHeaderValue(1));
	}
	
	//매수전 테스트 
	@Test
	public void checkBuy() {
		ICpTdDib buy = ClassFactory.createCpTdNew5331A();
		buy.setInputValue(0, account); // 계좌번호
		buy.setInputValue(1, "10");	//상품관리 코드
		buy.setInputValue(2, "A005930");	//상품관리 코드
		buy.setInputValue(3, "01");  //주문호가 구분코드
		buy.setInputValue(6, (int) '1');		
		buy.blockRequest();
		System.out.println("종목코드"+buy.getHeaderValue(0));
		System.out.println("종목명"+buy.getHeaderValue(1));
		System.out.println("매도가능 수량"+buy.getHeaderValue(34));
		
	}
	
	@Test
	public void checkSell() {
		ICpTdDib data = ClassFactory.createCpTdNew5331B();
	}
}
