package com.example.demo.data.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

public interface ItemService {
	int insertItem();
	void updateMarketCap();
	int insertHistoryData(ItemDto itemDto) throws ParseException;
	List<ItemDto> getItemList();
}
