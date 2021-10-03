package com.example.demo.dao;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

public interface SetupDao {
	int insertItem(ItemDto item);
	int insertHistoryData(HistoryDataDto historyData);
}
