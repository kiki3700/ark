package com.example.demo.data.index;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.data.service.IndexService;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class KorIndexTest {

	@Autowired
	IndexService ser;
	 
	List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
	HashMap<String, Object> map1 = new HashMap<>();
	HashMap<String, Object> map2 = new HashMap<>();
	@Before
	public void init() {
		
		map1.put("CODE_VALUE", "U001");
		list.add(map1);
		map2.put("CODE_VALUE", "U201");
		list.add(map2);
	}
	@Test
	public void test() {
		
		for(int i = 0; i<list.size();i++) {
			ser.insKorIndexDaishin(list.get(i));
		}
	}
	
}
