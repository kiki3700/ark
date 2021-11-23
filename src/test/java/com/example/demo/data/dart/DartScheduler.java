package com.example.demo.data.dart;

import java.util.Calendar;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.service.impl.DartServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DartScheduler {
	
	@Autowired
	DartServiceImpl dartService;
	
	@Test
	public void scheduler(){
		HashMap<String, Object> inParam = new HashMap<>();
		inParam = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		inParam.put("year", cal.get(Calendar.YEAR));
		ReprtCode reportCode = new ReprtCode();
		int quart= cal.get(Calendar.MONDAY)/3+1;
		inParam.put("reprtCode", reportCode.getReprtCode(quart));
		dartService.insertMultiBalanceSheet(inParam);
	}
}
