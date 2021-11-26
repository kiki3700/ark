package com.example.demo.data.scheduler;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.mapper.ItemMapper;
import com.example.demo.data.service.DartService;
import com.example.demo.data.service.IndexService;
import com.example.demo.vo.ItemDto;

@Component
public class KorIndexScheduler {
	
	@Autowired
	private DartService dartService;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	IndexMapper indexMapper;
	
	@Scheduled(cron = "0 30 15 * 1-5 ?")
	public void dsKorIndexScheduler() {
		List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("api","DAISHIN_KOR");		
		list = indexMapper.selectUsCodes(paramMap);
		for(int i = 0; i<list.size();i++) {
			HashMap<String, Object> inParam = list.get(i);
			inParam.put("quant", 7);
			indexService.insKorIndexDaishin(inParam);
		}
	}
}
