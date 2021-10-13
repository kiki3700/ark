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
		tdUtil.tradeInit(0);
		String[] accounts = (String[])  tdUtil.accountNumber();
		int len = accounts.length;
		System.out.println(len);
		for(int i = 0; i< len ; i++) {
			System.out.println(i+" "+accounts[i]);
		}
	}
}
