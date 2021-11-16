package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.service.IndexService;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.IndexHistoryDataDto;
import com.example.demo.vo.PriceVo;

import dashin.cpdib.ClassFactory;
import dashin.cpdib.IDib;

@Primary
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	IndexMapper indexMapper;
	
	@Override
	public List<PriceVo> getPrice(HashMap param) {
		List<PriceVo> price = indexMapper.getPrice(param);
		return price;
	}

	@Override
	public void insertPrice(PriceVo vo) {
		indexMapper.insertPrice(vo);
	}

	@Override
	public void getIndexHistory(Map<String, Object> inParam) throws Exception {
		
		IDib index = ClassFactory.createCpSvr8300();
		index.setInputValue(0, inParam.get("CODE_VALUE"));
		index.setInputValue(1, (int) 'D'); 
		index.setInputValue(3, (int) inParam.getOrDefault("QUANT", 3));
		
		index.blockRequest();
		
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		
		String indexCode = (String) index.getHeaderValue(0);
		short indexQuant = (short) index.getHeaderValue(3);
		System.out.println("indexCode" + indexCode );
		System.out.println("indexQuant" + indexQuant );
		for(int i=0;i<indexQuant;i++) {
			Number n = (Number) index.getDataValue(0, i);
			long dateL = n.longValue();
			Date date = FormatConverter.longToDate(dateL);
			float open = (float) index.getDataValue(1, i);
			float high = (float) index.getDataValue(2, i);
			float low = (float) index.getDataValue(3, i);
			float close = (float) index.getDataValue(4, i);
			Long volumeL = (Long) index.getDataValue(5, i);
			BigDecimal volume =  BigDecimal.valueOf(volumeL);
			historyDataDto.setIndexName((String)inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			indexMapper.insIndexDaishin(historyDataDto);
		}
	}
	@Override
	public void insAllIndexHistory(Map<String, Object> inParam) throws Exception {
		
		IDib index = ClassFactory.createCpSvr8300();
		index.setInputValue(0, inParam.get("CODE_VALUE"));
		index.setInputValue(1, (int) 'D'); 
		index.setInputValue(3, (int) inParam.getOrDefault("QUANT", 3));
		
		index.blockRequest();
		
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		
		String indexCode = (String) index.getHeaderValue(0);
		short indexQuant = (short) index.getHeaderValue(3);
//		System.out.println("indexCode" + indexCode );
//		System.out.println("indexQuant" + indexQuant );
		List<IndexHistoryDataDto> historyDataDtoList = new ArrayList<>();
		for(int i=0;i<indexQuant;i++) {
			Number n = (Number) index.getDataValue(0, i);
			long dateL = n.longValue();
			Date date = FormatConverter.longToDate(dateL);
			float open = (float) index.getDataValue(1, i);
			float high = (float) index.getDataValue(2, i);
			float low = (float) index.getDataValue(3, i);
			float close = (float) index.getDataValue(4, i);
			Long volumeL = (Long) index.getDataValue(5, i);
			BigDecimal volume =  BigDecimal.valueOf(volumeL);
			historyDataDto.setIndexName((String)inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			historyDataDtoList.add(historyDataDto);
		}
		indexMapper.insertAllIndex(historyDataDtoList);
	}
	
	@Override
	public void insKorIndexDaishin(Map<String, Object> inParam) {
		
		//코스피는 U001, 코스닥은 U201
		String ticker = (String) inParam.get("CODE_VALUE");
		int quant = (int) inParam.getOrDefault("quant", 3);
		dashin.cpsysdib.ISysDib indexClient = dashin.cpsysdib.ClassFactory.createStockChart();
		indexClient.setInputValue(0, ticker);	//티커
		indexClient.setInputValue(1, (int) '2'); // 요청 구분 데이터 개수
		indexClient.setInputValue(4, quant); // 요청 개수		
		indexClient.setInputValue(5, new int[] {0,2,3,4,5,8}); //날짜 시가, 고가, 저가, 종가, 거래량
		indexClient.setInputValue(6, (int) 'D');
		
		indexClient.blockRequest();
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		int len = (int) indexClient.getHeaderValue(3);
		String indexCode= (String) indexClient.getHeaderValue(0);
		System.out.println(len);
		
		for(int i = 0 ; i<len  ; i++) {
			long dateL = (long) indexClient.getDataValue(0, i);
			Date date = FormatConverter.longToDate(dateL);
			float open= (float) indexClient.getDataValue(1, i);
			float high= (float) indexClient.getDataValue(2, i);
			float low= (float) indexClient.getDataValue(3, i);
			float close= (float) indexClient.getDataValue(4, i);
			Number volumeL = (Number) indexClient.getDataValue(5, i);
			BigDecimal volume = new BigDecimal(volumeL.doubleValue()); 
			System.out.println(volumeL.getClass());
			historyDataDto.setIndexName((String) inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
//			System.out.println(historyDataDto);
			indexMapper.insIndexDaishin(historyDataDto);
		}
	}	
	@Override
	public void insAllKorIndexDaishin(Map<String, Object> inParam) {
		
		//코스피는 U001, 코스닥은 U201
		String ticker = (String) inParam.get("CODE_VALUE");
		int quant = (int) inParam.getOrDefault("quant", 3);
		dashin.cpsysdib.ISysDib indexClient = dashin.cpsysdib.ClassFactory.createStockChart();
		indexClient.setInputValue(0, ticker);	//티커
		indexClient.setInputValue(1, (int) '2'); // 요청 구분 데이터 개수
		indexClient.setInputValue(4, quant); // 요청 개수		
		indexClient.setInputValue(5, new int[] {0,2,3,4,5,8}); //날짜 시가, 고가, 저가, 종가, 거래량
		indexClient.setInputValue(6, (int) 'D');
		
		indexClient.blockRequest();
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		int len = (int) indexClient.getHeaderValue(3);
//		String indexCode= (String) indexClient.getHeaderValue(0);
//		System.out.println(len);
		List<IndexHistoryDataDto> historyDataDtoList = new ArrayList<>();
		for(int i = 0 ; i<len  ; i++) {
			long dateL = (long) indexClient.getDataValue(0, i);
			Date date = FormatConverter.longToDate(dateL);
			float open= (float) indexClient.getDataValue(1, i);
			float high= (float) indexClient.getDataValue(2, i);
			float low= (float) indexClient.getDataValue(3, i);
			float close= (float) indexClient.getDataValue(4, i);
			Number volumeL = (Number) indexClient.getDataValue(5, i);
			BigDecimal volume = new BigDecimal(volumeL.doubleValue()); 
			System.out.println(volumeL.getClass());
			historyDataDto.setIndexName((String) inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			historyDataDtoList.add(historyDataDto);
//			System.out.println(historyDataDto);
			indexMapper.insIndexDaishin(historyDataDto);
		}
	indexMapper.insertAllIndex(historyDataDtoList);
	}	
}
