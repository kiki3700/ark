package com.example.demo.data.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.constants.BokConst;
import com.example.demo.constants.CommonCodeConst;
import com.example.demo.data.dao.IndexMapper;
import com.example.demo.data.service.BithumbService;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.impl.BokServiceImpl;

@Component
public class IndexScheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	IndexService priceService;
	@Autowired
	IndexMapper indexMapper;
	@Autowired
	BokService bokService;
	@Autowired
	BithumbService bithumbService;
	
	
	//대신증권 인덱스 가져오기
	@Scheduled(cron = "0 0 06 * * ?")
	   public void dsIndexScheduler() {
			Map<String,Object> inParam = new HashMap<String,Object>();
			inParam.put("api", "DAISHIN");
	     	try {
	     		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
	     		codeMap = indexMapper.selectUsCodes(inParam);
	     		short quant = 3;
	     		for(Map<String, Object> paramMap : codeMap) {
	     			paramMap.put("QUANT",quant);
	     			priceService.getIndexHistory(paramMap);
	     		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	     	
	   }
	//한국은행 주요지표 
	@Scheduled(cron = "0 0 06 * * ?")
	   public void bokIndexScheduler() {
			
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
	     		logger.debug("BOK GET PPI START =====================");
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
	     		logger.debug("BOK GET PPI END =====================");
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
	//빗썸API 주요 암호화폐 정보
	@Scheduled(cron = "0 0 06 * * ?")
		   public void bitScheduler() {
				Map<String,Object> inParam = new HashMap<String,Object>();
		     	
				try {
					inParam.put("id", CommonCodeConst.BITHUMB_INDEX);
					List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
		     		codeMap = indexMapper.selectUsCodeCont(inParam);
		     		for(Map<String, Object> paramMap : codeMap) {
		     			bithumbService.insCrytoCurrencyHistory(inParam);
		     		}
		     		logger.debug("BITHUMB GET IDNEX END =====================");
				} catch (Exception e) {
					e.printStackTrace();
				}
		     
		   }
	
}
