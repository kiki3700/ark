package com.example.demo.data.dart;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.vo.BalanceSheetDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DartTest {
	@Autowired
	DartServiceImpl dartService;
	
	Map<String, Object> inParam;
	
	@Before
	public void Initialize() {
		inParam = new HashMap<>();
		inParam.put("corpCode", "00126380");
		inParam.put("year", "2018");
		inParam.put("reprtCode", "11011");
	}
	
	@Test
	public void getBs() throws ParseException {
//		System.out.print(inParam);
		BalanceSheetDto dto = dartService.getBalaceSheet(inParam);
		System.out.print(dto);
	}
}
