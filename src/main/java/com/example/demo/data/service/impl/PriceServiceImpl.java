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
	public void getIndexHistory(Map<String, Object> inParam) throws Exception {
		
		IDib index = ClassFactory.createCpSvr8300();
		index.setInputValue(0, inParam.get("CODE_VALUE"));
		index.setInputValue(1, (int) 'D'); 
		index.setInputValue(3, (short)3);
		
		index.blockRequest();
		
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		
		String indexCode = (String) index.getHeaderValue(0);
		short indexQuant = (short) index.getHeaderValue(3);
		System.out.println("indexCode" + indexCode );
		System.out.println("indexQuant" + indexQuant );
		for(int i=0;i<indexQuant;i++) {
			
			int date = (int) index.getDataValue(0, i);
			float open = (float) index.getDataValue(1, i);
			float high = (float) index.getDataValue(2, i);
			float low = (float) index.getDataValue(3, i);
			float close = (float) index.getDataValue(4, i);
			Long volumeL = (Long) index.getDataValue(5, i);
			BigDecimal volume =  BigDecimal.valueOf(volumeL);
			historyDataDto.setINDEX_NAME((String)inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			priceDao.insIndexDaishin(historyDataDto);
			
		}
	}
	
}
