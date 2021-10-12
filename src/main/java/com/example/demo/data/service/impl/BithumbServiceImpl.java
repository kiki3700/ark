package com.example.demo.data.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.vo.HistoryDataDto;

@Service
public class BithumbServiceImpl {
	@Value("${bithumb.url")
	String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void setCrytoCurrencyHistory(Map<String, Object>inParams) {
		List<HistoryDataDto> resultList = new LinkedList<HistoryDataDto>();
		String targetUrl = url+"/public/candlestick/{nm}_KRW/24h";
		Map<String, String> map = new HashMap<>();
		map.put("nm",(String) inParams.getOrDefault("name", null));
		HashMap<String, Object> result = restTemplate.getForObject(targetUrl, HashMap.class);
		
		String msg =(String) result.get("status");
		if(msg.equals("0000")) {
			List<Object[]> datas = (List<Object[]>) result.get("data");
			int len = datas.size();
			for(int i = 0; i < len; i++) {
				HistoryDataDto historyDataDto = new HistoryDataDto();
				Object[] data = datas.get(i);
				historyDataDto.setTradingDate(new Date(Integer.parseInt((String) data[0])));
				historyDataDto.setOpen(Integer.parseInt((String) data[1]));
				historyDataDto.setClose(Integer.parseInt((String) data[2]));
				historyDataDto.setHigh(Integer.parseInt((String) data[3]));
				historyDataDto.setLow(Integer.parseInt((String) data[4]));
				historyDataDto.setVolume(Float.parseFloat((String) data[5]));
				resultList.add(historyDataDto);
			}
		}
	}
}
