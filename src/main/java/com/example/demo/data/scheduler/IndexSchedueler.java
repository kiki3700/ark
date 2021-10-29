package com.example.demo.data.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.constants.BokConst;
import com.example.demo.constants.CommonCodeConst;
import com.example.demo.data.dao.IndexDao;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.impl.BokServiceImpl;

@Component
public class IndexSchedueler {
	@Autowired
	IndexService priceService;
	@Autowired
	IndexDao priceDao;
	@Autowired
	BokService bokService;
	
	
	//대신증권 인덱스 가져오기
	@Scheduled(cron = "0 0 06 * * ?")
	   public void dsIndexScheduler() {
			Map<String,Object> inParam = new HashMap<String,Object>();
	     	try {
	     		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
	     		codeMap = priceDao.selectUsCodes(inParam);
	     		for(Map<String, Object> paramMap : codeMap) {
	     			priceService.getIndexHistory(paramMap);
	     		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	     	
	   }
	//한국은행 
	@Scheduled(cron = "0 0 06 * * ?")
	   public void bokIndexScheduler() {
			
			SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMM");
			Calendar cal = Calendar.getInstance();
			
			String END_DATE = format1.format(cal.getTime()).substring(0,4);
			cal.add(Calendar.YEAR, -1);
			String START_DATE = format1.format(cal.getTime()).substring(0,4);
			
			Map<String,String> inParam = new HashMap<String,String>();
			inParam.put("REQUEST_TYPE", "/" + "json");
			inParam.put("REQUEST_LANG", "/" + "kr");
			inParam.put("START_NUM", "/" + "1");
			inParam.put("END_NUM", "/" + "12");
			inParam.put("REQUEST_CODE", "/" + BokConst.TB_BOK_BASE_RATE);
			inParam.put("YMD", "/" + "MM");
			inParam.put("START_DATE", "/" + START_DATE);
			
			inParam.put("END_DATE", "/" + END_DATE); 
			inParam.put("ATCL_CODE1", "/" +BokConst.BOK_BASE_RATE);
			inParam.put("ATCL_CODE2", "");
			inParam.put("ATCL_CODE3", "");
	     	try {
	     		 bokService.getBokIndex(inParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
	     	
	   }
}
