package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.vo.CandleChartDto;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandlechartTest {

	CandleChartDto dto = new CandleChartDto();
	@Test
	public void test() {
		dto.addCol("Mon", 11, 22, 23, 44);
		dto.addCol("Thu", 30, 44, 35, 45);
		Gson j = new Gson();
		String js = j.toJson(dto.getResult());
		System.out.print(js);
	}
}
