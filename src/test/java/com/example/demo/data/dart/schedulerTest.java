package com.example.demo.data.dart;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CommonCodeConst;
import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.service.IndexService;

import dashin.cputil.ClassFactory;
import dashin.cputil.ICpUsCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class schedulerTest {

	@Autowired	
	@Qualifier("indexServiceImpl")
	IndexService priceService;
	
	@Autowired
	IndexMapper indexMapper;
	
	Map<Object, Object> inParam;
	
	@Before
	public void Initialize() {
		
		
	}
	
	@Test
	public void getIndex() throws Exception {
		//UsCode 나라별지수 인서트
		ICpUsCode usCode =  ClassFactory.createCpUsCode();
		Map<String, Object> paramMap =new HashMap<String,Object>();
		paramMap.put("id", CommonCodeConst.ICPUSCODE_COUNTRY);
		paramMap.put("api", "DAISHIN");
		try {			
			String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_COUNTRY);
			for(int i = 0; i < tickers.length; i++) {
				paramMap.put("code_value", tickers[i]);
				paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
				int order_num = i+1;
				paramMap.put("code_order_num", order_num);
				indexMapper.insertUsCoded(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//UsCode 업종별지수 인서트
		paramMap =new HashMap<String,Object>();
		paramMap.put("id", CommonCodeConst.ICPUSCODE_UPJONG);
		paramMap.put("api", "DAISHIN");
		try {			
			String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_UPJONG);
			for(int i = 0; i < tickers.length; i++) {
				paramMap.put("code_value", tickers[i]);
				paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
				int order_num = i+1;
				paramMap.put("code_order_num", order_num);
				indexMapper.insertUsCoded(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//UsCode 업종별지수 인서트
				paramMap =new HashMap<String,Object>();
				paramMap.put("id", CommonCodeConst.ICPUSCODE_RAW);
				paramMap.put("api", "DAISHIN");
				try {			
					String[] tickers =  (String[]) usCode.getUsCodeList(dashin.cputil.USTYPE.USTYPE_RAW);
					for(int i = 0; i < tickers.length; i++) {
						paramMap.put("code_value", tickers[i]);
						paramMap.put("code_name", usCode.getNameByUsCode((String) tickers[i]));
						int order_num = i+1;
						paramMap.put("code_order_num", order_num);
						indexMapper.insertUsCoded(paramMap);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		
	}
	
	
}
