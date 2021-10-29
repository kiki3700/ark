package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.data.dao.IndexDao;
import com.example.demo.data.service.BithumbService;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.IndexHistoryDataDto;

@Service
public class BithumbServiceImpl implements BithumbService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${bithumb.url}")
	String url;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private IndexDao priceDao;

	@Override
	public void insCrytoCurrencyHistory(Map<String, Object>inParams) {
		//비트코인 : BTC 이더리움  : ETH
		

		String name = (String) inParams.get("CDOE_VALUE");
		System.out.print(name);
		HashMap<String, Object> result = webClient.mutate()
				.baseUrl(url)
				.build()
				.get()
				.uri("/public/candlestick/{name}_KRW/24h",name)
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
		String msg =(String) result.get("status");
		List<IndexHistoryDataDto> resultList = new LinkedList<IndexHistoryDataDto>();
		if(msg.equals("0000")) {
			List<ArrayList> datas = (List<ArrayList>) result.get("data");
			int len = datas.size();
			int quant = 3;
			for(int i = len-quant; i < len; i++) {
				IndexHistoryDataDto indexHistoryDataDto = new IndexHistoryDataDto();
				ArrayList data = datas.get(i);
				indexHistoryDataDto.setINDEX_NAME(name);
				indexHistoryDataDto.setIndexDate(new Date((long) data.get(0)));
				indexHistoryDataDto.setOpen(Integer.parseInt((String) data.get(1)));
				indexHistoryDataDto.setClose(Integer.parseInt((String) data.get(2)));
				indexHistoryDataDto.setHigh(Integer.parseInt((String) data.get(3)));
				indexHistoryDataDto.setLow(Integer.parseInt((String) data.get(4)));
				indexHistoryDataDto.setVolume(new BigDecimal((String) data.get(5)));
//				System.out.println(indexHistoryDataDto);
				priceDao.insertIndex(indexHistoryDataDto);	
			}
		}
		
	}
}
