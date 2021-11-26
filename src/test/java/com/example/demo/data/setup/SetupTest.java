package com.example.demo.data.setup;

import java.io.IOException;
import java.text.ParseException;
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
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.impl.BithumbServiceImpl;
import com.example.demo.data.service.impl.BokServiceImpl;
import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.data.service.impl.ItemServiceImpl;
import com.example.demo.vo.ItemDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SetupTest {
	@Autowired
	SetupMapper setupMapper;
	
	@Autowired
	IndexMapper indexMapper;
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	BithumbServiceImpl bithumbService;
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	BokServiceImpl bokService;
	
	@Autowired
	DartServiceImpl dartService;
		
	@Test
	public void historyDataDtoTest() throws ParseException {
//		itemService.initHistoryData(new HashMap<String, Object>());
	}
	@Test
	public void itemSettingTest() throws IOException {
		//ㄱ. 아이템 insert
		itemService.mergeItem();
		
		//ㄴ. item table corpNumber 컬럼 채우기
		dartService.updateCorpCode();
		//ㄷ. 마켓캡 insert
		itemService.updateMarketCap();
	}
	
	@Test
	public void fsTest() {
//		dartService.initMultiBalanceSheet();
	}
	@Test
	public void Bit() {
//		bithumbService.insAllCrytoCurrencyHistory();
	}
	@Test
	public void korHistory() throws ParseException {
//			setupMapper.deleteHistoryData(null);
//			HashMap<String, Object> inParam = new HashMap<>();
//			inParam.put("threshold", 5000);
//			//A144620이거 안됨
//			itemService.initHistoryDataBatch(inParam);
		
	}
	
	@Test
	public void korInd() {
//		List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
//		HashMap<String, Object> paramMap = new HashMap<>();
//		paramMap.put("api","DAISHIN_KOR");		
//		list = indexMapper.selectUsCodes(paramMap);
//		for(int i = 0; i<list.size();i++) {
//			HashMap<String, Object> inParam = list.get(i);
//			inParam.put("quant", 32767);
//			indexService.insAllKorIndexDaishin(inParam);
//		}
	}
	@Test
	public void globInd() {
//		Map<String,Object> inParam = new HashMap<String,Object>();
//		inParam.put("api", "DAISHIN");
//     	try {
//     		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
//     		codeMap = indexMapper.selectUsCodes(inParam);
//     		int quant = 32767;
//     		for(Map<String, Object> map : codeMap) {
//     			map.put("QUANT",quant);
//     			indexService.insAllIndexHistory(map);
//     		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Test
	public void BokInd() {
//		//4. BOK인덱스
//		// 월간
//	SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMM");
//	SimpleDateFormat format2 = new SimpleDateFormat ("yyyy");
//
//	Calendar cal = Calendar.getInstance();
//	String MONTH_END_DATE = format1.format(cal.getTime());
//	cal.add(Calendar.YEAR, -1);
//	String MONTH_START_DATE = format1.format(cal.getTime());
//	//년간
//	String YEAR_END_DATE = format2.format(cal.getTime());
//	cal.add(Calendar.YEAR, -30 	);
//	String YEAR_START_DATE = format2.format(cal.getTime());
//		//한국은행 기준금리
//     	try {
////     		logger.debug("BOK GET BASE RATE START =====================");
//     		Map<String,String> inParamMap = new HashMap<String,String>();
//     		inParamMap.put("REQUEST_TYPE", "/" + "json");
//     		inParamMap.put("REQUEST_LANG", "/" + "kr");
//     		inParamMap.put("START_NUM", "/" + "1");
//     		inParamMap.put("END_NUM", "/" + "12");
//     		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_BOK_BASE_RATE);
//     		inParamMap.put("YMD", "/" + "MM");
//     		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
//     		
//     		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
//     		inParamMap.put("ATCL_CODE1", "/" +BokConst.BOK_BASE_RATE);
//     		inParamMap.put("ATCL_CODE2", "");
//     		inParamMap.put("ATCL_CODE3", "");
//     		bokService.getBokIndex(inParamMap);
////     		logger.debug("BOK GET BASE RATE END =====================");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//     	//생산자 물가총지수
//     	try {
////     		logger.debug("BOK GET PPI START =====================");
//     		Map<String,String> inParamMap = new HashMap<String,String>();
//     		inParamMap.put("REQUEST_TYPE", "/" + "json");
//     		inParamMap.put("REQUEST_LANG", "/" + "kr");
//     		inParamMap.put("START_NUM", "/" + "1");
//     		inParamMap.put("END_NUM", "/" + "12");
//     		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_PRD_PRICE_INDEX);
//     		inParamMap.put("YMD", "/" + "MM");
//     		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
//     		
//     		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
//     		inParamMap.put("ATCL_CODE1", "/" +BokConst.PRD_PRICE_TOTAL);
//     		inParamMap.put("ATCL_CODE2", "");
//     		inParamMap.put("ATCL_CODE3", "");
//     		bokService.getBokIndex(inParamMap);
////     		logger.debug("BOK GET PPI END =====================");
//     	} catch(Exception e) {
//     		e.printStackTrace();
//     	}
//     	
//     	//소비자 물가 총지수
//     	try {
////     		logger.debug("BOK GET CPI START =====================");
//     		Map<String,String> inParamMap = new HashMap<String,String>();
//     		inParamMap.put("REQUEST_TYPE", "/" + "json");
//     		inParamMap.put("REQUEST_LANG", "/" + "kr");
//     		inParamMap.put("START_NUM", "/" + "1");
//     		inParamMap.put("END_NUM", "/" + "12");
//     		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_CONSUM_PRICE_INDEX);
//     		inParamMap.put("YMD", "/" + "MM");
//     		inParamMap.put("START_DATE", "/" + MONTH_START_DATE);
//     		
//     		inParamMap.put("END_DATE", "/" + MONTH_END_DATE); 
//     		inParamMap.put("ATCL_CODE1", "/" +BokConst.CONSUM_PRICE_TOTAL);
//     		inParamMap.put("ATCL_CODE2", "");
//     		inParamMap.put("ATCL_CODE3", "");
//     		bokService.getBokIndex(inParamMap);
////     		logger.debug("BOK GET CPI END =====================");
//     	} catch(Exception e) {
//     		e.printStackTrace();
//     	}
//     	
//     	//GDP
//     	try {
////     		logger.debug("BOK GET GDP START =====================");
//     		Map<String,String> inParamMap = new HashMap<String,String>();
//     		inParamMap.put("REQUEST_TYPE", "/" + "json");
//     		inParamMap.put("REQUEST_LANG", "/" + "kr");
//     		inParamMap.put("START_NUM", "/" + "1");
//     		inParamMap.put("END_NUM", "/" + "20");
//     		inParamMap.put("REQUEST_CODE", "/" + BokConst.TB_GDP_INDEX);
//     		inParamMap.put("YMD", "/" + "YY");
//     		inParamMap.put("START_DATE", "/" + YEAR_START_DATE);
//     		
//     		inParamMap.put("END_DATE", "/" + YEAR_END_DATE); 
//     		inParamMap.put("ATCL_CODE1", "/" +BokConst.GDP);
//     		inParamMap.put("ATCL_CODE2", "");
//     		inParamMap.put("ATCL_CODE3", "");
//     		bokService.getBokIndex(inParamMap);
////     		logger.debug("BOK GET GDP END =====================");
//     	} catch(Exception e) {
//     		e.printStackTrace();
//     	}
	}
	
}
