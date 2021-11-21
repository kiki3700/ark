package com.example.demo.data.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.HistoryDataDto;
import com.example.demo.vo.ItemDto;

@Mapper
public interface SetupMapper {
	int insertItem(ItemDto item);
	int insertHistoryData(HistoryDataDto historyData);
	HashMap<String, Object> checkSetting();
	int insertSetting(HashMap<String, Object> inParam);
	void deleteHistoryData(HashMap<String, Object> inParam);
	void deleteIndexHistoryData(HashMap<String, Object> inParam);
	void deleteBalanceSheet(HashMap<String, Object> inParam);
}
