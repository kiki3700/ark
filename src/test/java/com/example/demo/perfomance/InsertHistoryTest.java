//package com.example.demo.perfomance;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.demo.data.mapper.ItemMapper;
//import com.example.demo.util.FormatConverter;
//import com.example.demo.vo.HistoryDataDto;
//import com.example.demo.vo.ItemDto;
//
//import dashin.cpsysdib.ISysDib;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InsertHistoryTest {
//	@Autowired
//	ItemMapper item;
//	
//	List<HistoryDataDto> historyDataDtoList = new LinkedList<HistoryDataDto>();
//	HashMap<String, Object> inParam = new HashMap<>();
//	HashMap<String, Long> result = new HashMap<>();
//	@Before
//	public void init() throws ParseException {
//		ItemDto itemDto = new ItemDto();
//		inParam.put("itemId", "A005930");
//		itemDto.setId("A005930");
//		
//		ISysDib sysDib = dashin.cpsysdib.ClassFactory.createStockChart();
//		sysDib.setInputValue(0, itemDto.getId());
//		sysDib.setInputValue(1, (int) '1');
//		sysDib.setInputValue(3, 19750611);
//		sysDib.setInputValue(5, new int[] {0,1,2,3,4,5,8});
//		sysDib.setInputValue(6, (int) 'D');
//		sysDib.setInputValue(9,(int) '1');		
//		
//			sysDib.blockRequest();
//			Object data = sysDib.getHeaderValue(3);
//			System.out.println(sysDib.getHeaderValue(5));
//			
//			for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
//				String time = Long.toString((long) sysDib.getDataValue(0, i));
//				Date tradingDate = FormatConverter.stringToDate(time);
//				Number open = (Number) sysDib.getDataValue(1, i);
//				Number high = (Number) sysDib.getDataValue(2, i);
//				Number low = (Number) sysDib.getDataValue(3, i);
//				Number close = (Number) sysDib.getDataValue(4, i);
//				Number volume = (Number) sysDib.getDataValue(5, i);
//				HistoryDataDto historyDataDto = new HistoryDataDto();
//				historyDataDto.setItemId("A005930");
//				historyDataDto.setTradingDate(tradingDate);
//				historyDataDto.setOpen(open);
//				historyDataDto.setHigh(high);
//				historyDataDto.setLow(low);
//				historyDataDto.setClose(close);
//				historyDataDto.setVolume(volume);
//				historyDataDtoList.add(historyDataDto);
//				}
//					
//	}
//	@Test
//	public void insertWithAll() throws ParseException {
//
//		item.deleteHistoryData(inParam);
//		long st = System.currentTimeMillis();
//		item.initHistoryDataDtoList(historyDataDtoList);
//		long et = System.currentTimeMillis();
//		result.put("all", et-st);
//	}
//	@Test
//	public void insertWithUnion() {
//		item.deleteHistoryData(inParam);
//		long st = System.currentTimeMillis();
//		item.insertHistoryDataDtoList(historyDataDtoList);
//		long et = System.currentTimeMillis();
//		result.put("union", et-st);
//	}
//	@Test
//	public void insertWithUnionWithoutDelet() {
//		item.deleteHistoryData(inParam);
//		long st = System.currentTimeMillis();
//		item.insertHistoryDataDtoList(historyDataDtoList);
//		long et = System.currentTimeMillis();
//		result.put("unionWtDelete", et-st);
//	}
//	@Test
//	public void insertEach() {
//		item.deleteHistoryData(inParam);
//		long st = System.currentTimeMillis();
//		for(HistoryDataDto dto : historyDataDtoList) {
//			item.mergeHistoryDataDto(dto);
//		}
//		long et = System.currentTimeMillis();
//		result.put("each", et-st);
//	}
//	@Test
//	public void insertEachWithoutDelet() {
//		long st = System.currentTimeMillis();
//		for(HistoryDataDto dto : historyDataDtoList) {
//			item.mergeHistoryDataDto(dto);
//		}
//		long et = System.currentTimeMillis();
//		result.put("eachWtDelete", et-st);
//	}
//}
