package com.example.demo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

public class FormatConverter {
	static public Date stringToDate(String dateString) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
	Date date = format.parse(dateString);
	return date;
	}
	static public Date longToDate(long date) {
		try {
			return stringToDate(Long.toString(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	static public String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
		return format.format(date);
	}
	
	
	static List<HistoryDataDto> getKoreanStockItemDto(ItemDto item) throws ParseException {
		List<HistoryDataDto> dataList = new LinkedList<HistoryDataDto>();
		dashin.cpsysdib.ISysDib chart =dashin.cpsysdib.ClassFactory.createStockChart();
		String listingDate = FormatConverter.dateToString(item.getListingDate());
		chart.setInputValue(0, item.getTicker());
		chart.setInputValue(1, (int) '1');
		chart.setInputValue(3,listingDate);
		chart.setInputValue(5, new int[] {0,2,3,4, 5, 8});
		chart.setInputValue(6,(int) 'D');
		chart.setInputValue(9,(int) '1');
		do {
		chart.blockRequest();
		Object data = chart.getHeaderValue(3);
		System.out.println(chart.getHeaderValue(5));
		for(int i =0 ; i < Integer.parseInt(data.toString()); i++) {
			String time = Long.toString((long) chart.getDataValue(0, i));
			Date tradingDate = FormatConverter.stringToDate(time);
			Number open = (Number) chart.getDataValue(1, i);
			Number high = (Number) chart.getDataValue(2, i);
			Number low = (Number) chart.getDataValue(3, i);
			Number close = (Number) chart.getDataValue(4, i);
			Number volume = (Number) chart.getDataValue(5, i);
			HistoryDataDto vo = new HistoryDataDto();
			vo.setTradingDate(tradingDate);
			vo.setOpen(open);
			vo.setHigh(high);
			vo.setLow(low);
			vo.setClose(close);
			vo.setVolume(volume);
			dataList.add(vo);
			}
		}while(1==((int) chart._continue()));
		return dataList;
	}
}
