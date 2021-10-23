package com.example.demo.data.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
			Map<String,Object> codeMap = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
	     	try {
	     		paramMap.put("id", CommonCodeConst.ICPUSCODE_COUNTRY);
	     		paramMap.put("code_name", "S&P500");
	     		codeMap = priceDao.selectUsCodeCont(paramMap);
	     		String indexCodet = (String) codeMap.get("code_value");
	     		priceService.getIndexHistory(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	     	
	   }
}
