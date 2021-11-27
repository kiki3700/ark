package com.example.demo.data.setup.indexHistoryData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.BokConst;
import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.mapper.SetupMapper;
import com.example.demo.data.service.BithumbService;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.DartService;
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexHistoryDataTest {
	@Autowired
	ItemService itemService;
	
	@Autowired
	IndexMapper indexMapper;
	
	@Autowired
	BithumbService bithumbService;
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	BokService bokService;
	
	@Autowired
	SetupMapper setupMapper;
	
	@Autowired
	DartService dartService;

	
	@Test
	public void init() {
		//2.비트코인 
//		bithumbService.insAllCrytoCurrencyHistory();
	//3-ㄱ 한국주요 인덱스
	List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
	HashMap<String, Object> paramMap = new HashMap<>();
	paramMap.put("api","DAISHIN_KOR");		
//	list = indexMapper.selectUsCodes(paramMap);
//	for(int i = 0; i<list.size();i++) {
		HashMap<String, Object> inParam = new HashMap<>();
//		inParam.put("quant", 100000);
//		indexService.insAllKorIndexDaishin(inParam);
//	}
//	
//	//3-ㄴ 전세계인덱스
//	HashMap<String,Object> inParam = new HashMap<String,Object>();
//	inParam.put("api", "DAISHIN");
// 	try {
// 		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
// 		codeMap = indexMapper.selectUsCodes(inParam);
// 		int quant = Short.MAX_VALUE;
// 		for(Map<String, Object> map : codeMap) {
// 			map.put("QUANT",quant);
// 			indexService.insAllIndexHistory(map);
// 		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	//4. BOK인덱스
	// 월간
SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMM");
SimpleDateFormat format2 = new SimpleDateFormat ("yyyy");

Calendar cal = Calendar.getInstance();
String MONTH_END_DATE = format1.format(cal.getTime());
cal.add(Calendar.YEAR, -1);
String MONTH_START_DATE = format1.format(cal.getTime());
//년간
String YEAR_END_DATE = format2.format(cal.getTime());
cal.add(Calendar.YEAR, -30 	);
String YEAR_START_DATE = format2.format(cal.getTime());
	//한국은행 기준금리
 	try {
// 		logger.debug("BOK GET BASE RATE START =====================");
 		Map<String,String> inParamMap = new HashMap<String,String>();
 		inParamMap.put("REQUEST_TYPE", "/" + "json");
 		inParamMap.put("REQUEST_LANG", "/" + "kr");
 		inParamMap.put("START_NUM", "/" + "1");
 		inParamMap.put("END_NUM", "/" + "12");
 		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_BOK_BASE_RATE);
 		inParamMap.put("YMD", "/" + "MM");
 		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
 		
 		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
 		inParamMap.put("ATCL_CODE1", "/" +BokConst.BOK_BASE_RATE);
 		inParamMap.put("ATCL_CODE2", "");
 		inParamMap.put("ATCL_CODE3", "");
 		bokService.getBokIndex(inParamMap);
// 		logger.debug("BOK GET BASE RATE END =====================");
	} catch (Exception e) {
		e.printStackTrace();
	}
 	//생산자 물가총지수
 	try {
// 		logger.debug("BOK GET PPI START =====================");
 		Map<String,String> inParamMap = new HashMap<String,String>();
 		inParamMap.put("REQUEST_TYPE", "/" + "json");
 		inParamMap.put("REQUEST_LANG", "/" + "kr");
 		inParamMap.put("START_NUM", "/" + "1");
 		inParamMap.put("END_NUM", "/" + "12");
 		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_PRD_PRICE_INDEX);
 		inParamMap.put("YMD", "/" + "MM");
 		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
 		
 		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
 		inParamMap.put("ATCL_CODE1", "/" +BokConst.PRD_PRICE_TOTAL);
 		inParamMap.put("ATCL_CODE2", "");
 		inParamMap.put("ATCL_CODE3", "");
 		bokService.getBokIndex(inParamMap);
// 		logger.debug("BOK GET PPI END =====================");
 	} catch(Exception e) {
 		e.printStackTrace();
 	}
 	
 	//소비자 물가 총지수
 	try {
// 		logger.debug("BOK GET CPI START =====================");
 		Map<String,String> inParamMap = new HashMap<String,String>();
 		inParamMap.put("REQUEST_TYPE", "/" + "json");
 		inParamMap.put("REQUEST_LANG", "/" + "kr");
 		inParamMap.put("START_NUM", "/" + "1");
 		inParamMap.put("END_NUM", "/" + "12");
 		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_CONSUM_PRICE_INDEX);
 		inParamMap.put("YMD", "/" + "MM");
 		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
 		
 		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
 		inParamMap.put("ATCL_CODE1", "/" +BokConst.CONSUM_PRICE_TOTAL);
 		inParamMap.put("ATCL_CODE2", "");
 		inParamMap.put("ATCL_CODE3", "");
 		bokService.getBokIndex(inParamMap);
// 		logger.debug("BOK GET CPI END =====================");
 	} catch(Exception e) {
 		e.printStackTrace();
 	}
 	
 	//GDP
 	try {
// 		logger.debug("BOK GET GDP START =====================");
 		Map<String,String> inParamMap = new HashMap<String,String>();
 		inParamMap.put("REQUEST_TYPE", "/" + "json");
 		inParamMap.put("REQUEST_LANG", "/" + "kr");
 		inParamMap.put("START_NUM", "/" + "1");
 		inParamMap.put("END_NUM", "/" + "20");
 		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_GDP_INDEX);
 		inParamMap.put("YMD", "/" + "YY");
 		inParamMap.put("START_DATE", "/" + YEAR_START_DATE);
 		
 		inParamMap.put("END_DATE", "/" + YEAR_END_DATE); 
 		inParamMap.put("ATCL_CODE1", "/" +BokConst.GDP);
 		inParamMap.put("ATCL_CODE2", "");
 		inParamMap.put("ATCL_CODE3", "");
 		bokService.getBokIndex(inParamMap);
// 		logger.debug("BOK GET GDP END =====================");
 	} catch(Exception e) {
 		e.printStackTrace();
 	}
 	inParam.put("presence", 1);
 	setupMapper.insertSetting(inParam);
		}
	}

