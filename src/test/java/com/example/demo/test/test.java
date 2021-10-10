package com.example.demo.test;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.dao.PriceDao;
import com.example.demo.vo.LineChartDto;
import com.example.demo.vo.PriceVo;
import com.google.gson.Gson;


public class test {
	LineChartDto dto = new LineChartDto();
	
	@Autowired
	PriceDao dao;
	@Test
	public void test() {
		dto.addColumn("date", null, null, "date");
		dto.addColumn("price", null, null, "number");
		dto.createRows(1);
		dto.addCell(0, "20121212", "date");
		dto.addCell(0, "3000", "number");

		Gson j = new Gson();
		String js = j.toJson(dto.getResult());
		System.out.print(js);
	}
}
