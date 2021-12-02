package com.example.demo.util;

import org.springframework.stereotype.Component;

import dashin.cputil.ClassFactory;
import dashin.cputil.ICpCybos;
import dashin.cputil.LIMIT_TYPE;

@Component
public class LimitChecker {
	
	
	public void checkRQLimit() {
		ICpCybos cybos = ClassFactory.createCpCybos();
		int cnt = cybos.getLimitRemainCount(LIMIT_TYPE.LT_NONTRADE_REQUEST);
		int time = cybos.getLimitRemainTime(LIMIT_TYPE.LT_NONTRADE_REQUEST);
		if(cnt == 0) {
			try {
				System.out.println("동작 그만"+time);
				Thread.sleep(time);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
	}
	public void checkSubLimit() {
		ICpCybos cybos = ClassFactory.createCpCybos();
		int cnt = cybos.getLimitRemainCount(LIMIT_TYPE.LT_SUBSCRIBE);
		int time = cybos.getLimitRemainTime(LIMIT_TYPE.LT_SUBSCRIBE);
		if(cnt == 0) {
			try {
				System.out.println("동작 그만"+time);
				Thread.sleep(time);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
	}
	public void checkRqTradeLimit() {
		ICpCybos cybos = ClassFactory.createCpCybos();
		int cnt = cybos.getLimitRemainCount(LIMIT_TYPE.LT_TRADE_REQUEST);
		int time = cybos.getLimitRemainTime(LIMIT_TYPE.LT_TRADE_REQUEST);
		if(cnt == 0) {
			try {
				System.out.println("동작 그만"+time);
				Thread.sleep(time);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
	}
}
