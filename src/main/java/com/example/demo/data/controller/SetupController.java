package com.example.demo.data.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.service.BithumbService;
import com.example.demo.data.service.BokService;
import com.example.demo.data.service.DartService;
import com.example.demo.data.service.IndexService;
import com.example.demo.data.service.ItemService;
import com.example.demo.vo.ItemDto;

@CrossOrigin
@RestController
public class SetupController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
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
	DartService dartService;
	
	@RequestMapping("fillDb")
	public void fillDB() throws IOException, ParseException {
		/*
		 * 1. 아이템 채우기
		 * 	ㄱ) 아이템
		 * 	ㄴ) corpNum
		 * 	ㄷ) 마켓캡
		 * 	ㄹ) 재무제표 insert
		 * 	ㅁ) 가격구하기 
		 * 2. 비트코인
		 * 3. 인덱스
		 * 	ㄱ)한국 주요 인덱스
		 * 	ㄴ) 해외주요인덱스
		 * 4. bok인덱스 
		 */
		logger.debug("=================================");
		logger.debug("setUp start");
		logger.debug("=================================");
		//item table 채우기
		logger.debug("start to insert itemTable");

		//1. 아이템 insert
		HashMap<String,Object> inParams = new HashMap<>();
		
		//ㄱ. 아이템 insert
		itemService.insertItem();
		
		//ㄴ. item table corpNumber 컬럼 채우기
		dartService.updateCorpCode();
		//ㄷ. 마켓캡 insert
		itemService.updateMarketCap();
		
		List<ItemDto> itemDtoList = itemService.getItemList(inParams);
		//ㄹ. 재무재표 insert
		dartService.insAllBalaceSheet();
		
		//history data 삽입
		for(ItemDto item : itemDtoList) {
			itemService.inittHistoryData(item);
		}
		//2.비트코인 
		bithumbService.insAllCrytoCurrencyHistory();
		
		//3-ㄱ 한국주요 인덱스
		List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("api","DAISHIN_KOR");		
		list = indexMapper.selectUsCodes(paramMap);
		for(int i = 0; i<list.size();i++) {
			HashMap<String, Object> inParam = list.get(i);
			inParam.put("quant", 100000);
			indexService.insKorIndexDaishin(inParam);
		}
		
		//3-ㄴ 전세계인덱스
		Map<String,Object> inParam = new HashMap<String,Object>();
		inParam.put("api", "DAISHIN");
     	try {
     		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
     		codeMap = indexMapper.selectUsCodes(inParam);
     		int quant = Short.MAX_VALUE;
     		for(Map<String, Object> map : codeMap) {
     			map.put("QUANT",quant);
     			indexService.getIndexHistory(map);
     		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//4. BOK인덱스
	}
}
