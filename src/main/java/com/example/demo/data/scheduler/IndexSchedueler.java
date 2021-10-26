package com.example.demo.data.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.constants.CommonCodeConst;
import com.example.demo.data.dao.PriceDao;
import com.example.demo.data.service.PriceService;

@Component
public class IndexSchedueler {
	@Autowired
	PriceService priceService;
	@Autowired
	PriceDao priceDao;
	
	
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
}
