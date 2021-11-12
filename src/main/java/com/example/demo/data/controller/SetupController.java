package com.example.demo.data.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.service.BithumbService;
import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.data.service.impl.ItemServiceImpl;
import com.example.demo.vo.ItemDto;

@CrossOrigin
@RestController
public class SetupController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	BithumbService bithumbService;
	
	@Autowired
	DartServiceImpl dartService;
	
//	@RequestMapping("fillDb")
//	public void fillDB() throws IOException, ParseException {
//		logger.debug("=================================");
//		logger.debug("setUp start");
//		logger.debug("=================================");
//		//item table 채우기
//		logger.debug("start to insert itemTable");
////		itemService.insertKoreaItem();
//		
//		//item table corpNumber 컬럼 채우기
//		List<ItemDto> itemDtoList = itemService.getItemList();
//		HashMap<String, String> corpMap = dartService.getCorpCodeMap();
//		int len = itemDtoList.size();
//		for(int i = 0 ; i< len; i++) {
//			String ticker = itemDtoList.get(i).getId();
//			String corpCode = corpMap.get(ticker);
//			HashMap<String, String> inParams= new HashMap<>();
//			inParams.put("ticker", ticker);
//			inParams.put("corpCode", corpCode);
//			itemService.updateCorpCode(inParams);
//		}
//		//history data 삽입
//		for(ItemDto item : itemDtoList) {
//			itemService.insertHistoryData(item);
//		}
//	}
}
