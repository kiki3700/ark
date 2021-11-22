package com.example.demo.perfomance;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.dao.BatchDao;
import com.example.demo.data.mapper.SetupMapper;
import com.example.demo.data.service.impl.ItemServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class insertHistoryBatchTest {
	@Autowired
	SetupMapper setupMapper;
	
	@Autowired
	BatchDao batchDao;
	
	@Autowired
	ItemServiceImpl itemSer;
	
	HashMap<String, Object> inParam = new HashMap<>();
	
	
	
	@Before
	public void initHistory() {
		setupMapper.deleteHistoryData(null);
	}
	
	@Test
	public void batch3000() throws ParseException {
//		setupMapper.deleteHistoryData(null);
//		inParam.put("threshold", 5000);
//		itemSer.initHistoryDataBatch(inParam);
	}
//	
//	@Test
//	public void matest300() throws ParseException {
//		inParam.put("threshold", 300);
//		itemSer.initHistoryData(inParam);
//	}
//	
//	@Test
//	public void matest500() throws ParseException {
//		inParam.put("threshold", 500);
//		itemSer.initHistoryData(inParam);
//	}
//	
	@Test
	public void matest1000() throws ParseException {
		setupMapper.deleteHistoryData(null);
		inParam.put("threshold", 300);
		itemSer.initHistoryData(inParam);
	}
	
//	@Test
//	public void matest1500() throws ParseException {
//		inParam.put("threshold", 1500);
//		itemSer.initHistoryData(inParam);
//	}
	

	
	
}
