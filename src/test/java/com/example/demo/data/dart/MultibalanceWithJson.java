package com.example.demo.data.dart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.mapper.ItemMapper;
import com.example.demo.data.service.impl.DartServiceImpl;
import com.example.demo.util.DartUtil;
import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.ItemDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultibalanceWithJson {
	@Autowired
	ItemMapper itemMapper;
	@Autowired
	DartUtil dartUtil;
	
	@Autowired
	DartServiceImpl dartService;
	
	LinkedHashMap<String, Object> json;
	List<HashMap<String, Object>> datas;
	HashMap<String, BalanceSheetDto> connectBalanceMap= new HashMap<>();
	HashMap<String, BalanceSheetDto> balanceMap = new HashMap<>();
	@Before
	public void init() throws FileNotFoundException, IOException, ParseException, org.apache.tomcat.util.json.ParseException {
		
		ClassPathResource resource = new ClassPathResource("test/res500.json");
		JSONParser parser = new JSONParser(resource.getInputStream());
		
		 json = (LinkedHashMap<String, Object>) parser.parse();
		System.out.println(json.get("status"));
		datas = (List<HashMap<String, Object>>) json.get("list");
		
		List<ItemDto> itemList = itemMapper.selectItemList(null);
		
		for(ItemDto itemDto : itemList) {
			if(itemDto.getCorpCode()==null) continue;
//			resultMap.put(itemDto.getId(), null);
		}
	}
//	@Test
//	public void ss() {
//		
//		 
//		HashSet<String> set = new HashSet<>();
//		for(HashMap<String, Object> map :datas) {	
//			dartUtil.toBalanceSheetDtoMap(map, connectBalanceMap, balanceMap);
//		}
//		int cnt=0;
//		for(String item : connectBalanceMap.keySet()) {
//			BalanceSheetDto bal =connectBalanceMap.get(item);
//			if(bal==null) {
//				set.add(item);
//				continue;
//			}
//			System.out.println(bal);
//			cnt++;
//		}
//		for(String item : balanceMap.keySet()) {
//			System.out.println(balanceMap.get(item));
//		}
//		System.out.println(connectBalanceMap.size() +" "+balanceMap.size());
//		for(String s : set) {
//			System.out.println(s);
//		}
//	}
	@Test
	public void serTest() {
		dartService.initMultiBalanceSheet();
	}
}
