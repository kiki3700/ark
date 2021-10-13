package com.example.demo.trading;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dashin.cptrade.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Deposit {
	ICpTdUtil tdUtil = ClassFactory.createCpTdUtil();
	@Test
	public void test() {
		String account = (String) tdUtil.accountNumber();
	}
}
