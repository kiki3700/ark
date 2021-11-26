package com.example.demo.data.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

public interface ItemService {
	int mergeItem();
	void updateMarketCap();
//	void insertHistoryData(ItemDto itemDto) throws ParseException;
	List<ItemDto> getItemList(Map<String, Object> inParams);
//	void initHistoryData() throws ParseException;
	void initHistoryData(Map<String, Object> inParam) throws ParseException;
	void initHistoryDataBatch(Map<String, Object> inParam) throws ParseException;
	void insertHistoryData(HashMap<String, Object> inParam) throws ParseException;
}
