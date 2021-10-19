package com.example.demo.data.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	BithumbService bithumbService;
	
	@Autowired
	DartServiceImpl dartService;
	
	@RequestMapping("fillDb")
	public void fillDB() throws IOException {
		itemService.insertKoreaItem();
		List<ItemDto> itemDtoList = itemService.getItemList();
		HashMap<String, String> corpMap = dartService.getCorpCodeMap();
		int len = itemDtoList.size();
		for(int i = 0 ; i< len; i++) {
			String ticker = itemDtoList.get(i).getTicker();
			String corpCode = corpMap.get(ticker);
			HashMap<String, String> inParams= new HashMap<>();
			inParams.put("ticker", ticker);
			inParams.put("corpCode", corpCode);
			itemService.updateCorpCode(inParams);
		}
		
	}
}
