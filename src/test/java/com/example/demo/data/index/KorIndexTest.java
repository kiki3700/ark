package com.example.demo.data.index;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.service.impl.KorIndxServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class KorIndexTest {

	@Autowired
	KorIndxServiceImpl ser;
	
	HashMap<String, Object> map = new HashMap<>();
	@Before
	public void init() {
		map.put("CODE_VALUE", "U001");
		map.put("CODE_VALUE", "U201");
	}
	@Test
	public void test() {
		for(int i = 0; i<map.size();i++) {
			ser.insKorIndexDaishin(map);
		}
	}
	
}
