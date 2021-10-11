package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.PriceDao;
import com.example.demo.data.service.PriceService;
import com.example.demo.vo.IndexHistoryDataDto;
import com.example.demo.vo.PriceVo;

import dashin.cpdib.ClassFactory;
import dashin.cpdib.IDib;


@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	PriceDao priceDao;
	
	@Override
	public List<PriceVo> getPrice(HashMap param) {
		List<PriceVo> price = priceDao.getPrice(param);
		return price;
	}

	@Override
	public void insertPrice(PriceVo vo) {
		priceDao.insertPrice(vo);
	}

	@Override
	public IndexHistoryDataDto getIndexHistory(Map<Object, Object> inParam) throws Exception {
		
		IDib index = ClassFactory.createCpSvr8300();
		index.setInputValue(0, inParam.get("indexCode"));
		index.setInputValue(1, (int) 'D'); // 여기서 컨버팅 에러남 char를 variant로 변형시키지 못함
		index.setInputValue(2, inParam.get("quant"));
		
		index.blockRequest();
		
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		
		String indexCode = (String) index.getHeaderValue(0);
		short indexQuant = (short) index.getHeaderValue(3);
		System.out.println("indexCode" + indexCode );
		System.out.println("indexQuant" + indexQuant );
		historyDataDto.setINDEX_NAME(indexCode);
		for(int i=0;i<indexQuant;i++) {
			
			int date = (int) index.getDataValue(0, i);
			float open = (float) index.getDataValue(1, i);
			float high = (float) index.getDataValue(2, i);
			float low = (float) index.getDataValue(3, i);
			float close = (float) index.getDataValue(4, i);
			Long volumeL = (Long) index.getDataValue(5, i);
			BigDecimal volume =  BigDecimal.valueOf(volumeL);
			System.out.println("date : " +  date + "==========");
			System.out.println("open : " +  open);
			System.out.println("high : " +  high);
			System.out.println("low : " +  low);
			System.out.println("close : " +  close);
			System.out.println("volumeL : " +  volumeL);
			historyDataDto.setTradingDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			
			System.out.println("==========================");
			System.out.println("DTO : " + historyDataDto);
			System.out.println("==========================");
		}
		return historyDataDto;
	}
}
