package com.example.demo.data.index;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.constants.BokConst;
import com.example.demo.data.dao.IndexDao;
import com.example.demo.data.service.BithumbService;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.IndexService;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BokIndex {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	IndexService priceService;
	@Autowired
	IndexDao priceDao;
	@Autowired
	BokService bokService;
	@Autowired
	BithumbService bithumbService;
	
	@Test
	public void insBok() {

		// 월간
	SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMM");
	SimpleDateFormat format2 = new SimpleDateFormat ("yyyy");

	Calendar cal = Calendar.getInstance();
	String MONTH_END_DATE = format1.format(cal.getTime());
	cal.add(Calendar.YEAR, -1);
	String MONTH_START_DATE = format1.format(cal.getTime());
	//년간
	String YEAR_END_DATE = format2.format(cal.getTime());
	cal.add(Calendar.YEAR, -19);
	String YEAR_START_DATE = format2.format(cal.getTime());
		//한국은행 기준금리
     	try {
     		logger.debug("BOK GET BASE RATE START =====================");
     		Map<String,String> inParam = new HashMap<String,String>();
     		inParam.put("REQUEST_TYPE", "/" + "json");
     		inParam.put("REQUEST_LANG", "/" + "kr");
     		inParam.put("START_NUM", "/" + "1");
     		inParam.put("END_NUM", "/" + "12");
     		inParam.put("REQUEST_CODE", "/" + BokConst.TB_BOK_BASE_RATE);
     		inParam.put("YMD", "/" + "MM");
     		inParam.put("START_DATE", "/" + MONTH_START_DATE);
     		
     		inParam.put("END_DATE", "/" + MONTH_END_DATE); 
     		inParam.put("ATCL_CODE1", "/" +BokConst.BOK_BASE_RATE);
     		inParam.put("ATCL_CODE2", "");
     		inParam.put("ATCL_CODE3", "");
     		bokService.getBokIndex(inParam);
     		logger.debug("BOK GET BASE RATE END =====================");
		} catch (Exception e) {
			e.printStackTrace();
		}
     	//생산자 물가총지수
     	try {
     		
     		Map<String,String> inParam = new HashMap<String,String>();
     		inParam.put("REQUEST_TYPE", "/" + "json");
     		inParam.put("REQUEST_LANG", "/" + "kr");
     		inParam.put("START_NUM", "/" + "1");
     		inParam.put("END_NUM", "/" + "12");
     		inParam.put("REQUEST_CODE", "/" + BokConst.TB_PRD_PRICE_INDEX);
     		inParam.put("YMD", "/" + "MM");
     		inParam.put("START_DATE", "/" + MONTH_START_DATE);
     		
     		inParam.put("END_DATE", "/" + MONTH_END_DATE); 
     		inParam.put("ATCL_CODE1", "/" +BokConst.PRD_PRICE_TOTAL);
     		inParam.put("ATCL_CODE2", "");
     		inParam.put("ATCL_CODE3", "");
     		bokService.getBokIndex(inParam);
     		
     	} catch(Exception e) {
     		e.printStackTrace();
     	}
     	
     	//소비자 물가 총지수
     	try {
     		logger.debug("BOK GET CPI START =====================");
     		Map<String,String> inParam = new HashMap<String,String>();
     		inParam.put("REQUEST_TYPE", "/" + "json");
     		inParam.put("REQUEST_LANG", "/" + "kr");
     		inParam.put("START_NUM", "/" + "1");
     		inParam.put("END_NUM", "/" + "12");
     		inParam.put("REQUEST_CODE", "/" + BokConst.TB_CONSUM_PRICE_INDEX);
     		inParam.put("YMD", "/" + "MM");
     		inParam.put("START_DATE", "/" + MONTH_START_DATE);
     		
     		inParam.put("END_DATE", "/" + MONTH_END_DATE); 
     		inParam.put("ATCL_CODE1", "/" +BokConst.CONSUM_PRICE_TOTAL);
     		inParam.put("ATCL_CODE2", "");
     		inParam.put("ATCL_CODE3", "");
     		bokService.getBokIndex(inParam);
     		logger.debug("BOK GET CPI END =====================");
     	} catch(Exception e) {
     		e.printStackTrace();
     	}
     	
     	//GDP
     	try {
     		logger.debug("BOK GET GDP START =====================");
     		Map<String,String> inParam = new HashMap<String,String>();
     		inParam.put("REQUEST_TYPE", "/" + "json");
     		inParam.put("REQUEST_LANG", "/" + "kr");
     		inParam.put("START_NUM", "/" + "1");
     		inParam.put("END_NUM", "/" + "20");
     		inParam.put("REQUEST_CODE", "/" + BokConst.TB_GDP_INDEX);
     		inParam.put("YMD", "/" + "YY");
     		inParam.put("START_DATE", "/" + YEAR_START_DATE);
     		
     		inParam.put("END_DATE", "/" + YEAR_END_DATE); 
     		inParam.put("ATCL_CODE1", "/" +BokConst.GDP);
     		inParam.put("ATCL_CODE2", "");
     		inParam.put("ATCL_CODE3", "");
     		bokService.getBokIndex(inParam);
     		logger.debug("BOK GET GDP END =====================");
     	} catch(Exception e) {
     		e.printStackTrace();
     	}

	}
}
