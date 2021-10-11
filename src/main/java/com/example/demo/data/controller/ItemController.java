package com.example.demo.data.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.service.impl.ItemServiceImpl;
import com.example.demo.vo.ItemDto;

import dashin.cputil.CPE_MARKET_KIND;
import dashin.cputil.ClassFactory;
import dashin.cputil.ICpCodeMgr;

@CrossOrigin
@RestController
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;
	
	//전체 아이템 세팅하기
	@RequestMapping(value ="/init", method=RequestMethod.GET)
	public void initItem() {		
		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		
		Object[] kospiTickers= (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSPI);
		Object[] kosdaqTickers = (Object[]) codeMgr.getStockListByMarket(CPE_MARKET_KIND.CPC_MARKET_KOSDAQ);
		
		for(int i = 0; i < kospiTickers.length; i++) {
			//dto 생성
			HashMap<String, Object> inParam = new HashMap<>();
			inParam.put("ticker",(String) kospiTickers[i]);
			ItemDto itemDto = itemService.getItemDto(inParam);
			itemService.insertKoreaItem(itemDto);
		}
		for(int i = 0; i < kosdaqTickers.length; i++) {
			//dto 생성
			HashMap<String, Object> inParam = new HashMap<>();
			inParam.put("ticker",(String) kosdaqTickers[i]);
			ItemDto itemDto = itemService.getItemDto(inParam);
			itemService.insertKoreaItem(itemDto);
		}		
	}
}
