package com.example.demo.data.dart;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.BokConst;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.impl.BokServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BokTest {
	
	@Autowired
	BokServiceImpl bokService;
	
	@Test
	public void getBokIndex() throws Exception {
		Map<String, String> paramMap = new HashMap<String,String>();
		
		paramMap.put("REQUEST_TYPE", "/" + "json");
		paramMap.put("REQUEST_LANG", "/" + "kr");
		paramMap.put("START_NUM", "/" + "1");
		paramMap.put("END_NUM", "/" + "12");
		paramMap.put("REQUEST_CODE", "/" + BokConst.TB_BOK_BASE_RATE);
		paramMap.put("YMD", "/" + "MM");
		paramMap.put("START_DATE", "/" + "202001");
		paramMap.put("END_DATE", "/" + "202012");
		paramMap.put("ATCL_CODE1", "/" +BokConst.BOK_BASE_RATE);
		paramMap.put("ATCL_CODE2", "");
		paramMap.put("ATCL_CODE3", "");
		
		Map<Object, Object> result = new HashMap<Object,Object>();
		result = bokService.getBokIndex(paramMap);
	}
	
}
