package com.example.demo.data.setting;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.mapper.SetupMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettingTest {
	@Autowired
	SetupMapper setupMapper;
	
	@Test
	public void setupMapperTest() {
		/*해야할것 세팅했나 찾고 안했으면 다지우고 다시 데이터 집어넣기8*/
		HashMap<String, Object> map = setupMapper.checkSetting();
		System.out.println(map==null);
	}
	@Test
	public void deleteMapper() {
		setupMapper.deleteBalanceSheet(null);
		setupMapper.deleteHistoryData(null);
		setupMapper.deleteIndexHistoryData(null);
	}
}
