package com.example.demo.data.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.data.dao.BatchDao;
import com.example.demo.data.mapper.IndexMapper;
import com.example.demo.data.service.IndexService;
import com.example.demo.util.FormatConverter;
import com.example.demo.vo.IndexHistoryDataDto;
import com.example.demo.vo.PriceVo;

import dashin.cpdib.ClassFactory;
import dashin.cpdib.IDib;
import dashin.cputil.ICpCybos;
import dashin.cputil.LIMIT_TYPE;

@Primary
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	IndexMapper indexMapper;
	
	@Autowired
	BatchDao batchDao;
	
	public boolean checkRqLimit() {	
		ICpCybos cybos = dashin.cputil.ClassFactory.createCpCybos();
		int cnt = cybos.getLimitRemainCount(LIMIT_TYPE.LT_NONTRADE_REQUEST);
		
		if(cnt == 0) {
			try {

				int time = cybos.getLimitRemainTime(LIMIT_TYPE.LT_NONTRADE_REQUEST);
				System.out.println("동작 그만"+time);
				Thread.sleep(time);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
		return false;
	}
	
	public boolean checkRqLimit(List<IndexHistoryDataDto> indexHistoryDataDtoList) {	
		ICpCybos cybos = dashin.cputil.ClassFactory.createCpCybos();
		int cnt = cybos.getLimitRemainCount(LIMIT_TYPE.LT_NONTRADE_REQUEST);
		
		if(cnt == 0) {
			try {
				batchDao.mergeIndexHistoryDataDtoList(indexHistoryDataDtoList);
				indexHistoryDataDtoList.clear();;
				int time = cybos.getLimitRemainTime(LIMIT_TYPE.LT_NONTRADE_REQUEST);
				System.out.println("동작 그만"+time);
				Thread.sleep(time);
			}catch (InterruptedException e) {
				System.out.println("error");
			}
		}
		return false;
	}
	
	@Override
	public List<PriceVo> getPrice(HashMap param) {
		List<PriceVo> price = indexMapper.getPrice(param);
		return price;
	}

	@Override
	public void insertPrice(PriceVo vo) {
		indexMapper.insertPrice(vo);
	}
	
	
//사용중
	@Override
	public void getIndexHistory(Map<String, Object> inParam) throws Exception {
		
		IDib index = ClassFactory.createCpSvr8300();
		List<HashMap<String, Object>> codeMap = new ArrayList<HashMap<String,Object>>();
		codeMap = indexMapper.selectUsCodes(inParam);
		short quant =(short) inParam.getOrDefault("QUANT", 3);
		List<IndexHistoryDataDto> historyDataDtoList= new ArrayList<>();
		for(Map<String, Object> paramMap : codeMap) {
			paramMap.put("QUANT",quant);
			checkRqLimit(historyDataDtoList);
			System.out.println(paramMap.get("CODE_VALUE"));
			index.setInputValue(0,paramMap.get("CODE_VALUE"));
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
				historyDataDto.setIndexName((String)paramMap.get("CODE_NAME"));
				historyDataDto.setIndexDate(date);
				historyDataDto.setClose(close);
				historyDataDto.setHigh(high);
				historyDataDto.setLow(low);
				historyDataDto.setOpen(open);
				historyDataDto.setVolume(volume);
				System.out.println(historyDataDto);
				historyDataDtoList.add(historyDataDto);
			}
		}
		batchDao.mergeIndexHistoryDataDtoList(historyDataDtoList);
	}
	
	@Override
	public void insAllIndexHistory(Map<String, Object> inParam) throws Exception {
		List<IndexHistoryDataDto> historyDataDtoList = new ArrayList<>();
		IDib index = ClassFactory.createCpSvr8300();
		checkRqLimit(historyDataDtoList);
		index.setInputValue(0, inParam.get("CODE_VALUE"));
		index.setInputValue(1, (int) 'D'); 
		index.setInputValue(3, (int) inParam.getOrDefault("QUANT", 3));
		index.blockRequest();
		String indexCode = (String) index.getHeaderValue(0);
		short indexQuant = (short) index.getHeaderValue(3);
//		System.out.println("indexCode" + indexCode );
//		System.out.println("indexQuant" + indexQuant );
		for(int i=0;i<indexQuant;i++) {
			IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
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
		batchDao.initIndexHistoryDataDtoList(historyDataDtoList);
	}
	
	//사용중
	@Override
	public void insKorIndexDaishin(Map<String, Object> inParam) {
		IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
		checkRqLimit();
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
		int len = (int) indexClient.getHeaderValue(3);
		String indexCode= (String) indexClient.getHeaderValue(0);
		System.out.println(len);
		List<IndexHistoryDataDto> indexHistoryDataDtoList = new ArrayList<>();
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
			indexHistoryDataDtoList.add(historyDataDto);
//			System.out.println(historyDataDto);
		}
		batchDao.mergeIndexHistoryDataDtoList(indexHistoryDataDtoList);
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

		int len = (int) indexClient.getHeaderValue(3);
//		String indexCode= (String) indexClient.getHeaderValue(0);
//		System.out.println(len);
		List<IndexHistoryDataDto> historyDataDtoList = new LinkedList<>();
		for(int i = 0 ; i<len  ; i++) {
			IndexHistoryDataDto historyDataDto = new IndexHistoryDataDto();
			long dateL = (long) indexClient.getDataValue(0, i);
			Date date = FormatConverter.longToDate(dateL);
			float open= (float) indexClient.getDataValue(1, i);
			float high= (float) indexClient.getDataValue(2, i);
			float low= (float) indexClient.getDataValue(3, i);
			float close= (float) indexClient.getDataValue(4, i);
			Number volumeL = (Number) indexClient.getDataValue(5, i);
			BigDecimal volume = new BigDecimal(volumeL.doubleValue()); 
//			System.out.println(volumeL.getClass());
			historyDataDto.setIndexName((String) inParam.get("CODE_NAME"));
			historyDataDto.setIndexDate(date);
			historyDataDto.setClose(close);
			historyDataDto.setHigh(high);
			historyDataDto.setLow(low);
			historyDataDto.setOpen(open);
			historyDataDto.setVolume(volume);
			historyDataDtoList.add(historyDataDto);
			System.out.println(historyDataDto);
//			indexMapper.insIndexDaishin(historyDataDto);
//			if(i%500==0) {
//				batchDao.initIndexHistoryDataDtoList(historyDataDtoList);
//				historyDataDtoList.clear();
//			}
		}
		if(historyDataDtoList.size()>0) {
			batchDao.initIndexHistoryDataDtoList(historyDataDtoList);
			historyDataDtoList.clear();
		}
//	indexMapper.insertAllIndex(historyDataDtoList);
	}	
}
