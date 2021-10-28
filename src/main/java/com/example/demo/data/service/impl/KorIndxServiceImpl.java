package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.PriceDao;

import com.example.demo.vo.IndexHistoryDataDto;

import dashin.cpsysdib.ClassFactory;
import dashin.cpsysdib.ISysDib;

@Service
public class KorIndxServiceImpl  {
//	@Autowired
//	PriceDao priceDao;
	
	public void insKorIndexDaishin(Map<String, Object> inParam) {
		
		//코스피는 U001, 코스닥은 U201
		String ticker = (String) inParam.get("CODE_VALUE");
		ISysDib indexClient = ClassFactory.createStockChart();
		indexClient.setInputValue(0, "U0001");	//티커
		indexClient.setInputValue(1, (int) '3'); //요청 데이터 개수
		indexClient.setInputValue(5, new int[] {0,2,3,4,5,8}); //날짜 시가, 고가, 저가, 종가, 거래량
		indexClient.setInputValue(6, (int) 'D');
		
		indexClient.blockRequest();
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		short len = (short) indexClient.getHeaderValue(1);
		String indexCode= (String) indexClient.getHeaderValue(0);
		
		
		for(int i = 0 ; i< len ; i++) {
			long date = (long) indexClient.getDataValue(0, i);
			float open= (int) indexClient.getDataValue(1, i);
			float high= (int) indexClient.getDataValue(2, i);
			float low= (int) indexClient.getDataValue(3, i);
			float close= (int) indexClient.getDataValue(4, i);
			Long volumeL = (Long) indexClient.getDataValue(5, i);
			BigDecimal volume =  BigDecimal.valueOf(volumeL);
			historyDataDto.setINDEX_NAME((String)inParam.get("CODE_NAME"));
//			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			System.out.println(historyDataDto);
//			priceDao.insIndexDaishin(historyDataDto);
		}
	
	}
}
