package com.example.demo.data.dart;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.constants.ReprtCode;
import com.example.demo.data.dao.BatchDao;
import com.example.demo.data.mapper.DartMapper;
import com.example.demo.data.mapper.ItemMapper;
import com.example.demo.util.DartUtil;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.BalanceSheetDto;
import com.example.demo.vo.ItemDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiBalanceSheet {
	
	@Value("${dartkey}")
	String crtfcKey;
	@Value("${darturl}")
	String dartUrl;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BatchDao batchDao;
	
	@Autowired
	private WebClient webClient;
	
	
	@Autowired
	private DartUtil dartUtil;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private DartMapper dartMapper;
	
	@Autowired
	private FormatConverter formatConverter;
	
	@Test
	public void formTest() {
		HashMap<String, Object> inParam = new HashMap<>();
		inParam.put("isCorpCode", true);
		List<ItemDto> itemDtoList = itemMapper.selectItemList(inParam);
		assertEquals(itemDtoList.size() , 2340);
		List<BalanceSheetDto> balanceSheetDtoList = new ArrayList<>();
		StringBuilder sb= new StringBuilder();
		for(int i = 0 ; i<500; i++) { 
			sb.append(itemDtoList.get(i).getCorpCode()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("corpCode", sb.toString());
//				for(int i = 0; i<6; i++) {
					map.put("crtfcKey", crtfcKey);
					Date d = new Date();
					int year = d.getYear()+1900;
					
					map.put("year", year);
//					for(int j = 1; j <=4;j++) {
						map.put("reprtCode", ReprtCode.getReprtCode(4));
						HashMap<String, Object> resultMap = webClient.mutate()
								.baseUrl(dartUrl)
								.build()
								.get()
								.uri("/api/fnlttMultiAcnt.json?crtfc_key={crtfcKey}&corp_code={corpCode}&bsns_year={year}&reprt_code={reprtCode}",map)
								.retrieve()
								.bodyToMono(HashMap.class)
								.block();
						

	
//					}
	}
	}