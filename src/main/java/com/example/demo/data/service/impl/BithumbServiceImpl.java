package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.data.dao.PriceDao;
import com.example.demo.data.service.BithumbService;
import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.IndexHistoryDataDto;

@Service
public class BithumbServiceImpl implements BithumbService{
	@Value("${bithumb.url")
	String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private PriceDao priceDao;

	@Override
	public List<IndexHistoryDataDto> getCrytoCurrencyHistory(Map<String, Object>inParams) {
		String targetUrl = url+"/public/candlestick/BTC_KRW/24h";
//		Map<String, String> queryMap = new HashMap<>();
//		queryMap.put("nm",(String) inParams.get("name"));
		String name = (String) inParams.get("name");
		//HashMap<String, Object> result = restTemplate.getForObject(targetUrl, HashMap.class, queryMap);
		HashMap<String, Object> result = webClient.mutate().baseUrl(targetUrl)
				.build()
				.get().retrieve().bodyToFlux(HashMap.class).blockLast();
		String msg =(String) result.get("status");
		List<IndexHistoryDataDto> resultList = new LinkedList<IndexHistoryDataDto>();
		if(msg.equals("0000")) {
			List<Object[]> datas = (List<Object[]>) result.get("data");
			
			int len = datas.size();
			int quant = (int) inParams.getOrDefault("quant", len);
			for(int i = len-quant; i < len; i++) {
				IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
				Object[] data = datas.get(i);
				historyDataDto.setINDEX_NAME((String) inParams.get("name"));
				historyDataDto.setTradingDate(new Date(Long.parseLong((String) data[0])));
				historyDataDto.setOpen(Integer.parseInt((String) data[1]));
				historyDataDto.setClose(Integer.parseInt((String) data[2]));
				historyDataDto.setHigh(Integer.parseInt((String) data[3]));
				historyDataDto.setLow(Integer.parseInt((String) data[4]));
				historyDataDto.setVolume(new BigDecimal((String) data[5]));
				resultList.add(historyDataDto);
			}
		}
		return resultList;
	}
	public void insertCryptoCurrencyHistory(List<IndexHistoryDataDto> DtoList) {
		 for(IndexHistoryDataDto dto : DtoList) {
			 priceDao.insertIndex(dto);
		 }
	}
}
