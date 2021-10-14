package com.example.demo.trading;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dashin.cptrade.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Deposit {
	static ICpTdUtil tdUtil = ClassFactory.createCpTdUtil();
	@Test
	public void test() {
		//트레이드 이닛
		tdUtil.tradeInit(0);
		String[] accounts = (String[])  tdUtil.accountNumber();
		int len = accounts.length;
		System.out.println(len);
		String account = new String();
		
		//계좌 번호 취득
		for(int i = 0; i< len ; i++) {
			System.out.println(i+" "+accounts[i]);
			account = accounts[i];
		}
		
		ICpTdDib data = ClassFactory.createCpTd6033();
		System.out.println("계좌명"+account);
		data.setInputValue(0, account);
		data.setInputValue(1, "10");
		data.request();
		System.out.println("평가금"+data.getHeaderValue(3));
		System.out.println("계좌명"+data.getHeaderValue(1));
		ICpTdDib calcuBuy = ClassFactory.createCpTdNew5331A();
		calcuBuy.setInputValue(0, account);
		calcuBuy.setInputValue(1, "10");
		calcuBuy.setInputValue(3, "01");
		calcuBuy.setInputValue(6, (int) '1');
		calcuBuy.request();
		System.out.println(calcuBuy.getHeaderValue(45));
		ICpTdDib calDepo = ClassFactory.createCpTd0732();
		calDepo.setInputValue(0, account);
		calDepo.setInputValue(1, "10");
		System.out.println(calDepo.getHeaderValue(3));
		System.out.println(calDepo.getHeaderValue(0).getClass());
		
		
	}
}
