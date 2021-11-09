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

import com.example.demo.data.dao.IndexDao;
import com.example.demo.data.service.IndexService;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class KorIndexTest {

	@Autowired
	IndexService ser;
	
	@Autowired
	IndexDao indexDao;
	 
	List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
	
	@Before
	public void init() {
		HashMap<String, Object> paramMap = new HashMap<>();
		list = indexDao.selectKorCodes(paramMap);
		System.out.println("di");
		System.out.println(list.size());
		for(HashMap<String, Object> map : list) {
			System.out.println(map.get("CODE_VALUE"));
		}
		
	}
	@Test
	public void test() {
		
//		for(int i = 0; i<list.size();i++) {
//			ser.insKorIndexDaishin(list.get(i));
//		}
	}
	
}
